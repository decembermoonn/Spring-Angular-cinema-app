import { HttpEvent, HttpHandler, HttpInterceptor, HttpRequest } from '@angular/common/http';
import { Observable } from 'rxjs';
import { AuthenticationService } from '../services/authentication.service';
import { Injectable } from '@angular/core';

@Injectable()
export class AuthenticationInterceptor implements HttpInterceptor {
  constructor(private authService: AuthenticationService) {}

  /**
   * @summaty add authorization header with basic auth credentials if available
   * @param req
   * @param next
   */
  intercept(req: HttpRequest<unknown>, next: HttpHandler): Observable<HttpEvent<unknown>> {
    const currentUser = this.authService.currentUserValue;
    if (currentUser && currentUser.authData) {
      req = req.clone({
        setHeaders: {
          Authentication: `Basic ${currentUser.authData}`,
        },
      });
    }
    return next.handle(req);
  }
}
