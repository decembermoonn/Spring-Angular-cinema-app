export interface Reservation {
  rowNumber: number;
  columnNumber: number;
}

export interface ReservationWithDetailsForPanel {
  imageUrl: string;
  title: string;
  beginning: string;
  reservationGroup: number
  reservedSeatDtoList: Reservation[]
}
