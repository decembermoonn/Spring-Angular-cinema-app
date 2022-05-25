package cinema.service.models.dtos;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Getter
@Setter
public class ReservedSeatDto {
  int rowNumber;
  int columnNumber;
}
