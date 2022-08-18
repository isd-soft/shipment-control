import { Component, OnInit } from '@angular/core';

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

  ngOnInit(): void {

  }

  onReset(): void {
    console.log("resetting")
  }

  onSubmit() {
    console.warn(this.registrationForm.value);
  }

  constructor(private fb: FormBuilder) {
    this.registrationForm = fb.group({
      userType: fb.control('', [Validators.required]),
      email: fb.control('', [Validators.email]),
      userName: fb.control('', [Validators.required]),
      companyName: fb.control('', [Validators.required]),
      telephoneNumber: fb.control('', [Validators.required]),
      password: fb.control('', [Validators.required]),
      confirmPassword: fb.control('', [Validators.required]),
      }, {validator: ConfirmedValidator('password', 'confirmPassword')});
    }
}

export function ConfirmedValidator(controlName: string, matchingControlName: string){
  return (formGroup: FormGroup) => {
    const control = formGroup.controls[controlName];
    const matchingControl = formGroup.controls[matchingControlName];
    if (matchingControl.errors && !matchingControl.errors['confirmedValidator']) {
      return;
    }
    if (control.value !== matchingControl.value) {
      matchingControl.setErrors({ confirmedValidator: true });
    } else {
      matchingControl.setErrors(null);
    }
  }
}
