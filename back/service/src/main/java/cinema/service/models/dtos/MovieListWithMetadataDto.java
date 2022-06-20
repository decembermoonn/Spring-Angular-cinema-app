package cinema.service.models.dtos;

import cinema.service.models.Movie;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.util.List;

@RequiredArgsConstructor
public class MovieListWithMetadataDto {
    final public List<Movie> movieList;
    final public long totalPages;
}
