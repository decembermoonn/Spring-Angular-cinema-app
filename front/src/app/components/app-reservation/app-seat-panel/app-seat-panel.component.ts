import { Component, EventEmitter, Input, Output } from '@angular/core';
import { SeatPanelInput } from '../../../models/seat-panel-input';
import { Reservation } from '../../../models/reservation';
import { RoomInfo } from '../../../models/room-info';

@Component({
  selector: 'app-seat-panel',
  templateUrl: './app-seat-panel.component.html',
  styleUrls: ['./app-seat-panel.component.scss'],
})
/***
 * @author github.com/abhinav-juneja, december_moon
 * @summary logic by me, html and css mostly by abhinav-juneja (I know, might not be well-coded...)
 */
export class AppSeatPanelComponent {
  @Input() set seatInfo(info: SeatPanelInput) {
    this.roomInfo = info.roomInfo;
    this.createSeatsMapFromReservedSeats(info.reservations);
  }

  @Output() confirm = new EventEmitter();
  @Output() actualReservationCount = new EventEmitter();

  roomInfo!: RoomInfo;
  seatsMap: ('e' | 'r' | 'c')[][] = [];
  actualReservationCountLocal = 0;

  executeSeatAction(row: number, col: number): void {
    const char = this.seatsMap[row][col];
    if (char === 'r') return;
    this.seatsMap[row][col] = this.getNewCharForSeatMap(char);
  }

  private getNewCharForSeatMap(char: 'e'|'c'): 'e'|'c' {
    let newChar = '';
    if(char === 'e') {
      newChar = 'c';
      this.actualReservationCountLocal++;
    } else {
      newChar = 'e';
      this.actualReservationCountLocal--;
    }
    this.actualReservationCount.next(this.actualReservationCountLocal);
    return newChar as 'e'|'c';
  }

  getCheckedSeatsAsReservations(): Reservation[] {
    const arr: Reservation[] = [];
    this.seatsMap.forEach((row, rowIndex) => row.forEach((col, colIndex) => {
      if(col === 'c') arr.push({rowNumber:rowIndex + 1, columnNumber:colIndex + 1});
    }));
    return arr;
  }

  isSeatChecked(row: number, col: number): boolean {
    return this.seatsMap[row][col] === 'c';
  }

  isSeatReserved(row: number, col: number): boolean {
    return this.seatsMap[row][col] === 'r';
  }

  private createSeatsMapFromReservedSeats(reservations: Reservation[]): void {
    const newSeatMap = new Array(this.roomInfo.rows).fill([]).map(() => new Array(this.roomInfo.columns).fill('e'));
    reservations.forEach((reservation) => {
      newSeatMap[reservation.rowNumber][reservation.columnNumber] = 'r';
    });
    this.seatsMap = newSeatMap;
  }
}
