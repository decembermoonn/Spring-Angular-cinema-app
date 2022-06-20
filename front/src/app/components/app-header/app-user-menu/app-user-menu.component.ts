import { Component, EventEmitter, Input, Output } from '@angular/core';
import { Router } from '@angular/router';
import { OptionalUser } from '../../../models/user';
import {AuthenticationService} from "../../../services/authentication.service";

@Component({
  selector: 'app-user-menu',
  templateUrl: './app-user-menu.component.html',
  styleUrls: ['./app-user-menu.component.scss'],
})
export class AppUserMenuComponent {
  @Input('expanded') isRowExapnded = false;
  @Input('user') localUser: OptionalUser;

  @Output() visibilityChange = new EventEmitter<boolean>();

  constructor(private router: Router, private auth: AuthenticationService) {}

  // redirectToLoginPage(): void {
  //   this.isRowExapnded = false;
  //   this.visibilityChange.next(false);
  //   this.router.navigate(['login']);
  // }

  logout(): void {
    this.auth.logout();
  }
}
