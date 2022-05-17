package cinema.service.services;

import cinema.service.models.Screening;
import cinema.service.models.dto.ScreeningWithMovieDto;
import cinema.service.repositories.ScreeningRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ScreeningService {
  private final ScreeningRepository screeningRepository;

  public List<ScreeningWithMovieDto> getScreeningsOnDate(LocalDate date) {
    LocalDateTime dayBeginning = date.atStartOfDay();
    LocalDateTime dayEnding = date.atTime(23, 59);
    List<Screening> screenings = screeningRepository.findAllWithinTime(dayBeginning, dayEnding);
    return mapToScreeningWithMovieDtos(screenings);
  }

  public LocalDate getLastScreeningDate() {
    return screeningRepository.findLastScreeningDate().toLocalDate();
  }

  private List<ScreeningWithMovieDto> mapToScreeningWithMovieDtos(List<Screening> screenings) {
    return screenings.stream().map(this::mapToScreeningWithMovieDto).collect(Collectors.toList());
  }

  private ScreeningWithMovieDto mapToScreeningWithMovieDto(Screening screening) {
    return new ScreeningWithMovieDto(screening.getMovie(), screening.getBeginning());
  }
}
