package cinema.service.converters;

import cinema.service.models.dtos.ReservedSeatDto;
import cinema.service.rabbitmq.ReservationChange;

public class ReservedSeatDtoToReservationChangeSeat {
    private ReservedSeatDtoToReservationChangeSeat() {}

    public static ReservationChange.Seat convert(ReservedSeatDto reservedSeatDto) {
        return new ReservationChange.Seat(reservedSeatDto.getRowNumber(), reservedSeatDto.getColumnNumber());
    }
}
