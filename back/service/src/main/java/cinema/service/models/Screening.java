package cinema.service.models;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;

@Setter
@Getter
@Entity
@Table(name = "Screenings")
public class Screening {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private long id;

  LocalDateTime beginning;

  @ManyToOne
  Movie movie;

  @ManyToOne(fetch = FetchType.LAZY)
  Room room;
}


