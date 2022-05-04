import { Component } from '@angular/core';
import { FormBuilder } from '@angular/forms';
import { AuthenticationService } from '../../services/authentication.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-login',
  templateUrl: './app-login.component.html',
  styleUrls: ['./app-login.component.scss'],
})
export class AppLoginComponent {
  showError = false;
  signUpForm = this.formBuilder.group({
    login: '',
    password: '',
  });

  constructor(private formBuilder: FormBuilder, private auth: AuthenticationService, private router: Router) {
    if (this.auth.currentUserValue) {
      this.router.navigate(['']);
    }
  }

  isButtonDisabled(): boolean {
    return !Object.values(this.signUpForm.controls).every((e) => e.value);
  }

  onFormSubmission(): void {
    this.showError = false;
    const login = this.signUpForm.get('login')?.value;
    const password = this.signUpForm.get('password')?.value;
    this.auth.login(login, password).subscribe({
      next: () => {
        this.router.navigate(['']);
      },
      error: () => {
        this.showError = true;
      },
    });
  }
}
