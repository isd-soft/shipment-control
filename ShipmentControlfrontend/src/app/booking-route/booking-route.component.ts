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
import {BookingRouteCommand} from "../services/BookingRouteCommand";
import {DaysOfWeek, RouteDisplayDetailsComponent} from "../route/route.display.details/route.display.details.component";
import {AvailableDaysRentDto} from "../model/availableDaysRentDto";
import {DatePipe} from "@angular/common";


@Component({
  selector: 'app-booking-route',
  templateUrl: './booking-route.component.html',
  styleUrls: ['./booking-route.component.css']
})

export class BookingRouteComponent implements OnInit {

  @ViewChild(MatAccordion) accordion: MatAccordion;

  bookingRouteForm !: FormGroup;
  cargoTypeDtoList: CargoTypeDto[];
  currentRouteId: Params;
  legs: LegDto[];
  dateForm!: FormGroup;
  d: any;
  finalArr: (undefined | number)[] = [];
  daysCalendar: string[] = [];
  availableDaysRents: AvailableDaysRentDto[];

  constructor(private formBuilder: FormBuilder,
              private routeService: RouteService,
              private snackBar: MatSnackBar,
              private route: ActivatedRoute,
              private transportService: TransportsService,
              private bookingService: BookingService,
              private cargoTypeService: CargoTypeService,
              private changeDetectorRef: ChangeDetectorRef,
              private datePipe: DatePipe
  ) {
  }

  ngOnInit(): void {
    this.route.queryParams
      .subscribe(params => {
        this.currentRouteId = params;
      });
    this.bookingRouteForm = this.formBuilder.group({
      cargoType: new FormControl('', [Validators.required]),
      maxWeight: new FormControl('', [Validators.required]),
      maxVolume: new FormControl('', [Validators.required]),
      //pickedDate: new

    });
    this.dateForm = this.formBuilder.group({
      pickedDate: new FormControl('', [Validators.required])
    });
    this.getAllCargoTypes();
    this.getAllLegsForRoute();
    this.getAvailableDays();
  }

  getAvailableDays() {
    this.routeService.getRouteById(this.currentRouteId['routeId'])
      .subscribe({
        next: (res) => {
          this.availableDaysRents = res.availableDaysRentList
        }
      })
  }


  conv() {
    let i = 0;
    this.availableDaysRents.forEach(name => {
      this.daysCalendar[i] = name.label;
      i++;
    })

    for (let u = 0; u < 7; u++) {
      for (let j = 0; j < 7; j++) {
        if (this.daysCalendar[u] === this.weekDays[j].name) {
          this.weekDays[j].id = undefined;
        }
      }
      this.finalArr[u] = this.weekDays[u].id;
    }
    console.log(this.finalArr);
    console.log(this.daysCalendar);

  }

  weekDays: DaysOfWeek[] = [
    {name: 'Sunday', id: 0},
    {name: 'Monday', id: 1},
    {name: 'Tuesday', id: 2},
    {name: 'Wednesday', id: 3},
    {name: 'Thursday', id: 4},
    {name: 'Friday', id: 5},
    {name: 'Saturday', id: 6}
  ]

  filter = (d: Date): boolean => {
    this.conv();
    const days = d.getDay();

    return days !== this.finalArr[0] && days !== this.finalArr[1]
      && days !== this.finalArr[2] && days !== this.finalArr[3]
      && days !== this.finalArr[4] && days !== this.finalArr[5]
      && days !== this.finalArr[6]
  }

  onSubmit(): void {
    console.log(this.legs);
    /*const itinerary: ItineraryCommand = {
      legList: this.legs,
      //estimatedAmountTimeShipment: this.bookingRouteForm.controls['estimatedDays'].value
    };*/

    const date = this.dateForm.controls['pickedDate'].value;

    const legCommands: LegCommand[] = this.legs.map(leg => ({
      country: leg.country,
      countryCode: leg.countryCode, name: leg.name, address: leg.address
    }));

    const itineraryCommand: ItineraryCommand = {
      legList: legCommands,
      estimatedAmountTimeShipment: null
    }

    const bookingRouteCommand: BookingRouteCommand = {

      totalWeight: this.bookingRouteForm.controls['maxWeight'].value,
      totalVolume: this.bookingRouteForm.controls['maxVolume'].value,
      cargoTypeList: this.bookingRouteForm.controls['cargoType'].value,
      itineraryCommand: itineraryCommand,
      bookingDate: this.datePipe.transform(date, "yyyy-MM-dd")
    }
    console.log(bookingRouteCommand);
    this.bookingService.createBookingRoute(bookingRouteCommand).subscribe(
      response => {
        console.log("Hurray!");
        this.snackBar.open("Successfully added", 'OK', {duration: 6000});

      },
      error => {
        console.log(error);
        console.log("Unsuccessful");
        this.snackBar.open("Unsuccessfully added route", 'OK', {duration: 6000});

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

  // @ts-ignore
  removeLeg(): void {
    if (this.legs.length > 2) {
      this.legs.pop();
    } else {
      //@ts-ignore
      document.querySelector('.remove-leg').disable = true;
      }
    }
}
