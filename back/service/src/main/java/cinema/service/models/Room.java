package cinema.service.models;

import com.sun.istack.NotNull;
import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@Setter
@Table(name = "rooms")
public class Room {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private long id;

  @NotNull private int rows;

  @NotNull private int columns;
}
