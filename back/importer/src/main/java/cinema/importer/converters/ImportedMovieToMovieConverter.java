package cinema.importer.converters;

import cinema.importer.models.ImportedMovie;
import cinema.importer.models.Movie;

import java.util.Random;

public class ImportedMovieToMovieConverter {

  public static Movie convert(ImportedMovie importedMovie) {
    return Movie.builder()
        .crew(importedMovie.getCrew())
        .imageUrl(importedMovie.getImageUrl())
        .imDbRatingCount(importedMovie.getImDbRatingCount())
        .minutesLength(new Random().nextInt(30, 150))
        .imDbRating(importedMovie.getImDbRating())
        .title(importedMovie.getTitle())
        .year(importedMovie.getYear())
        .build();
  }
}
