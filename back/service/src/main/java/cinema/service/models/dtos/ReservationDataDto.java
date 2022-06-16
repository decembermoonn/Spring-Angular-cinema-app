package cinema.service.models.dtos;

import lombok.Getter;
import lombok.Setter;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import java.util.List;

@Getter
@Setter
public class ReservationDataDto {
    long screeningId;
    @NotEmpty(message = "Reserved seat list must not be empty.")
    @Valid
    List<ReservedSeatDto> reservedSeatDtoList;
    @NotEmpty(message = "Ticket ids list must not be empty.")
    long[] ticketIds;
}
