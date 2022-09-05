import {Component, OnInit, ViewChild} from '@angular/core';
import {MatTableDataSource} from "@angular/material/table";
import {RouteDto} from "../../model/route.dto";
import {RouteService} from "../../services/route.service";
import {MatSnackBar} from "@angular/material/snack-bar";
import {ActivatedRoute, Params, Router} from "@angular/router";
import {TransportDto} from "../../model/transport.dto";
import {MatPaginator} from "@angular/material/paginator";
import {MatSort} from "@angular/material/sort";
import {LegDto} from "../../model/leg.dto";
import {FormBuilder, FormControl, FormGroup, Validators} from "@angular/forms";
import {BookingRequestService} from "../../services/BookingRequest.service";
import {BookingRequestCommand} from "../../services/BookingRequestCommand";
import {DatePipe} from "@angular/common";

export interface RouteDetails {
  name: string;
  content: string;
}

@Component({
  selector: 'app-route.display.details',
  templateUrl: './route.display.details.component.html',
  styleUrls: ['./route.display.details.component.css']
})
export class RouteDisplayDetailsComponent implements OnInit {

  transportDisplayedColumns: string[] = ['transportName', 'transportType', 'cargoTypes'];
  routeDetailsDisplayedColumns: string[] = ['name', 'content'];
  legDisplayedColumns: string [] = ['name', 'address', 'country', 'countryCode'];
  dataSource: RouteDto;
  transportDataSource: MatTableDataSource<TransportDto>;
  legDataSource: MatTableDataSource<LegDto>;
  currentRouteId: Params;
  routeDetails: RouteDetails[];
  @ViewChild('transportPaginator') transportPaginator: MatPaginator;
  @ViewChild('legPaginator') legPaginator: MatPaginator;
  @ViewChild('transportSort') transportSort = new MatSort();
  @ViewChild('legSort') legSort = new MatSort();
  dateForm!: FormGroup;
  private arr: number[] = [1, 2, 5];


  constructor(
    private routeService: RouteService,
    private snackbar: MatSnackBar,
    private router: Router,
    private route: ActivatedRoute,
    private bookingRequestService: BookingRequestService,
    private formBuilder: FormBuilder,
    private datePipe: DatePipe) {
    this.transportDataSource = new MatTableDataSource();
    this.legDataSource = new MatTableDataSource();
  }

  ngOnInit(): void {
    this.getRouteById();
    this.dateForm = this.formBuilder.group({
      pickedDate: new FormControl('', [Validators.required])
    });

  }

  getCargoTypeNames(element: any): string {
    let cargoTypes = "";

    element.cargoTypes.forEach(name => {
      cargoTypes += name.name + ", ";
    })
    return cargoTypes;
  }

  getAvailableDays(element: any): string {
    let days = "";

    element.availableDaysRentList.forEach(name => {
      days += name.label + ", ";
    })
    return days;
  }

  getRouteById() {
    this.route.queryParams
      .subscribe(params => {
        this.currentRouteId = params;
      })
    this.routeService.getRouteById(this.currentRouteId['routeId'])
      .subscribe({
        next: (res) => {
          this.dataSource = res;
          this.transportDataSource = new MatTableDataSource<TransportDto>(res.transportDTOList);
          this.transportDataSource.sort = this.transportSort;
          this.transportDataSource.paginator = this.transportPaginator;
          this.routeDetails = [
            {name: "Route Description", content: this.dataSource.routeDescription},
            {name: "Available days", content: this.getAvailableDays(this.dataSource)},
            {name: "Maximum load weight", content: this.dataSource.maximalLoadWeight.toString()},
            {name: "Estimate time for delivering", content: this.dataSource.estimatedAmountTimeShipment.toString()}
          ];
          this.legDataSource = new MatTableDataSource<LegDto>(res.itineraryDTO.legDTOS);
          this.legDataSource.paginator = this.legPaginator;
          this.legDataSource.sort = this.legSort;

        },
        error: () => {
          this.snackbar.open("Error while fetching the the record!!", 'Error', {duration: 2000});

        }
      })
  }


  dateFilter = (d: Date): boolean => {

    // Prevent Saturday and Sunday from being selected.
    return d.getDay() !== 0 && d.getDay() !== 6;
  }


  onSubmit() {
    console.log(this.dateForm.value);
    const date = this.dateForm.controls['pickedDate'].value;
    if (date != '') {
      const data: BookingRequestCommand = {
        routeId: this.currentRouteId['routeId'],
        localDateRequested: this.datePipe.transform(date, "yyyy-MM-dd"),
      }
      this.bookingRequestService.addBookingRequest(data)
        .subscribe({
          next: () => {
            this.snackbar.open("The request was sent successfully!", 'Ok', {duration: 2000});
          },
          error: () => {
            this.snackbar.open("Error while requesting the day", 'Error', {duration: 2000});
          }
        })
    } else {
      this.snackbar.open("Please provide a valid day!", 'Error', {duration: 2000});
    }
  }

  bookRoute(): void {
    this.router.navigate(['/dashboard/book'],
      {queryParams: {routeId: this.currentRouteId['routeId']}});
  }
}
