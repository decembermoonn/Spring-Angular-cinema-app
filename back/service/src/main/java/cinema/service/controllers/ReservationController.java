package cinema.service.controllers;

import cinema.service.models.dtos.ReservationDataDto;
import cinema.service.models.dtos.ReservationWithDetailsForUserDto;
import cinema.service.models.dtos.ReservedSeatDto;
import cinema.service.services.ReservationService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.security.Principal;
import java.util.List;

@CrossOrigin
@RestController
@RequiredArgsConstructor
@Slf4j
public class ReservationController {
  private final ReservationService reservationService;

  @GetMapping("/reservations/seats")
  public List<ReservedSeatDto> getReservedSeatsByScreening(@RequestParam long screeningId) {
    return reservationService.getReservedSeatsByScreening(screeningId);
  }

  @PostMapping("/reservation")
  public ResponseEntity<Object> postReservations(
      @RequestBody @Valid ReservationDataDto reservationDataDto,
      BindingResult bindingResult,
      Principal principal) {
    log.warn(reservationDataDto.toString());
    if (bindingResult.hasErrors()) {
      List<String> errMessages = bindingResult.getAllErrors().stream().map(e -> e.getDefaultMessage()).toList();
      return ResponseEntity.badRequest().body(errMessages);
    }
    return reservationService.postReservations(reservationDataDto, principal);
  }

  @GetMapping("/reservations")
  public ResponseEntity<Object> getReservationsForUser(Principal principal) {
    if(principal == null) return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
    return ResponseEntity.ok(reservationService.getReservationsForUser(principal.getName()));
  }

  //TODO - should be sensitive to user who is deleting (checking if he owns the reseravation)
  @DeleteMapping("/reservation/{groupId}")
  public ResponseEntity<Void> deleteReservationByGroupId(@PathVariable int groupId) {
    return reservationService.deleteReservationByGroupId(groupId);
  }
}
