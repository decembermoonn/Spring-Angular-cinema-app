import {Component, EventEmitter, Input, OnDestroy, Output} from '@angular/core';
import { ReservationWithDetailsForPanel } from '../../../models/reservation';
import { ReservationService } from '../../../services/reservation.service';

@Component({
  selector: 'app-user-reservation',
  templateUrl: './app-user-reservation.component.html',
  styleUrls: ['./app-user-reservation.component.scss'],
})
export class AppUserReservationComponent implements OnDestroy {
  @Output('change') change = new EventEmitter<void>();
  @Input('reservation') reservation!: ReservationWithDetailsForPanel;
  userAskedToConfirm = false;
  timeOutId?: number;

  constructor(private service: ReservationService) {}

  ngOnDestroy(): void {
    clearTimeout(this.timeOutId);
  }

  tryCancelReservation(reservationGroup: number): void {
    if (!this.userAskedToConfirm) {
      this.userAskedToConfirm = true;
      this.timeOutId = setTimeout(() => {
        this.userAskedToConfirm = false;
      }, 3000);
    } else {
      this.userAskedToConfirm = false;
      clearTimeout(this.timeOutId);
      this.cancelReservation(reservationGroup)
    }
  }

  private cancelReservation(reservationGroup: number): void {
    this.service.deleteReservationByGroupId(reservationGroup).subscribe({
      next: () => this.change.next()
    })
  }
}
