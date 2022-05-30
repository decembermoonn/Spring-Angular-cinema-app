import {Component, ViewChild} from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { ReservationService } from '../../services/reservation.service';
import { SeatPanelInput } from '../../models/seat-panel-input';
import {AppSeatPanelComponent} from "./app-seat-panel/app-seat-panel.component";

@Component({
  selector: 'app-reservation',
  templateUrl: './app-reservation.component.html',
  styleUrls: ['./app-reservation.component.scss'],
})
export class AppReservationComponent {
  @ViewChild('panel') panelRef!: AppSeatPanelComponent;
  seatPanelInput?: SeatPanelInput;
  ticketTypes: string[] = [];

  constructor(private route: ActivatedRoute, private router: Router, private reservationService: ReservationService) {
    console.log(router.getCurrentNavigation()?.extras.state);
    const screeningId = route.snapshot.paramMap.get('id');
    if (screeningId) {
      const intScreeningId = parseInt(screeningId);

      reservationService.getDataForSeatPanel(intScreeningId).subscribe((data) => {
        this.seatPanelInput = data;
      });

      reservationService.getTicketTypes().subscribe((data) => (this.ticketTypes = data));
    }
  }

  setActualReservationCount(count: number): void {
    console.log(count);
  }

  test(): void {
    console.log(this.panelRef.getCheckedSeatsAsReservations());
  }
}
