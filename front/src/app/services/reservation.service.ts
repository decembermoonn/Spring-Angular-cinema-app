import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { environment } from '@environments/environment';
import {forkJoin, map, Observable} from 'rxjs';
import { Reservation } from '../models/reservation';
import { RoomInfo } from '../models/room-info';
import {SeatPanelInput} from "../models/seat-panel-input";
import {Ticket} from "../models/ticket";

@Injectable({ providedIn: 'root' })
export class ReservationService {
  cinemaApiUrl: string;

  constructor(private http: HttpClient) {
    this.cinemaApiUrl = environment.cinemaApiUrl;
  }

  getTicketTypes(): Observable<Ticket[]> {
    return this.http.get(`${this.cinemaApiUrl}/tickets`) as Observable<Ticket[]>;
  }

  getReservationsForScreening(screeningId: number): Observable<Reservation[]> {
    return this.http.get(`${this.cinemaApiUrl}/reservations`, {
      params: {
        screeningId,
      },
    }) as Observable<Reservation[]>;
  }

  getRoomInfoForScreening(screeningId: number): Observable<RoomInfo> {
    return this.http.get(`${this.cinemaApiUrl}/rooms`, {
      params: {
        screeningId,
      },
    }) as Observable<RoomInfo>;
  }

  getDataForSeatPanel(screeningId: number): Observable<SeatPanelInput> {
    return forkJoin([
      this.getRoomInfoForScreening(screeningId),
      this.getReservationsForScreening(screeningId),
    ]).pipe(
      map((data) => {
        return {
          roomInfo: data[0] as RoomInfo,
          reservations: data[1] as Reservation[],
        };
      })
    );
  }
}
