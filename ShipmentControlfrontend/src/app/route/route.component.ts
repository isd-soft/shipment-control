import {Component, OnInit, ViewChild} from '@angular/core';
import {MatDialog} from "@angular/material/dialog";
import {MatPaginator} from "@angular/material/paginator";
import {MatSort} from "@angular/material/sort";
import {RouteService} from "../services/route.service";
import {MatSnackBar} from "@angular/material/snack-bar";
import {Router} from "@angular/router";
import {MatTableDataSource} from "@angular/material/table";
import {RouteDto} from "../model/route.dto";
import {SelectionModel} from "@angular/cdk/collections";


@Component({
  selector: 'app-route',
  templateUrl: './route.component.html',
  styleUrls: ['./route.component.css']
})
export class RouteComponent implements OnInit {
  displayedColumns: string[] = ['routeDescription', 'availableDaysRent', 'origin', 'destination', 'details','edit','delete'];
  dataSource:MatTableDataSource<RouteDto>;
  selection = new SelectionModel<RouteDto>(true, []);
  @ViewChild('paginator') paginator: MatPaginator;
  @ViewChild('empTbSort') empTbSort = new MatSort();


  constructor(private dialog: MatDialog,
              private routeService: RouteService,
              private snackbar: MatSnackBar,
              private router: Router) {
    this.dataSource = new MatTableDataSource()
  }

  ngOnInit(): void {
    this.getAllRoutes();
  }

  getAvailableDays(element: any): string {
    let days = "";

    element.availableDaysRentList.forEach(name => {
      days += name.name + ", ";
    })
    return days;
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

  // layout
  opened = true;

  toggle(): void {
    this.opened = !this.opened;
  }

  getAllRoutes() {
    this.routeService.getRoute()
      .subscribe({
        next: (res) => {
          this.dataSource = new MatTableDataSource<RouteDto>(res);
          this.dataSource.paginator = this.paginator;
          this.dataSource.sort = this.empTbSort;
        },
        error: () => {
          this.snackbar.open("Error while fetching the the record!!", 'Error', {duration: 2000});

        }
      })
  }

  /*editRoute(row: any) {
    this.dialog.open(RouteDialogComponent, {
      width: '30%',
      data: row
    }).afterClosed().subscribe(value => {
      if (value === 'update') {
        this.getAllRoutes();
      }
    })
  }*/

  /*deleteRoute(id: number) {
    this.routeService.deleteRoute(id)
      .subscribe({
        next: (res) => {
          this.snackbar.open("Deleted Successfully", 'Ok', {duration: 2000})

          this.getAllRoutes();
        },
        error: () => {
          this.snackbar.open("Error while deleting the Route", 'Error', {duration: 2000});
        }
      })

  }*/

  applyFilter(event: Event) {
    const filterValue = (event.target as HTMLInputElement).value;
    this.dataSource.filter = filterValue.trim().toLowerCase();
    if (this.dataSource.paginator) {
      this.dataSource.paginator.firstPage();
    }
  }

  viewRouteDetails(row) {


  }

  onRowClicked(row) {

  }

  openDetails(row) {
    this.router.navigateByUrl('/dashboard/route/details');
  }
}





