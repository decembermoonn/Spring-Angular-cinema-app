package cinema.service.controllers;

import cinema.service.models.Movie;
import cinema.service.models.dtos.MovieListWithMetadataDto;
import cinema.service.services.MovieService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequiredArgsConstructor
@CrossOrigin
public class MovieController {

  private final MovieService movieService;

  @GetMapping("/movies/{movieId}")
  public ResponseEntity<Object> getMovie(@PathVariable Long movieId) {
    Optional<Movie> movieOptional = movieService.getMovie(movieId);
    if (movieOptional.isEmpty()) return ResponseEntity.notFound().build();
    return ResponseEntity.ok(movieOptional.get());
  }

  @GetMapping("/movies")
  public ResponseEntity<Object> getMovies(
      Sort.Direction direction,
      String sortProperty,
      String query,
      @RequestParam(required = false, defaultValue = "0") int page) {
    MovieListWithMetadataDto movieList = movieService.getMovies(direction, sortProperty, query, page);
    return ResponseEntity.ok(movieList);
  }
}
