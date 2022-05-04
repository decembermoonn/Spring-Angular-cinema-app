import { Component } from '@angular/core';
import { FormBuilder } from '@angular/forms';

@Component({
  selector: 'app-login',
  templateUrl: './app-login.component.html',
  styleUrls: ['./app-login.component.scss'],
})
export class AppLoginComponent {
  signUpForm = this.formBuilder.group({
    login: '',
    password: '',
  });

  constructor(private formBuilder: FormBuilder) {}

  isButtonDisabled(): boolean {
    return !Object.values(this.signUpForm.controls).every((e) => e.value);
  }

  onFormSubmission(): void {
    const loginData = {
      login: this.signUpForm.get('login')?.value,
      password: this.signUpForm.get('password')?.value,
    };
    console.log(loginData);
  }
}
