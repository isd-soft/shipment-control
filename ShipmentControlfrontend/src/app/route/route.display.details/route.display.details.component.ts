import {Component, OnInit, ViewChild} from '@angular/core';
import {MatTableDataSource} from "@angular/material/table";
import {RouteDto} from "../../model/route.dto";
import {RouteService} from "../../services/route.service";
import {MatSnackBar} from "@angular/material/snack-bar";
import {Router} from "@angular/router";

@Component({
  selector: 'app-route.display.details',
  templateUrl: './route.display.details.component.html',
  styleUrls: ['./route.display.details.component.css']
})
export class RouteDisplayDetailsComponent implements OnInit {

  displayedColumns: string[] = ['routeDescription', 'availableDaysRent', 'origin', 'destination', 'details','edit','delete'];
  dataSource:MatTableDataSource<RouteDto>;

  constructor(
              private routeService: RouteService,
              private snackbar: MatSnackBar,
              private router: Router) {
    this.dataSource = new MatTableDataSource()
  }

  ngOnInit(): void {
    this.getAllRoutes();
  }

  getOrigin(element: any): string {
    return element.legDTOS.shift().address;
  }
  getDestination(element: any): string {
    return element.legDTOS.at(-1).address;
  }

  btnClick = () => {
    this.router.navigateByUrl('/dashboard/route/add');
  };

  getAllRoutes() {
    this.routeService.getRoute()
      .subscribe({
        next: (res) => {
          this.dataSource = new MatTableDataSource<RouteDto>(res);
        },
        error: () => {
          this.snackbar.open("Error while fetching the the record!!", 'Error', {duration: 2000});

        }
      })
  }



}
