import { Component } from '@angular/core';
import { ScreeningsService } from '../../services/screenings.service';
import * as moment from 'moment';
import { Screening } from '../../models/screening';

@Component({
  selector: 'app-screenings',
  templateUrl: './app-screenings.component.html',
  styleUrls: ['./app-screenings.component.scss'],
})
export class AppScreeningsComponent {
  screenings: Screening[] = [];

  constructor(private screeningService: ScreeningsService) {
    screeningService
      .getScreeningsByIsoDateString(moment().format('YYYY-MM-DD'))
      .subscribe((screenings) => (this.screenings = screenings));
  }

  handleScreeningDateChange(isoDateString: string): void {
    console.log(isoDateString);
  }
}
