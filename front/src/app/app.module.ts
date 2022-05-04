import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { AppHeaderComponent } from './components/app-header/app-header.component';
import { FontAwesomeModule } from '@fortawesome/angular-fontawesome';
import { AppUserMenuComponent } from './components/app-header/app-user-menu/app-user-menu.component';
import { AppLoginComponent } from './components/app-login/app-login.component';
import { ReactiveFormsModule } from '@angular/forms';
import { HTTP_INTERCEPTORS } from '@angular/common/http';
import { AuthenticationInterceptor } from './interceptors/authentication.interceptor';

@NgModule({
  declarations: [AppComponent, AppHeaderComponent, AppUserMenuComponent, AppLoginComponent],
  imports: [BrowserModule, AppRoutingModule, FontAwesomeModule, ReactiveFormsModule],
  providers: [{ provide: HTTP_INTERCEPTORS, useClass: AuthenticationInterceptor, multi: true }],
  bootstrap: [AppComponent],
})
export class AppModule {}
