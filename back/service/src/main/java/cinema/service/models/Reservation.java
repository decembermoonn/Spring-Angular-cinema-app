package cinema.service.models;

import com.sun.istack.NotNull;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@Table(name = "reservations")
public class Reservation {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private long id;

  @NotNull private int rowNumber;

  @NotNull private int columnNumber;

  @NotNull private LocalDateTime expirationDate;

  @NotNull private int reservationGroup;

  @ManyToOne(optional = false, fetch = FetchType.LAZY)
  private Screening screening;

  @ManyToOne(optional = false, fetch = FetchType.LAZY)
  private User user;

  @ManyToOne(optional = false, fetch = FetchType.LAZY)
  private Ticket ticket;
}
