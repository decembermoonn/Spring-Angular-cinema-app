import {Component, OnDestroy} from '@angular/core';
import { faUser } from '@fortawesome/free-solid-svg-icons';
import { faBars } from '@fortawesome/free-solid-svg-icons';
import {AuthenticationService} from "../../services/authentication.service";
import {Subscription} from "rxjs";
import { OptionalUser } from "../../models/user";

@Component({
  selector: 'app-header',
  templateUrl: './app-header.component.html',
  styleUrls: ['./app-header.component.scss'],
})
export class AppHeaderComponent implements OnDestroy {
  faUser = faUser;
  faBars = faBars;
  userMenuExpanded = false;
  localUserSubscription: Subscription;
  localUser: OptionalUser;

  constructor(auth: AuthenticationService) {
    this.localUserSubscription = auth.currentUser.subscribe((user) => {
      this.localUser = user;
    });
  }

  expandUserMenu(): void {
    this.userMenuExpanded = !this.userMenuExpanded;
  }

  changeVisibility(value: boolean): void {
    this.userMenuExpanded = value;
  }

  ngOnDestroy(): void {
    this.localUserSubscription.unsubscribe();
  }
}
