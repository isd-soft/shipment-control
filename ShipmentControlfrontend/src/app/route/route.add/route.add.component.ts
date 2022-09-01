// import { Component, OnInit,ViewChild } from '@angular/core';
// import {FormBuilder, FormControl, FormGroup, Validators} from "@angular/forms";
// import {MatAccordion} from '@angular/material/expansion';
//
// @Component({
//   selector: 'app-route.add',
//   templateUrl: './route.add.component.html',
//   styleUrls: ['./route.add.component.css']
// })
// export class RouteAddComponent implements OnInit  {
//   @ViewChild(MatAccordion) accordion: MatAccordion;
//
//   legs: string[] = [];
//   routeForm !: FormGroup;
//
//   constructor(private formBuilder: FormBuilder) { }
//
//   ngOnInit(): void {
//     this.routeForm = this.formBuilder.group({
//       description: new FormControl('', [Validators.required]),
//       estimatedDays: new FormControl('', [Validators.required]),
//       maxWeight: new FormControl('', [Validators.required]),
//       maxVolume: new FormControl('', [Validators.required]),
//       availableDaysRent: new FormControl('', [Validators.required]),
//       transport: new FormControl('', [Validators.required]),
//
//     });
//   }
//   add(){
//     this.legs.push('')
//   }
//
//   clearField(legIndex: number){
//     this.legs.splice(legIndex, 1);
//   }
//
//
// }



import { Component, OnInit,ViewChild } from '@angular/core';
import {FormArray, FormBuilder, FormControl, FormGroup, Validators} from "@angular/forms";
import {MatAccordion} from '@angular/material/expansion';
import {RegistrationService} from "../../services/registration.service";
import {Router} from "@angular/router";
import {MatSnackBar} from "@angular/material/snack-bar";
import {ConfirmedValidator} from "../../registration-page/registration-page.component";
import {RegisterCommand} from "../../services/RegisterCommand";
import {RouteService} from "../../services/route.service";
import {RouteCommand} from "../../services/RouteCommand";
import {LegCommand} from "../../services/LegCommand";
import {ItineraryCommand} from "../../services/ItineraryCommand";
import {TransportsService} from "../../services/transports.service";
import {MatTableDataSource} from "@angular/material/table";
import {TransportDto} from "../../model/transport.dto";

@Component({
  selector: 'app-route.add',
  templateUrl: './route.add.component.html',
  styleUrls: ['./route.add.component.css']
})
export class RouteAddComponent implements OnInit  {
  @ViewChild(MatAccordion) accordion: MatAccordion;

  legs: LegCommand[] = [];
  routeForm !: FormGroup;
  element: any;


  transport: any;
  SelectedValue: any;
  ChangeTransport(e){
    console.log(e.target.value)
    this.SelectedValue=e.target.value;
  }


  constructor(private formBuilder: FormBuilder,
              private routeService: RouteService,
              private snackBar: MatSnackBar,
              private transportService: TransportsService
              ) {
      this.routeForm = formBuilder.group({
        description: new FormControl('', [Validators.required]),
        maxWeight: new FormControl('', [Validators.required]),
        maxVolume: new FormControl('', [Validators.required]),
        availableDaysRent: new FormControl('', [Validators.required]),
        transport: new FormControl('', [Validators.required]),
        estimatedDays: new FormControl('', [Validators.required])
    });
  }

  ngOnInit(): void {
    this.legs.push({name: '', address: '', country: '', countryCode: ''});
    this.legs.push({name: '', address: '', country: '', countryCode: ''});


    this.transportService.getTransports().subscribe((data:any)=>{
      this.transport=data;
    })

  }


  add(){
    this.legs.push({name: '', address: '', country: '', countryCode: ''});
  }

  clearField(legIndex: number){
    this.legs.splice(legIndex, 1);
  }

  onReset(): void {
    this.routeForm.reset();
  }

  onSubmit() {
    console.log(this.legs);
    const itinerary: ItineraryCommand = {
      legList: this.legs,
      estimatedAmountTimeShipment: this.routeForm.controls['estimatedDays'].value
    };
    const routeCommand: RouteCommand = {
      detailedRouteDescription: this.routeForm.controls['description'].value,
      maxLoadWeight: this.routeForm.controls['maxWeight'].value,
      maxLoadVolume: this.routeForm.controls['maxVolume'].value,
      availableDaysRentList: this.routeForm.controls['availableDaysRent'].value,
      transportIdList: this.routeForm.controls['transport'].value,
      itineraryCommand: itinerary,
    }
    console.log(routeCommand);

    this.routeService.createRoute(routeCommand).subscribe(
      response => {
    this.snackBar.open("Successfully added", 'OK',{duration:6000});

  },
      error => {
        console.log("Unsuccessful");
        this.snackBar.open("Unsuccessfully added route, please fill all the fields", 'OK',{duration:6000});

      }
    );

  }

}

// export class DropdownTransport implements OnInit{
//
//   TransportList: any;
//   SelectedValue: any;
//   ChangeTransport(e){
//     console.log(e.target.value)
//     this.SelectedValue=e.target.value;
//   }
//   constructor(private transportService: TransportsService) {}
//
//   ngOnInit(): void {
//     this.transportService.getTransports().subscribe((data:any)=>{
//       this.TransportList=data;
//     })
//   }
// }
