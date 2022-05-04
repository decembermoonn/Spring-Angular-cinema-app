import { Component, EventEmitter, Input, Output } from '@angular/core';
import { Router } from '@angular/router';

@Component({
  selector: 'app-user-menu',
  templateUrl: './app-user-menu.component.html',
  styleUrls: ['./app-user-menu.component.scss'],
})
export class AppUserMenuComponent {
  @Input('expanded') isRowExapnded = false;

  @Output() visibilityChange = new EventEmitter<boolean>();

  constructor(private router: Router) {}

  redirectToLoginPage(): void {
    this.isRowExapnded = false;
    this.visibilityChange.next(false);
    this.router.navigate(['login']);
  }
}
