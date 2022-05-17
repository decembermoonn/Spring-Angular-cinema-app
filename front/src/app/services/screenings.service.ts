import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { environment } from '@environments/environment';
import { map, Observable } from 'rxjs';
import { MovieWithScreenings, ScreeningFromApi } from '../models/screening';

@Injectable({ providedIn: 'root' })
export class ScreeningsService {
  cinemaApiUrl: string;

  constructor(private http: HttpClient) {
    this.cinemaApiUrl = environment.cinemaApiUrl;
  }

  getLastScreeningDateAsIsoString(): Observable<string> {
    return this.http.get(`${this.cinemaApiUrl}/screenings/last`) as Observable<string>;
  }

  getScreeningsByIsoDateString(isoDate: string): Observable<MovieWithScreenings[]> {
    return this.http
      .get(`${this.cinemaApiUrl}/screenings`, {
        params: {
          date: isoDate,
        },
      })
      .pipe(
        map((screenings) => {
          return this.extractMoviesWithScreeningsFromDataReturnedByApi(screenings as ScreeningFromApi[]);
        })
      );
  }

  //TODO - unit test this method
  private extractMoviesWithScreeningsFromDataReturnedByApi(
    screeningsFromApi: ScreeningFromApi[]
  ): MovieWithScreenings[] {
    const movieWithScreenings: MovieWithScreenings[] = [];
    screeningsFromApi.forEach((screening) => {
      const entry = movieWithScreenings.find((entry) => entry.movie.title === screening.movie.title);
      if (entry) {
        entry.screenings.push({
          id: screening.id,
          beginning: screening.beginning,
        });
      } else {
        movieWithScreenings.push({
          movie: screening.movie,
          screenings: [
            {
              id: screening.id,
              beginning: screening.beginning,
            },
          ],
        });
      }
    });
    return movieWithScreenings;
  }
}
