package cinema.service.models.dtos;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Min;

@Builder
@Getter
@Setter
public class ReservedSeatDto {
  @Min(value = 1, message = "Row cannot be smaller than 1")
  int rowNumber;
  @Min(value = 1, message = "Column cannot be smaller than 1")
  int columnNumber;
}
