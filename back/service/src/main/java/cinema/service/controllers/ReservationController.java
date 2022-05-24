package cinema.service.controllers;

import cinema.service.models.Reservation;
import cinema.service.repositories.ReservationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@CrossOrigin
@RestController
@RequiredArgsConstructor
public class ReservationController {
    private final ReservationRepository reservationRepository;

    @GetMapping("/reservations")
    public List<Reservation> getReservationsByScreening(@RequestParam long screeningId){
        return reservationRepository.findAllByScreeningId(screeningId);
    }

}
