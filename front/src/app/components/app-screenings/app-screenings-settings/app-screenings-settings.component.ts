import { Component, EventEmitter, Output } from '@angular/core';
import * as moment from 'moment';
import { ScreeningsService } from '../../../services/screenings.service';
import { FormControl } from '@angular/forms';
import { Subscription } from 'rxjs';

@Component({
  selector: 'app-screenings-settings',
  templateUrl: './app-screenings-settings.component.html',
  styleUrls: ['./app-screenings-settings.component.scss'],
})
export class AppScreeningsSettingsComponent {
  @Output() onScreeningDateChange = new EventEmitter<string>();

  firstScreeningDate = moment().format('YYYY-MM-DD');
  lastScreeningDate = '';

  actualScreeningDate = new FormControl(this.firstScreeningDate);
  actualScreeningDateValueSub: Subscription;

  constructor(screeningsService: ScreeningsService) {
    screeningsService.getLastScreeningDateAsIsoString().subscribe((date) => (this.lastScreeningDate = date));
    this.actualScreeningDateValueSub = this.actualScreeningDate.valueChanges.subscribe((val) =>
      this.emitScreeningDateChange(val)
    );
  }

  emitScreeningDateChange(val: string): void {
    this.onScreeningDateChange.emit(val);
  }
}
