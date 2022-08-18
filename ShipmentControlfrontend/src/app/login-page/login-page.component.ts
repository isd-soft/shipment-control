import { Component, OnInit } from '@angular/core';

import {FormGroup, FormControl, Validators, FormBuilder} from '@angular/forms';
import {LoginCommand} from "../services/LoginCommand";
import {AuthService} from "../services/auth.service";

@Component({
  selector: 'app-login-page',
  templateUrl: './login-page.component.html',
  styleUrls: ['./login-page.component.css']
})
export class LoginPageComponent implements OnInit {

  loginForm: FormGroup;

  constructor(private fb: FormBuilder,
              private authService: AuthService) {
    this.loginForm = fb.group({
      username: new FormControl('', [Validators.required]),
      password: new FormControl('', [Validators.required])
    });
  }

  ngOnInit(): void {
  }

  onSubmit(): void {
    const loginCommand = new LoginCommand(this.loginForm.controls['username'].value,
                                    this.loginForm.controls['password'].value);
    console.log(this.loginForm.value);
    localStorage.setItem('login', loginCommand.username);
    localStorage.setItem('password', loginCommand.password);

    const loginResult = {
      next(response) {
        localStorage.setItem('token', response.token);
      },
      error(error) {

      }
    }

    /*const passwordResult = {
      next(response)
    }*/
    this.authService.login(loginCommand).subscribe(loginResult);
  }
}
