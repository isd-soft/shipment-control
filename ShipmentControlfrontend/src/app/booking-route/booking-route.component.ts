import {ChangeDetectorRef, Component, OnInit, ViewChild} from '@angular/core';
import {FormBuilder, FormControl, FormGroup, Validators} from "@angular/forms";
import {MatAccordion} from '@angular/material/expansion';
import {MatSnackBar} from "@angular/material/snack-bar";
import {RouteService} from "../services/route.service";
import {TransportsDialogComponent} from "../transports/transports.dialog/transports.dialog.component";
import {LegCommand} from "../services/LegCommand";
import {TransportsService} from "../services/transports.service";
import {ItineraryCommand} from "../services/ItineraryCommand";
import {RouteCommand} from "../services/RouteCommand";
import {CargoTypeDto} from "../model/cargoType.dto";
import {ActivatedRoute, Params} from "@angular/router";
import {BookingService} from "../services/booking.service";
import {CargoTypeService} from "../services/cargoType.service";
import {LegDto} from "../model/leg.dto";


@Component({
  selector: 'app-booking-route',
  templateUrl: './booking-route.component.html',
  styleUrls: ['./booking-route.component.css']
})

export class BookingRouteComponent implements OnInit {
  @ViewChild(MatAccordion) accordion: MatAccordion;

  //legs: LegCommand[] = [];
  bookingRouteForm !: FormGroup;
  element: any;
  cargoTypeDtoList: CargoTypeDto[];
  currentRouteId: Params;
  legs: LegDto[];


  constructor(private formBuilder: FormBuilder,
              private routeService: RouteService,
              private snackBar: MatSnackBar,
              private route: ActivatedRoute,
              private transportService: TransportsService,
              private bookingService: BookingService,
              private cargoTypeService: CargoTypeService,
              private changeDetectorRef: ChangeDetectorRef
  ) {

  }

  ngOnInit(): void {
    this.route.queryParams
      .subscribe(params => {
        this.currentRouteId = params;
      });
    this.bookingRouteForm = this.formBuilder.group({
      cargoType:new FormControl('',[Validators.required]),
      description: new FormControl('', [Validators.required]),
      maxWeight: new FormControl('', [Validators.required]),
      maxVolume: new FormControl('', [Validators.required]),

    });
    this.getAllCargoTypes();
    this.getAllLegsForRoute()

    /*for (let leg of this.legs) {
      console.log(leg.name);
    }*/
  }

  onSubmit(): void {
    console.log(this.legs);
    /*const itinerary: ItineraryCommand = {
      legList: this.legs,
      //estimatedAmountTimeShipment: this.bookingRouteForm.controls['estimatedDays'].value
    };*/

    const bookingRouteCommand = {
      cargoDescription: this.bookingRouteForm.controls['cargoType'].value,
      /*maxLoadWeight: this.bookingRouteForm.controls['maxWeight'].value,
      maxLoadVolume: this.bookingRouteForm.controls['maxVolume'].value,*/
      cargoType: this.bookingRouteForm.controls['cargoTypeDtoList'].value,
      weight: this.bookingRouteForm.controls['weight'].value,
      volume: this.bookingRouteForm.controls['volume'].value
    }

    this.bookingService.createBookingRoute(bookingRouteCommand).subscribe(
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
    this.cargoTypeService.getCargoType()
      .subscribe({
        next: (res) => {
          this.cargoTypeDtoList = res;
        }
      });
  }

  onReset(): void {
    this.bookingRouteForm.reset();
  }

  getAllLegsForRoute(): void {
    this.bookingService.getAllLegsFromRoute(this.currentRouteId['routeId']).subscribe(legs => {
      this.legs = legs;
      this.changeDetectorRef.detectChanges();
    });
  }

  removeLeg() : void {
    if (this.legs.length > 3) {
      this.legs.pop();
    }
  }
}
