import { Component, OnInit } from '@angular/core';
import {FormBuilder} from "@angular/forms";
import {AuthenticationService} from "../../services/authentication.service";
import {Router} from "@angular/router";

@Component({
  selector: 'app-registration',
  templateUrl: './app-registration.component.html',
  styleUrls: ['./app-registration.component.scss']
})
export class AppRegistrationComponent {
  showError = false;
  errorMessage = '';
  signUpForm = this.formBuilder.group({
    login: '',
    password: '',
    repeatPassword: ''
  });

  constructor(private formBuilder: FormBuilder, private auth: AuthenticationService, private router: Router) {
    if (this.auth.currentUserValue) {
      this.router.navigate(['']);
    }
  }

  isButtonDisabled(): boolean {
    return !Object.values(this.signUpForm.controls).every((e) => (e.value && e.value.length >= 3));
  }

  onFormSubmission(): void {
    this.showError = false;
    const password = this.signUpForm.get('password')?.value;
    const repeatPassword = this.signUpForm.get('repeatPassword')?.value;
    if(password !== repeatPassword) {
      this.errorMessage = 'Passwords must be the same';
      this.showError = true;
    } else {
      const login = this.signUpForm.get('login')?.value;
      this.auth.register(login, password).subscribe({
        next: () => {
          this.router.navigate(['']);
        },
        error: () => {
          this.errorMessage = 'Username is already taken';
          this.showError = true;
        }
      });
    }
  }
}
