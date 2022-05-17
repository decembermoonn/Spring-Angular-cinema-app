package cinema.service.controllers;

import cinema.service.models.Screening;
import cinema.service.models.dto.ScreeningWithMovieDto;
import cinema.service.services.ScreeningService;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@CrossOrigin
@RequiredArgsConstructor
public class ScreeningController {

  private final ScreeningService screeningService;

  @GetMapping("/screenings")
  public ResponseEntity<Object> getScreeningsByDate(
      @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date) {
    List<ScreeningWithMovieDto> screenings = screeningService.getScreeningsOnDate(date);
    return ResponseEntity.ok(screenings);
  }
}
