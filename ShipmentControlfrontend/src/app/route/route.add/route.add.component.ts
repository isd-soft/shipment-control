import { Component, OnInit,ViewChild } from '@angular/core';
import {FormBuilder, FormControl, FormGroup, Validators} from "@angular/forms";
import {MatAccordion} from '@angular/material/expansion';

@Component({
  selector: 'app-route.add',
  templateUrl: './route.add.component.html',
  styleUrls: ['./route.add.component.css']
})
export class RouteAddComponent implements OnInit  {
  @ViewChild(MatAccordion) accordion: MatAccordion;

  legs: string[] = [];
  routeForm !: FormGroup;

  constructor(private formBuilder: FormBuilder) { }

  ngOnInit(): void {
    this.routeForm = this.formBuilder.group({
      description: new FormControl('', [Validators.required]),
      estimatedDays: new FormControl('', [Validators.required]),
      maxWeight: new FormControl('', [Validators.required]),
      availableDaysRent: new FormControl('', [Validators.required]),
      transport: new FormControl('', [Validators.required]),

    });
  }
  add(){
    this.legs.push('')
  }

  clearField(legIndex: number){
    this.legs.splice(legIndex, 1);
  }


}
