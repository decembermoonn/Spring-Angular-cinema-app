import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { AppLoginComponent } from './components/app-login/app-login.component';
import { AppWelcomeComponent } from './components/app-welcome/app-welcome.component';
import { AppScreeningsComponent } from './components/app-screenings/app-screenings.component';
import { AppReservationComponent } from './components/app-reservation/app-reservation.component';
import { AuthenticationGuard } from './guards/authentication.guard';
import {
  AppUserReservationPanelComponent
} from "./components/app-user-reservation-panel/app-user-reservation-panel.component";

const routes: Routes = [
  {
    path: 'login',
    component: AppLoginComponent,
  },
  {
    path: 'screenings',
    component: AppScreeningsComponent,
  },
  {
    path: 'reservation/:id',
    component: AppReservationComponent,
    canActivate: [AuthenticationGuard],
  },
  {
    path: 'reservations',
    component: AppUserReservationPanelComponent,
    canActivate: [AuthenticationGuard],
  },
  {
    path: '**',
    component: AppWelcomeComponent,
  },
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule],
})
export class AppRoutingModule {}
