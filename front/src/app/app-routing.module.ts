import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { AppLoginComponent } from './components/app-login/app-login.component';
import { AppWelcomeComponent } from './components/app-welcome/app-welcome.component';
import { AppScreeningsComponent } from './components/app-screenings/app-screenings.component';

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
    path: '**',
    component: AppWelcomeComponent,
  },
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule],
})
export class AppRoutingModule {}
