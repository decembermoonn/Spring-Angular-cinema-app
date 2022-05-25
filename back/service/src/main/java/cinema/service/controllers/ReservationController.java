package cinema.service.controllers;

import cinema.service.models.dtos.ReservedSeatDto;
import cinema.service.services.ReservationService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@CrossOrigin
@RestController
@RequiredArgsConstructor
public class ReservationController {
    private final ReservationService reservationService;

    @GetMapping("/reservations")
    public List<ReservedSeatDto> getReservedSeatsByScreening(@RequestParam long screeningId){
        return reservationService.getReservedSeatsByScreening(screeningId);
    }

}
