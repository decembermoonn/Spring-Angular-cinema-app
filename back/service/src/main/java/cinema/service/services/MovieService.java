package cinema.service.services;

import cinema.service.models.Movie;
import cinema.service.repositories.MovieRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class MovieService {

  private final MovieRepository movieRepository;

  @SuppressWarnings("FieldCanBeLocal")
  private final int PAGE_SIZE = 10;

  public List<Movie> getMovies(
      Sort.Direction direction, String sortProperty, String query, int page) {
    PageRequest pageRequest = PageRequest.of(page, PAGE_SIZE);
    if (direction != null && sortProperty != null) {
      pageRequest = pageRequest.withSort(direction, sortProperty);
    }
    if (query != null) {
      return movieRepository.findAllMoviesByTitleQuery(pageRequest, query);
    }
    return movieRepository.findAllMovies(pageRequest);
  }

  public Optional<Movie> getMovie(Long movieId) {
    return movieRepository.findById(movieId);
  }
}
