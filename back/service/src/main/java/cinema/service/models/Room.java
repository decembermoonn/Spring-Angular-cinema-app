package cinema.service.models;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Setter
@Getter
@Entity
public class Room {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private long id;

  private int rows;
  private int columns;
}
