package cinema.service.repositories;

import cinema.service.models.Screening;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface ScreeningRepository extends JpaRepository<Screening, Long> {
  @Query(
      "select s from Screening s left join fetch s.movie "
          + "where s.beginning >= :dayBeginning and s.beginning <= :dayEnding")
  List<Screening> findAllWithinTime(LocalDateTime dayBeginning, LocalDateTime dayEnding);

  @Query("select max(s.beginning) from Screening s")
  LocalDateTime findLastScreeningDate();
}
