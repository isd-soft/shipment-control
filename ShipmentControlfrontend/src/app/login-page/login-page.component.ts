import { Component, OnInit } from '@angular/core';
import {MatSnackBar, MatSnackBarModule} from '@angular/material/snack-bar';
import {FormGroup, FormControl, Validators, FormBuilder} from '@angular/forms';
import {LoginCommand} from "../services/LoginCommand";
import {AuthService} from "../services/auth.service";
import {Router} from "@angular/router";
import {error} from "@angular/compiler-cli/src/transformers/util";
import {JwtHelperService} from "@auth0/angular-jwt";

@Component({
  selector: 'app-login-page',
  templateUrl: './login-page.component.html',
  styleUrls: ['./login-page.component.css']
})
export class LoginPageComponent implements OnInit {

  loginForm: FormGroup;
  hide: boolean = true;

  constructor(private fb: FormBuilder,
              private authService: AuthService,
              private router: Router,
              private jwtHelper:JwtHelperService,
              private _snackBar: MatSnackBar) {
    this.loginForm = fb.group({
      username: new FormControl('', [Validators.required]),
      password: new FormControl('', [Validators.required]),
    });
  }

  ngOnInit(): void {
  }

  onSubmit(): void {
    const loginCommand = new LoginCommand(
        this.loginForm.controls['username'].value,
        this.loginForm.controls['password'].value,

    );
    console.log(this.loginForm.value);

    this.authService.login(loginCommand).subscribe(
      (result) => {
        localStorage.setItem('token', result.token);
        localStorage.setItem('username', result.username);
        this._snackBar.open("Successfully logged in",'OK',{duration:1000});
        this.router.navigateByUrl('/dashboard');
      },
      error => {

        checkLoginAndPassword();
        function showMessage(block, message) {
          block.textContent = message;
        }

        function checkLoginAndPassword() {
          const login = document.querySelector('.login');
          const password = document.querySelector('.password');
          const out = document.querySelector('.out');
          let message;
          // @ts-ignore
          if (login.value == '' && password.value == '') {
            message = "You must enter the login and password!";
            showMessage(out, message);
            // @ts-ignore
          } else if (login.value == '') {
              message = "You must enter the login!";
              showMessage(out, message);
            // @ts-ignore
          } else if (password.value == '') {
              message = "You must enter the password!";
              showMessage(out, message);
          } else {
              message = "The login or password are wrong!";
              showMessage(out, message);
          }
        }

        // @ts-ignore
        document.querySelector('.btn-reset').addEventListener('click', () => {
          // @ts-ignore
          document.querySelector('.out').textContent = '';
        });
      }
    )
  }

}
