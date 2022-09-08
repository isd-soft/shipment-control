import {Component, OnInit, ViewChild} from '@angular/core';
import {FormBuilder, FormControl, FormGroup, Validators} from "@angular/forms";
import {MatAccordion} from '@angular/material/expansion';
import {MatSnackBar} from "@angular/material/snack-bar";
import {RouteService} from "../../services/route.service";
import {RouteCommand} from "../../services/RouteCommand";
import {LegCommand} from "../../services/LegCommand";
import {ItineraryCommand} from "../../services/ItineraryCommand";
import {TransportsService} from "../../services/transports.service";
import {Router} from "@angular/router";

@Component({
  selector: 'app-route.add',
  templateUrl: './route.add.component.html',
  styleUrls: ['./route.add.component.css']
})
export class RouteAddComponent implements OnInit {
  @ViewChild(MatAccordion) accordion: MatAccordion;

  legs: LegCommand[] = [];
  routeForm !: FormGroup;
  element: any;

  transport: any;
  SelectedValue: any;

  ChangeTransport(value) {
    console.log(value);
    this.SelectedValue = value;
  }

  constructor(private formBuilder: FormBuilder,
              private routeService: RouteService,
              private snackBar: MatSnackBar,
              private route: Router,
              private transportService: TransportsService
  ) {
    this.routeForm = formBuilder.group({
      description: new FormControl('', [Validators.required]),
      maxWeight: new FormControl('', [Validators.required]),
      maxVolume: new FormControl('', [Validators.required]),
      availableDaysRent: new FormControl('', [Validators.required]),
      transportIdList: new FormControl('', [Validators.required]),
      estimatedDays: new FormControl('', [Validators.required])
    });
  }

  ngOnInit(): void {
    this.legs.push({name: '', address: '', country: '', countryCode: '', price: 0});
    this.legs.push({name: '', address: '', country: '', countryCode: '', price: 0});


    this.transportService.getTransports().subscribe((data: any) => {
      this.transport = data;
    })

  }

  add() {
    this.legs.push({name: '', address: '', country: '', countryCode: '', price: 0});
  }

  clearField(legIndex: number) {
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
      transportIdList: this.routeForm.controls['transportIdList'].value,
      itineraryCommand: itinerary,
    }
    console.log(routeCommand);

    this.routeService.createRoute(routeCommand).subscribe(
      response => {
        this.snackBar.open("Successfully added", 'OK', {duration: 6000});
        this.route.navigateByUrl('/dashboard/route');

      },
      error => {
        this.snackBar.open("Unsuccessfully added route", 'OK', {duration: 6000});

      }
    );

  }

}
