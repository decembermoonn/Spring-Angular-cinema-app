package cinema.service.models.dtos;

import cinema.service.models.Movie;
import lombok.RequiredArgsConstructor;

import java.util.List;

@RequiredArgsConstructor
public class MovieListWithMetadataDto {
    public final List<Movie> movieList;
    public final long totalPages;
}
