package cinema.importer.models;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.time.LocalDateTime;

@Setter
@Getter
@Entity
@ToString
@Table(name = "Screenings")
public class Screening {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private long id;

  LocalDateTime beginning;

  @ManyToOne
  Movie movie;

  @ManyToOne
  Room room;
}
