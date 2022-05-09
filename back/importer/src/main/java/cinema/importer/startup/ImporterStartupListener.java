package cinema.importer.startup;

import cinema.importer.exception.ImporterException;
import cinema.importer.models.ImportedMovie;
import cinema.importer.services.ImdbMoviesDownloadService;
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

  @EventListener
  public void handleContextRefresh(ContextRefreshedEvent event) {
    try {
      log.info("IMPORTER - Fetching data started.");
      List<ImportedMovie> movies = imdbMoviesDownloadService.fetchData();
      log.info("IMPORTER - Fetching data successful.");

      //TODO - publish to database. Should I use liquibase?
    } catch (ImporterException e) {
      e.printStackTrace();
    }
  }
}
