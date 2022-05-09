package cinema.importer.models;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class ImportedMovies {
  List<ImportedMovie> items;
}
