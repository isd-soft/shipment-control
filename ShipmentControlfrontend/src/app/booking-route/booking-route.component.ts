import { Component, OnInit,ViewChild } from '@angular/core';
import {FormBuilder, FormControl, FormGroup, Validators} from "@angular/forms";
import {MatAccordion} from '@angular/material/expansion';
import {MatSnackBar} from "@angular/material/snack-bar";
import {RouteService} from "../services/route.service";
import {TransportsDialogComponent} from "../transports/transports.dialog/transports.dialog.component";
import {LegCommand} from "../services/LegCommand";
import {TransportsService} from "../services/transports.service";
import {ItineraryCommand} from "../services/ItineraryCommand";
import {RouteCommand} from "../services/RouteCommand";
import {BookingRouteCommand} from "../services/BookingRouteCommand";
import {CargoTypeDto} from "../model/cargoType.dto";
import {BookingRouteService} from "../services/BookingRoute.service";


@Component({
  selector: 'app-booking-route',
  templateUrl: './booking-route.component.html',
  styleUrls: ['./booking-route.component.css']
})

export class BookingRouteComponent implements OnInit {
  @ViewChild(MatAccordion) accordion: MatAccordion;

  legs: LegCommand[] = [];
  bookingRouteForm !: FormGroup;
  element: any;
  cargoTypeDtoList: CargoTypeDto[];

  constructor(private formBuilder: FormBuilder,
              private routeService: RouteService,
              private snackBar: MatSnackBar,
              private transportService: TransportsService,
              private bookingRouteService: BookingRouteService
  ) {

  }

  ngOnInit(): void {
    this.bookingRouteForm = this.formBuilder.group({
      cargoType:new FormControl('',[Validators.required]),
      description: new FormControl('', [Validators.required]),
      maxWeight: new FormControl('', [Validators.required]),
      maxVolume: new FormControl('', [Validators.required]),
    });
    this.getAllCargoTypes();
    this.getAllLegsForRoute()
  }

  onSubmit(): void {
    console.log(this.legs);
    const itinerary: ItineraryCommand = {
      legList: this.legs,
      estimatedAmountTimeShipment: this.bookingRouteForm.controls['estimatedDays'].value
    };

    const bookingRouteCommand = {
      cargoDescription: this.bookingRouteForm.controls['cargoType'].value,
      maxLoadWeight: this.bookingRouteForm.controls['maxWeight'].value,
      maxLoadVolume: this.bookingRouteForm.controls['maxVolume'].value,
      cargoType: this.bookingRouteForm.controls['cargoTypeDtoList'].value,
      weight: this.bookingRouteForm.controls['weight'].value,
      volume: this.bookingRouteForm.controls['volume'].value
    }

    this.bookingRouteService.createBookingRoute(bookingRouteCommand).subscribe(
      response => {
        console.log("Hurray!");
        this.snackBar.open("Successfully added", 'OK',{duration:6000});

      },
      error => {
        console.log(error);
        console.log("Unsuccessful");
        this.snackBar.open("Unsuccessfully added route", 'OK',{duration:6000});

      }
    );
  }

  getAllCargoTypes() {
    this.bookingRouteService.getCargoType()
      .subscribe({
        next: (res) => {
          this.cargoTypeDtoList = res;
        }
      });
  }

  clearField(legIndex: number){
    this.legs.splice(legIndex, 1);
  }

  onReset(): void {
    this.bookingRouteForm.reset();
  }
}
