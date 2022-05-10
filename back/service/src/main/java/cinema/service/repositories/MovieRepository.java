package cinema.service.repositories;

import cinema.service.models.Movie;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MovieRepository extends JpaRepository<Movie, Long> {
  @Query("select m from Movie m")
  List<Movie> findAllMovies(Pageable page);

  @Query("select m from Movie m where lower(m.title) like concat('%',lower(:query),'%')")
  List<Movie> findAllMoviesByTitleQuery(Pageable page, String query);
}
