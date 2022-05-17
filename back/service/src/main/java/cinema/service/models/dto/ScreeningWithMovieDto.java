package cinema.service.models.dto;

import cinema.service.models.Movie;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.time.LocalDateTime;

@Getter
@RequiredArgsConstructor
public class ScreeningWithMovieDto {
  private final Movie movie;
  private final LocalDateTime beginning;
}