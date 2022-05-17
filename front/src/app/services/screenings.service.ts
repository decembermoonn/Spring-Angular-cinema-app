import {Injectable} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {environment} from '@environments/environment';
import {Observable} from 'rxjs';
import {Screening} from '../models/screening';

@Injectable({providedIn: 'root'})
export class ScreeningsService {
  cinemaApiUrl: string;

  constructor(private http: HttpClient) {
    this.cinemaApiUrl = environment.cinemaApiUrl;
  }

  getLastScreeningDateAsIsoString(): Observable<string> {
    return this.http.get(`${this.cinemaApiUrl}/screenings/last`) as Observable<string>;
  }

  getScreeningsByIsoDateString(isoDate: string): Observable<Screening[]> {
    return this.http.get(`${this.cinemaApiUrl}/screenings`, {
      params: {
        date: isoDate,
      },
    }) as Observable<Screening[]>;
  }
}
