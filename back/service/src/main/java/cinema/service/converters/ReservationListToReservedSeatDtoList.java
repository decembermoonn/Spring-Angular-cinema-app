package cinema.service.converters;

import cinema.service.models.Reservation;
import cinema.service.models.dtos.ReservedSeatDto;

import java.util.List;
import java.util.stream.Collectors;

public class ReservationListToReservedSeatDtoList {
  public static List<ReservedSeatDto> convert(List<Reservation> reservations) {
    return reservations.stream()
        .map(
            r ->
                ReservedSeatDto.builder()
                    .rowNumber(r.getRowNumber())
                    .columnNumber(r.getColumnNumber())
                    .build())
        .collect(Collectors.toList());
  }
}
