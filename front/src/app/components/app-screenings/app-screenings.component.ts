import { Component } from '@angular/core';
import { ScreeningsService } from '../../services/screenings.service';
import * as moment from 'moment';
import { MovieWithScreenings } from '../../models/screening';
import {AuthenticationService} from "../../services/authentication.service";

@Component({
  selector: 'app-screenings',
  templateUrl: './app-screenings.component.html',
  styleUrls: ['./app-screenings.component.scss'],
})
export class AppScreeningsComponent {
  moviesWithScreenings: MovieWithScreenings[] = [];

  constructor(private screeningService: ScreeningsService, private authService: AuthenticationService) {
    this.fetchMoviesByIsoDateString(moment().format('YYYY-MM-DD'));
  }

  isGuest(): boolean {
    return !this.authService.currentUserValue;
  }

  handleScreeningDateChange(isoDateString: string): void {
    this.fetchMoviesByIsoDateString(isoDateString);
  }

  fetchMoviesByIsoDateString(isoDateString: string): void {
    this.screeningService
      .getScreeningsByIsoDateString(isoDateString)
      .subscribe((moviesWithScreenings) => (this.moviesWithScreenings = moviesWithScreenings));
  }

  getOnlyHourAndMinutes(screening: MovieWithScreenings['screenings'][number]): string {
    return moment(screening.beginning).format('H:mm');
  }
}
