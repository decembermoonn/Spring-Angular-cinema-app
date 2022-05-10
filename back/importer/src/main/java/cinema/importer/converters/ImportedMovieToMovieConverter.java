package cinema.importer.converters;

import cinema.importer.models.ImportedMovie;
import cinema.importer.models.Movie;

public class ImportedMovieToMovieConverter {

  public static Movie convert(ImportedMovie importedMovie) {
    return Movie.builder()
        .crew(importedMovie.getCrew())
        .imageUrl(importedMovie.getImageUrl())
        .imDbRatingCount(importedMovie.getImDbRatingCount())
        .imDbRating(importedMovie.getImDbRating())
        .title(importedMovie.getTitle())
        .year(importedMovie.getYear())
        .build();
  }
}
