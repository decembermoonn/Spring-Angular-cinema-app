package cinema.importer.models;

import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name="Movies")
public class Movie {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    long id;
    String title;
    int year;
    int minutesLength;
    String imageUrl;
    String crew;
    double imDbRating;
    int imDbRatingCount;
}
