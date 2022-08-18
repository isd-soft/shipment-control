import { Component, OnInit } from '@angular/core';

import {FormGroup, FormControl, Validators, FormBuilder} from '@angular/forms';
import {LoginCommand} from "../services/LoginCommand";
import {AuthService} from "../services/auth.service";
import {Router} from "@angular/router";
import {error} from "@angular/compiler-cli/src/transformers/util";

@Component({
  selector: 'app-login-page',
  templateUrl: './login-page.component.html',
  styleUrls: ['./login-page.component.css']
})
export class LoginPageComponent implements OnInit {

  loginForm: FormGroup;

  constructor(private fb: FormBuilder,
              private authService: AuthService,
              private router: Router) {
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

    this.authService.login(loginCommand).subscribe(
      (result) => {
        this.router.navigateByUrl('/dashboard');
      },
      error => {

      }
    );
  }
}
