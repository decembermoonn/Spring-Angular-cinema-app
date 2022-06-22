package cinema.service.models;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "movies")
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
