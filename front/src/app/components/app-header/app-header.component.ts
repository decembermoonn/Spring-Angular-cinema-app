import { Component } from '@angular/core';
import { faUser } from '@fortawesome/free-solid-svg-icons';
import { faBars } from '@fortawesome/free-solid-svg-icons';

@Component({
  selector: 'app-header',
  templateUrl: './app-header.component.html',
  styleUrls: ['./app-header.component.scss'],
})
export class AppHeaderComponent {
  faUser = faUser;
  faBars = faBars;
  userMenuExpanded = false;

  expandUserMenu(): void {
    this.userMenuExpanded = !this.userMenuExpanded;
  }

  changeVisibility(value: boolean): void {
    this.userMenuExpanded = value;
  }
}
