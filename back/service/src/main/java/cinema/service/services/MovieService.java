package cinema.service.services;

import cinema.service.models.Movie;
import cinema.service.models.dtos.MovieListWithMetadataDto;
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

  public MovieListWithMetadataDto getMovies(
      Sort.Direction direction, String sortProperty, String query, int page) {
    PageRequest pageRequest = PageRequest.of(page, PAGE_SIZE);
    if (direction != null && sortProperty != null) {
      pageRequest = pageRequest.withSort(direction, sortProperty);
    }
    List<Movie> movies;
    if (query != null) {
      movies = movieRepository.findAllMoviesByTitleQuery(pageRequest, query);
    } else {
      movies = movieRepository.findAllMovies(pageRequest);
    }
    long pages = (long) Math.ceil((double)movieRepository.count() / PAGE_SIZE);
    return new MovieListWithMetadataDto(movies, pages);
  }

  public Optional<Movie> getMovie(Long movieId) {
    return movieRepository.findById(movieId);
  }
}
