import { Component, OnDestroy } from '@angular/core';
import { ReservationService } from '../../services/reservation.service';
import { ReservationWithDetailsForPanel } from '../../models/reservation';

@Component({
  selector: 'app-user-reservation-panel',
  templateUrl: './app-user-reservation-panel.component.html',
  styleUrls: ['./app-user-reservation-panel.component.scss'],
})
export class AppUserReservationPanelComponent {
  reservationData: ReservationWithDetailsForPanel[] = [];

  constructor(private service: ReservationService) {
    this.refreshPanel();
  }

  refreshPanel(): void {
    this.service.getReservationsForAuthenticatedUser().subscribe({
      next: (data) => {
        this.reservationData = data;
      },
      error: () => console.log('Error! Unauthenticated'),
    });
  }
}
