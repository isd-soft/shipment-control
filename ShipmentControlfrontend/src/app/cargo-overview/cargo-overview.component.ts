import {AfterViewInit, Component, OnInit, ViewChild} from '@angular/core';
import {MatTable, MatTableDataSource} from "@angular/material/table";
import {SelectionModel} from "@angular/cdk/collections";
import {MatSort} from "@angular/material/sort";
import {MatPaginator} from "@angular/material/paginator";
import {CargoOverviewDTO} from "../model/cargoOverview.dto";
import {MatDialog} from "@angular/material/dialog";
import {CargoOverviewService} from "../services/cargoOverview.service";
import {MatSnackBar} from "@angular/material/snack-bar";
import {Router} from "@angular/router";
import {CargoService} from "../services/cargoService";
import decode from "jwt-decode";


@Component({
  selector: 'app-cargo-overview',
  templateUrl: './cargo-overview.component.html',
  styleUrls: ['./cargo-overview.component.css']
})
export class CargoOverviewComponent implements OnInit {

  displayedColumns: string[] = ['goodsCompanyName', 'bookingDate', 'trackingNumber', 'origin', 'destination', 'cargoStatus', 'action'];
  dataSource: MatTableDataSource<CargoOverviewDTO>;
  selection = new SelectionModel<CargoOverviewDTO>(true, []);
  // @ts-ignore
  userRole = decode(localStorage.getItem('token')).sub;
  @ViewChild(MatPaginator) paginator!: MatPaginator;
  @ViewChild(MatSort) sort!: MatSort;

  constructor(private dialog: MatDialog,
              private api: CargoOverviewService,
              private cargoService: CargoService,
              private snackbar: MatSnackBar,
              private router: Router) {
  }

  ngOnInit(): void {
    this.getAllCargoOverview();
  }

  reload() {
    this.getAllCargoOverview();
  }

  applyFilter(event: Event) {
    const filterValue = (event.target as HTMLInputElement).value;
    this.dataSource.filter = filterValue.trim().toLowerCase();
  }

  openDetails(row) {
    this.router.navigateByUrl('/dashboard/cargo/details/' + row.id);
  }

  getAllCargoOverview() {
    // @ts-ignore
    console.log(decode(localStorage.getItem('token')).username);
    // @ts-ignore
    // this.api.getCargoOverview(decode(localStorage.getItem('token')).username)
    this.api.getCargoOverview()
      .subscribe({
        next: (res) => {
          // @ts-ignore
          this.dataSource = new MatTableDataSource<CargoOverviewDTO>(res);
          this.dataSource.paginator = this.paginator;
          this.dataSource.sort = this.sort;
        },
        error: () => {
          this.snackbar.open("Error while fetching the record!!",
            'Error', {duration: 2000});
        }
      })
  }

  isUserShipment() {
    return this.userRole === '[ROLE_SHIPMENT_COMPANY]';
  }
}
