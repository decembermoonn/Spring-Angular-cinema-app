package cinema.service.services;

import cinema.service.models.Room;
import cinema.service.models.Screening;
import cinema.service.models.dtos.RoomDto;
import cinema.service.repositories.ScreeningRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class RoomService {
  private final ScreeningRepository screeningRepository;

  public ResponseEntity<RoomDto> getRoomByScreeningId(long screeningId) {
    Optional<Screening> screeningOptional = screeningRepository.findById(screeningId);
    if (screeningOptional.isEmpty()) return ResponseEntity.notFound().build();
    Screening screening = screeningOptional.get();
    Room room = screening.getRoom();
    return ResponseEntity.ok(mapToRoomDto(room));
  }

  private RoomDto mapToRoomDto(Room room) {
    return RoomDto.builder().rows(room.getRows()).columns(room.getColumns()).build();
  }
}
