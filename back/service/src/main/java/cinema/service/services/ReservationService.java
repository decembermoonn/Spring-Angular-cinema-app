package cinema.service.services;

import cinema.service.models.Reservation;
import cinema.service.models.dtos.ReservedSeatDto;
import cinema.service.repositories.ReservationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ReservationService {
  private final ReservationRepository reservationRepository;

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
}
