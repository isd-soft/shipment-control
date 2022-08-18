import {Component, Injectable, OnInit, ViewChild} from '@angular/core';
import {RegistrationService} from "../services/registration.service";
import {RegisterCommand} from "../services/RegisterCommand";

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

  @ViewChild('resetBtn') resetBtn;
  onReset(): void {
    this.registrationForm.reset();
  }

  onSubmit() {
    const registerCommand: RegisterCommand = new RegisterCommand();
    registerCommand.userType = this.registrationForm.controls['userType'].value;
    registerCommand.email = this.registrationForm.controls['email'].value;
    registerCommand.companyName = this.registrationForm.controls['companyName'].value;
    registerCommand.userName = this.registrationForm.controls['userName'].value;
    registerCommand.telephoneNumber = this.registrationForm.controls['telephoneNumber'].value;
    registerCommand.password = this.registrationForm.controls['password'].value;
    registerCommand.confirmPassword = this.registrationForm.controls['confirmPassword'].value;

    const registrationObserver = {
      next() {
        console.log('Successfully registered');
      },
      error(error) {
        // We actually could just remove this method,
        // since we do not really care about errors right now.
      }
    };


    this.registrationService.register(registerCommand).subscribe(registrationObserver);

    console.warn(this.registrationForm.value);
  }

  constructor(private fb: FormBuilder,
              private registrationService: RegistrationService) {
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
