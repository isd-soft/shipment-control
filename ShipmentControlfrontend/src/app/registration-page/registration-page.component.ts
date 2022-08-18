import { Component, OnInit } from '@angular/core';
import { RegistrationService } from "../services/registration.service";
import { RegisterCommand } from "../services/RegisterCommand";
import { Router } from "@angular/router";

import {
  FormGroup,
  Validators,
  FormBuilder,
} from '@angular/forms';


@Component({
  selector: 'app-registration-page',
  templateUrl: './registration-page.component.html',
  styleUrls: ['./registration-page.component.css']
})

export class RegistrationPageComponent implements OnInit {

  registrationForm: FormGroup;
  hide: boolean = true;

  users : any
  ngOnInit(): void {
    // this.registrationService.getUsers().subscribe(data =>{
    //   this.users = data;
    // })
  }

  onReset(): void {
    this.registrationForm.reset();
  }

  onSubmit() {
    const registerCommand: RegisterCommand = {
      userRole: this.registrationForm.controls['userType'].value,
      email: this.registrationForm.controls['email'].value,
      companyName: this.registrationForm.controls['companyName'].value,
      userName: this.registrationForm.controls['userName'].value,
      telephoneNumber: this.registrationForm.controls['telephoneNumber'].value,
      password: this.registrationForm.controls['password'].value,
      confirmPassword: this.registrationForm.controls['confirmPassword'].value
    }
    console.log(registerCommand);

    this.registrationService.register(registerCommand).subscribe(
      response => {
        this.router.navigateByUrl("/login");
      },
      error => {

      }
    );
  }

  constructor(private fb: FormBuilder,
              private registrationService: RegistrationService,
              private router: Router) {
    this.registrationForm = fb.group({
      userType: fb.control('', [Validators.required]),
      email: fb.control('', [Validators.email]),
      userName: fb.control('', [Validators.required]),
      companyName: fb.control('', [Validators.required]),
      telephoneNumber: fb.control('', [Validators.required, Validators.pattern("^[0-9]*$"),  Validators.maxLength(10)]),
      password: fb.control('', [Validators.required]),
      confirmPassword: fb.control('', [Validators.required]),
      }, {validator: ConfirmedValidator('password', 'confirmPassword')});
    }
}

export function ConfirmedValidator(controlName: string, matchingControlName: string) {
  return (formGroup: FormGroup) => {
    const control = formGroup.controls[controlName];
    const matchingControl = formGroup.controls[matchingControlName];
    if (matchingControl.errors && !matchingControl.errors['confirmedValidator']) {
      return;
    }
    if (control.value !== matchingControl.value) {
      matchingControl.setErrors({confirmedValidator: true});
    } else {
      matchingControl.setErrors(null);
    }
  }
}
