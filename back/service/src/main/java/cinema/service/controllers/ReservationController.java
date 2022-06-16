package cinema.service.controllers;

import cinema.service.models.dtos.ReservationDataDto;
import cinema.service.models.dtos.ReservedSeatDto;
import cinema.service.services.ReservationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.security.Principal;
import java.util.List;

@CrossOrigin
@RestController
@RequiredArgsConstructor
public class ReservationController {
  private final ReservationService reservationService;

  @GetMapping("/reservations")
  public List<ReservedSeatDto> getReservedSeatsByScreening(@RequestParam long screeningId) {
    return reservationService.getReservedSeatsByScreening(screeningId);
  }

  @PostMapping("/reservation")
  public ResponseEntity<Object> postReservations(
      @RequestBody @Valid ReservationDataDto reservationDataDto,
      BindingResult bindingResult,
      Principal principal) {
    if (bindingResult.hasErrors()) {
      List<String> errMessages = bindingResult.getAllErrors().stream().map(e -> e.getDefaultMessage()).toList();
      return ResponseEntity.badRequest().body(errMessages);
    }
    return reservationService.postReservations(reservationDataDto, principal);
  }
}
