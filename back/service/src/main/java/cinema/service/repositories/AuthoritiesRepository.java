package cinema.service.repositories;

import cinema.service.models.Authority;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AuthoritiesRepository extends JpaRepository<Authority, String> {
}
