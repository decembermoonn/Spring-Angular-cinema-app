package cinema.importer.startup;

import cinema.importer.exceptions.ImporterException;
import cinema.importer.models.Screening;
import cinema.importer.repositories.ScreeningRepository;
import cinema.importer.services.RandomScreeningsGenerator;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Slf4j
@RequiredArgsConstructor
public class GeneratorStartupListener {
  private final RandomScreeningsGenerator randomScreeningsGenerator;
  private final ScreeningRepository screeningRepository;

  @EventListener
  public void handleContextRefresh(ContextRefreshedEvent event) {
    if (screeningRepository.count() == 0) {
      List<Screening> screenings = randomScreeningsGenerator.generate();
      screeningRepository.saveAll(screenings);
    } else {
      log.warn("GENERATOR - Generation aborted because Screenings table is not empty.");
    }
  }
}
