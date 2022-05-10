package cinema.importer.startup;

import cinema.importer.converters.ImportedMovieToMovieConverter;
import cinema.importer.exceptions.ImporterException;
import cinema.importer.models.ImportedMovie;
import cinema.importer.models.Movie;
import cinema.importer.services.ImdbMoviesDownloadService;
import cinema.importer.services.MovieService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Slf4j
@RequiredArgsConstructor
public class ImporterStartupListener {

  private final ImdbMoviesDownloadService imdbMoviesDownloadService;
  private final MovieService movieService;

  @EventListener
  public void handleContextRefresh(ContextRefreshedEvent event) throws ImporterException {
    if(!movieService.isMovieTableIsEmpty())
      throw new ImporterException("IMPORTER - Importing failed because Movies table is not empty.");

    log.info("IMPORTER - Fetching data started.");
    List<ImportedMovie> movies = imdbMoviesDownloadService.fetchData();
    log.info("IMPORTER - Fetching data successful.");

    log.info("IMPORTER - Population of DB with movies started.");
    List<Movie> convertedMovies =
            movies.stream().map(ImportedMovieToMovieConverter::convert).toList();
    movieService.populateDatabaseWithMovies(convertedMovies);
    log.info("IMPORTER - Population of DB with movies successful.");
  }
}
