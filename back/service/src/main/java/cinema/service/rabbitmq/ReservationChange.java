package cinema.service.rabbitmq;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.io.Serializable;
import java.util.List;

@Getter
@RequiredArgsConstructor
public class ReservationChange implements Serializable {
    final long screeningId;
    final List<Seat> changedSeats;
    final boolean isItReservation;

    @Getter
    @RequiredArgsConstructor
    public static class Seat {
        final int rowNumber;
        final int columnNumber;
    }
}
