package cinema.service.models;

import com.sun.istack.NotNull;
import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "Movies")
public class Movie {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  long id;

  @NotNull String title;
  @NotNull int minutesLength;

  int year;
  String imageUrl;
  String crew;
  double imDbRating;
  int imDbRatingCount;
}
