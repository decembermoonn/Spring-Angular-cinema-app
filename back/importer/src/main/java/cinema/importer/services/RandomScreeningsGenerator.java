package cinema.importer.services;

import cinema.importer.models.Movie;
import cinema.importer.models.Room;
import cinema.importer.models.Screening;
import cinema.importer.repositories.MovieRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Component
@RequiredArgsConstructor
public class RandomScreeningsGenerator {
  private final int ROOM_MIN_ID = 1;
  private final int ROOM_MAX_ID = 3;

  private final MovieRepository movieRepository;

  public List<Screening> generate() {
    Random rnd = new Random();
    List<Screening> screenings = new ArrayList<>();
    List<Movie> movies = movieRepository.findAll();

    for (int dayIndex = 0; dayIndex < 7; dayIndex++) {
      int additionalMinutes = 0;
      int screeningsThatDay = rnd.nextInt(2, 7);
      for (int screeningIndex = 0; screeningIndex < screeningsThatDay; screeningIndex++) {
        Movie movie = movies.get(rnd.nextInt(0, movies.size()));
        Room room = Room.builder().id(rnd.nextInt(ROOM_MIN_ID, ROOM_MAX_ID + 1)).build();
        LocalDateTime beginning =
            LocalDateTime.of(LocalDate.now(), LocalTime.MIDNIGHT).plusDays(dayIndex).plusHours(8);

        Screening screening = new Screening();
        screening.setMovie(movie);
        screening.setRoom(room);
        screening.setBeginning(beginning.plusMinutes(additionalMinutes));
        additionalMinutes += (((movie.getMinutesLength() + 5) / 10) + 1) * 10;
        screenings.add(screening);
      }
    }
    return screenings;
  }
}
