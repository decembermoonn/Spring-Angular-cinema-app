import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { AppHeaderComponent } from './components/app-header/app-header.component';
import { FontAwesomeModule } from '@fortawesome/angular-fontawesome';
import { AppUserMenuComponent } from './components/app-header/app-user-menu/app-user-menu.component';
import { AppLoginComponent } from './components/app-login/app-login.component';
import { ReactiveFormsModule } from '@angular/forms';
import { HTTP_INTERCEPTORS, HttpClientModule } from '@angular/common/http';
import { AuthenticationInterceptor } from './interceptors/authentication.interceptor';
import { AppWelcomeComponent } from './components/app-welcome/app-welcome.component';
import { AppMovieSettingsComponent } from './components/app-welcome/app-movie-settings/app-movie-settings.component';
import { AppMovieComponent } from './components/app-movie/app-movie.component';
import { AppScreeningsComponent } from './components/app-screenings/app-screenings.component';
import { AppScreeningsSettingsComponent } from './components/app-screenings/app-screenings-settings/app-screenings-settings.component';
import { AppReservationComponent } from './components/app-reservation/app-reservation.component';
import { AppSeatPanelComponent } from './components/app-reservation/app-seat-panel/app-seat-panel.component';
import { AppArrayRangePipe } from './pipes/app-array-range.pipe';

@NgModule({
  declarations: [AppComponent, AppHeaderComponent, AppUserMenuComponent, AppLoginComponent, AppWelcomeComponent, AppMovieSettingsComponent, AppMovieComponent, AppScreeningsComponent, AppScreeningsSettingsComponent, AppReservationComponent, AppSeatPanelComponent, AppArrayRangePipe],
  imports: [BrowserModule, AppRoutingModule, FontAwesomeModule, ReactiveFormsModule, HttpClientModule],
  providers: [{ provide: HTTP_INTERCEPTORS, useClass: AuthenticationInterceptor, multi: true }],
  bootstrap: [AppComponent],
})
export class AppModule {}
