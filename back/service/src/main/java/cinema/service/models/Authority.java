package cinema.service.models;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Getter
@Setter
@Table(name = "authorities")
public class Authority {
    @Id
    private String username;
    private String authority;
}
