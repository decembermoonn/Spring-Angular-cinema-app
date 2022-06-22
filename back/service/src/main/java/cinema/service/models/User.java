package cinema.service.models;

import javax.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Getter
@Setter
@Table(name = "users")
public class User {
  @Id private String username;
  @NotNull private String password;
  @NotNull private boolean enabled;
}
