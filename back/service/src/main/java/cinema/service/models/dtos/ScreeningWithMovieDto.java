package cinema.service.models.dtos;

import cinema.service.models.Movie;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.time.LocalDateTime;

@Getter
@RequiredArgsConstructor
public class ScreeningWithMovieDto {
  private final long id;
  private final Movie movie;
  private final LocalDateTime beginning;
}
