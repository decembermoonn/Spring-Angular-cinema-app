package cinema.service.controllers;

import cinema.service.models.Room;
import cinema.service.repositories.RoomRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@CrossOrigin
@RestController
@RequiredArgsConstructor
public class RoomController {
    private final RoomRepository roomRepository;

    @GetMapping("/room/{id}")
    public ResponseEntity<Room> getRoomById(@PathVariable long id) {
        Optional<Room> roomOptional = roomRepository.findById(id);
        if(roomOptional.isEmpty()) return ResponseEntity.notFound().build();
        return ResponseEntity.ok(roomOptional.get());
    }
}
