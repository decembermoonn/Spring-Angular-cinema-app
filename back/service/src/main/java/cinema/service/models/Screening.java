package cinema.service.models;

import javax.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;

@Setter
@Getter
@Entity
@Table(name = "screenings")
public class Screening {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private long id;

  @NotNull LocalDateTime beginning;

  @ManyToOne Movie movie;

  @ManyToOne(fetch = FetchType.LAZY)
  Room room;
}
