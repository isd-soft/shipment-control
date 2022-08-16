import {Component, OnInit} from '@angular/core';

import {FormGroup, FormControl, Validators} from '@angular/forms';

@Component({
  selector: 'app-registration-page',
  templateUrl: './registration-page.component.html',
  styleUrls: ['./registration-page.component.css']
})

export class RegistrationPageComponent implements OnInit {
  userEmail = new FormGroup({
    email: new FormControl('', [
      Validators.required,
      Validators.pattern("^[a-z0-9._%+-]+@[a-z0-9.-]+\\.[a-z]{2,4}$")]),
  });

  get email() {
    return this.userEmail.get('email')
  }

  constructor() {
  }

  ngOnInit(): void {
  }

  onReset(): void {
    console.log("resetting")
  }

  onSubmit() {
    console.log("submitting")
  }

}



