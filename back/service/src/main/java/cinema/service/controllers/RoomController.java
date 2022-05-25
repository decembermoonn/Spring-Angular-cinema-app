package cinema.service.controllers;

import cinema.service.models.dtos.RoomDto;
import cinema.service.services.RoomService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
@RequiredArgsConstructor
public class RoomController {
  private final RoomService roomService;

  @GetMapping("/rooms")
  public ResponseEntity<RoomDto> getRoomByScreeningId(@RequestParam long screeningId) {
    return roomService.getRoomByScreeningId(screeningId);
  }
}
