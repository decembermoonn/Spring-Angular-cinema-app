package cinema.service.services;

import cinema.service.converters.ReservationListToReservedSeatDtoList;
import cinema.service.models.Reservation;
import cinema.service.models.Screening;
import cinema.service.models.Ticket;
import cinema.service.models.User;
import cinema.service.models.dtos.ReservationDataDto;
import cinema.service.models.dtos.ReservationWithDetailsForUserDto;
import cinema.service.models.dtos.ReservedSeatDto;
import cinema.service.rabbitmq.ReservationChangePublisher;
import cinema.service.repositories.AccountRepository;
import cinema.service.repositories.ReservationRepository;
import cinema.service.repositories.ScreeningRepository;
import cinema.service.repositories.TicketRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.security.Principal;
import java.time.LocalDateTime;
import java.util.*;
import java.util.concurrent.TimeoutException;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

@Service
@Slf4j
@RequiredArgsConstructor
public class ReservationService {
  private final ReservationRepository reservationRepository;
  private final ScreeningRepository screeningRepository;
  private final AccountRepository accountRepository;
  private final TicketRepository ticketRepository;

  public List<ReservedSeatDto> getReservedSeatsByScreening(long screeningId) {
    List<Reservation> reservations = reservationRepository.findAllByScreeningId(screeningId);
    return mapToReservedSeatDtos(reservations);
  }

  private List<ReservedSeatDto> mapToReservedSeatDtos(List<Reservation> reservations) {
    return reservations.stream().map(this::mapToReservedSeatDto).toList();
  }

  private ReservedSeatDto mapToReservedSeatDto(Reservation reservation) {
    return ReservedSeatDto.builder()
        .columnNumber(reservation.getColumnNumber())
        .rowNumber(reservation.getRowNumber())
        .build();
  }

  /* TODO - checking if some reservations already exist (seats are overlapping),
  TODO - check if some seats are OK for room in which screenings takes place [no time for doing it right now]. */
  public ResponseEntity<Object> postReservations(
      ReservationDataDto reservationDataDto, Principal principal) {
    Optional<Screening> screening =
        screeningRepository.findById(reservationDataDto.getScreeningId());
    if (screening.isEmpty()) return ResponseEntity.badRequest().body("Screening ID is invalid");
    if (screening.get().getBeginning().isBefore(LocalDateTime.now()))
      return ResponseEntity.badRequest().body("This screening has already happened");

    if (reservationDataDto.getTicketIds().length
        != reservationDataDto.getReservedSeatDtoList().size())
      return ResponseEntity.badRequest().body("Tickets and seats must not differ in length");

    int maxReservationGroup = reservationRepository.getMaxReservationGroup().orElse(0);

    LocalDateTime expiration =
        getMinimumDate(screening.get().getBeginning(), LocalDateTime.now().plusHours(12));
    User user = accountRepository.getById(principal.getName());

    List<Ticket> tickets = mapTicketIdsToTickets(reservationDataDto.getTicketIds());
    if (tickets.stream().anyMatch(Objects::isNull))
      return ResponseEntity.badRequest().body("Some ticket ids are invalid");

    List<Reservation> reservations =
        mapDataToReservation(
            reservationDataDto.getReservedSeatDtoList(),
            expiration,
            maxReservationGroup + 1,
            screening.get(),
            user,
            tickets);

     reservationRepository.saveAll(reservations);
      try {
          ReservationChangePublisher.publish(reservationDataDto, true);
      } catch (IOException | TimeoutException e) {
          log.error("ERROR! Unable to publish data (however it isn't necessary).");
      }
      return ResponseEntity.ok().build();
  }

  private List<Reservation> mapDataToReservation(
      List<ReservedSeatDto> reservedSeatDtoList,
      LocalDateTime expiration,
      int reservationGroup,
      Screening screening,
      User user,
      List<Ticket> tickets) {
    return IntStream.range(0, reservedSeatDtoList.size())
        .mapToObj(
            i -> {
              Reservation reservation = new Reservation();
              reservation.setRowNumber(reservedSeatDtoList.get(i).getRowNumber());
              reservation.setColumnNumber(reservedSeatDtoList.get(i).getColumnNumber());
              reservation.setReservationGroup(reservationGroup);
              reservation.setExpirationDate(expiration);
              reservation.setScreening(screening);
              reservation.setUser(user);
              reservation.setTicket(tickets.get(i));
              return reservation;
            })
        .toList();
  }

  private List<Ticket> mapTicketIdsToTickets(long[] ticketIds) {
    List<Ticket> tickets = ticketRepository.findAll();
    Map<Long, Ticket> ticketMap = tickets.stream().collect(Collectors.toMap(Ticket::getId, t -> t));
    return Arrays.stream(ticketIds).mapToObj(ticketMap::get).toList();
  }

  private LocalDateTime getMinimumDate(LocalDateTime beginning, LocalDateTime nowPlusSomeHours) {
    if (beginning.isBefore(nowPlusSomeHours)) return beginning;
    return nowPlusSomeHours;
  }

  public List<ReservationWithDetailsForUserDto> getReservationsForUser(String username) {
    List<Reservation> reservations = reservationRepository.getJoinedReservationsForUser(username);
    var map =
        reservations.stream().collect(Collectors.groupingBy((Reservation::getReservationGroup)));
    return map.entrySet().stream()
        .map(
            e -> {
              ReservationWithDetailsForUserDto newRes = new ReservationWithDetailsForUserDto();
              Reservation pattern = e.getValue().get(0);
              newRes.setBeginning(pattern.getScreening().getBeginning());
              newRes.setImageUrl(pattern.getScreening().getMovie().getImageUrl());
              newRes.setReservationGroup(e.getKey());
              newRes.setTitle(pattern.getScreening().getMovie().getTitle());
              newRes.setReservedSeatDtoList(
                  ReservationListToReservedSeatDtoList.convert(e.getValue()));
              return newRes;
            })
        .collect(Collectors.toList());
  }

  @Transactional
  public ResponseEntity<Void> deleteReservationByGroupId(int groupId) {
    reservationRepository.deleteAllByReservationGroup(groupId);
    return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
  }
}
