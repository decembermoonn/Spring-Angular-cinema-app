package cinema.importer.services;

import cinema.importer.models.Movie;
import cinema.importer.repositories.MovieRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MovieService {
    private final MovieRepository movieRepository;

    public boolean isMovieTableIsEmpty() {
        return movieRepository.count() == 0;
    }

    public void populateDatabaseWithMovies(List<Movie> movies) {
        movieRepository.saveAll(movies);
    }
}
