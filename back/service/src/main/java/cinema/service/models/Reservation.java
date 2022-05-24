package cinema.service.models;

import com.sun.istack.NotNull;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
public class Reservation {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private long id;

  @NotNull private int rowNumber;

  @NotNull private int columnNumber;

  @NotNull private LocalDateTime expirationDate;

  @ManyToOne(optional = false)
  private Screening screening;

  @ManyToOne(optional = false)
  private User user;

  @ManyToOne(optional = false)
  private Ticket ticket;
}
