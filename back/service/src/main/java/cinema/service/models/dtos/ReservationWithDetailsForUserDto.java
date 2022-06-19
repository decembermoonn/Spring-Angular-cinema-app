package cinema.service.models.dtos;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
public class ReservationWithDetailsForUserDto {
    String imageUrl;
    String title;
    LocalDateTime beginning;
    int reservationGroup;
    List<ReservedSeatDto> reservedSeatDtoList;
}
