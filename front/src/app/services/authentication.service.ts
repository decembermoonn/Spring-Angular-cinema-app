import { Injectable } from '@angular/core';
import { OptionalUser } from '../models/user';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { environment } from '@environments/environment';
import { BehaviorSubject, map, Observable } from 'rxjs';

@Injectable({ providedIn: 'root' })
export class AuthenticationService {
  private currentUserSubject: BehaviorSubject<OptionalUser>;
  public currentUser: Observable<OptionalUser>;
  public get currentUserValue(): OptionalUser {
    return this.currentUserSubject.value;
  }

  constructor(private http: HttpClient) {
    const localStorageCurrentUser = localStorage.getItem('currentUser');
    this.currentUserSubject = new BehaviorSubject<OptionalUser>(
      localStorageCurrentUser ? JSON.parse(localStorageCurrentUser) : undefined
    );
    this.currentUser = this.currentUserSubject.asObservable();
  }

  login(username: string, password: string): Observable<HttpResponse<unknown>> {
    const { cinemaApiUrl } = environment;
    return this.http
      .post(
        `${cinemaApiUrl}/login`,
        { username, password },
        {
          observe: 'response',
        }
      )
      .pipe(
        map((successfulResponse) => {
          const currentUserObject = {
            username,
            authData: window.btoa(username + ':' + password),
          };
          // I think that storing authData (base64 encoded string) in localStorage might not be the best idea...
          // But I'm going to leave it as is, might change it later...
          localStorage.setItem('currentUser', JSON.stringify(currentUserObject));
          this.currentUserSubject.next(currentUserObject);
          return successfulResponse;
        })
      );
  }

  logout(): void {
    localStorage.removeItem('currentUser');
    this.currentUserSubject.next(undefined);
  }
}
