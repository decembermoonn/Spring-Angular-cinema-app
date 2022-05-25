import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';

@Component({
  selector: 'app-reservation',
  templateUrl: './app-reservation.component.html',
  styleUrls: ['./app-reservation.component.scss'],
})
export class AppReservationComponent {
  constructor(private router: Router) {
    console.log(this.router.getCurrentNavigation()?.extras.state);
  }
}
