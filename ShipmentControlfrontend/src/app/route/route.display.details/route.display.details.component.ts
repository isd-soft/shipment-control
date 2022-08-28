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

  constructor(
    private routeService: RouteService,
    private snackbar: MatSnackBar,
    private router: Router,
    private route: ActivatedRoute) {
    this.transportDataSource = new MatTableDataSource();
    this.legDataSource = new MatTableDataSource();
  }

  ngOnInit(): void {
    this.getRouteById();

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


}
