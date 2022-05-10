package cinema.importer.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ImportedMovie {
  String title;
  int year;

  @JsonProperty("image")
  String imageUrl;

  String crew;
  double imDbRating;
  double imDbRatingCount;
}
