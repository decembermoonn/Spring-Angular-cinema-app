import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { AppLoginComponent } from './components/app-login/app-login.component';
import {AppWelcomeComponent} from "./components/app-welcome/app-welcome.component";

const routes: Routes = [
  {
    path: 'login',
    component: AppLoginComponent,
  },
  {
    path: '**',
    component:AppWelcomeComponent,
  }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule],
})
export class AppRoutingModule {}
