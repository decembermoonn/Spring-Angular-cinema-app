package cinema.service.models;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity
@Getter
@Setter
@Table(name = "reservation_groups")
public class ReservationGroup {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private long id;

  @OneToMany(orphanRemoval = true)
  List<Reservation> reservationList;
}
