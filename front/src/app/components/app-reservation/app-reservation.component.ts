import { Component, ViewChild } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { ReservationService } from '../../services/reservation.service';
import { SeatPanelInput } from '../../models/seat-panel-input';
import { AppSeatPanelComponent } from './app-seat-panel/app-seat-panel.component';
import { Ticket } from '../../models/ticket';
import { FormArray, FormBuilder, FormControl } from '@angular/forms';

@Component({
  selector: 'app-reservation',
  templateUrl: './app-reservation.component.html',
  styleUrls: ['./app-reservation.component.scss'],
})
export class AppReservationComponent {
  @ViewChild('panel') panelRef!: AppSeatPanelComponent;
  seatPanelInput?: SeatPanelInput;
  ticketData: Ticket[] = [];
  selectedSeats = 0;
  eachTicketTypeCountGroup = this.fb.group({
    eachTicketTypeCount: this.fb.array([]),
  });
  ticketCountMatchesSelectedSeatsCount = true;

  constructor(
    private route: ActivatedRoute,
    private router: Router,
    private reservationService: ReservationService,
    private fb: FormBuilder
  ) {
    console.log(router.getCurrentNavigation()?.extras.state);
    const screeningId = route.snapshot.paramMap.get('id');
    if (screeningId) {
      const intScreeningId = parseInt(screeningId);

      reservationService.getDataForSeatPanel(intScreeningId).subscribe((data) => {
        this.seatPanelInput = data;
      });

      reservationService.getTicketTypes().subscribe((data) => {
        this.eachTicketTypeCountGroup.setControl(
          'eachTicketTypeCount',
          new FormArray(data.map(() => new FormControl(0)))
        );
        this.ticketData = data;
      });
    }

    this.eachTicketTypeCountGroup.valueChanges.subscribe((vals) =>
      this.checkIfTicketCountMatchesSelectedSeatsCount(vals)
    );
  }

  get totalPrice(): number {
    const eachTicketTypeCount = this.eachTicketTypeCountGroup.get('eachTicketTypeCount')?.value as number[];
    return eachTicketTypeCount.map((value, index) => value * this.ticketData[index].price).reduce((l, r) => l + r);
  }

  setActualReservationCount(count: number): void {
    this.selectedSeats = count;
    this.checkIfTicketCountMatchesSelectedSeatsCount({
      eachTicketTypeCount: this.eachTicketTypeCountGroup.get('eachTicketTypeCount')?.value as number[],
    });
  }

  checkIfTicketCountMatchesSelectedSeatsCount(vals: { eachTicketTypeCount: number[] }): void {
    this.ticketCountMatchesSelectedSeatsCount = vals.eachTicketTypeCount.reduce((l, r) => l + r) === this.selectedSeats;
  }

  makeReservation(): void {
    console.log(this.panelRef.getCheckedSeatsAsReservations());
    console.log(this.ticketData);
    console.log(this.eachTicketTypeCountGroup.get('eachTicketTypeCount'));
  }
}
