package cinema.service.repositories;

import cinema.service.models.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ReservationRepository extends JpaRepository<Reservation, Long> {
  List<Reservation> findAllByScreeningId(long screeningId);

  @Query("select max(r.reservationGroup) from Reservation r")
  Optional<Integer> getMaxReservationGroup();

  @Query("select r from Reservation r join fetch r.screening as s join fetch s.movie where r.user.username = :username")
  List<Reservation> getJoinedReservationsForUser(String username);

  void deleteAllByReservationGroup(int reservationGroup);
}
