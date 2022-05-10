package cinema.importer.services;

import cinema.importer.exceptions.ImporterException;
import cinema.importer.models.ImportedMovie;
import cinema.importer.models.ImportedMovies;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ImdbMoviesDownloadService {
  @Value("${settings.api-base}")
  private String apiBase;

  @Value("${settings.api-uri}")
  private String apiUri;

  @Value("${settings.api-key}")
  private String apiKey;

  public List<ImportedMovie> fetchData() throws ImporterException {
    WebClient client = WebClient.create(apiBase);
    var req = client.get().uri(uriBuilder -> uriBuilder.path(apiUri + "/{key}").build(apiKey));
    var resp = req.retrieve().toEntity(ImportedMovies.class).block();
    ImportedMovies importedMovies;
    if (resp != null && (importedMovies = resp.getBody()) != null) {
      return importedMovies.getItems();
    } else {
      throw new ImporterException(
          "IMPORTER - Importing failed because of problems with fetching data.");
    }
  }
}
