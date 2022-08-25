import {AfterViewInit, Component, OnInit, ViewChild} from '@angular/core';
import {TransportDto} from "../model/transport.dto";
import {TransportsService} from "../services/transports.service";
import {MatTableDataSource} from "@angular/material/table";
import {SelectionModel} from "@angular/cdk/collections";
import {MatSort} from '@angular/material/sort';
import {MatPaginator} from "@angular/material/paginator";
import {MatSnackBar} from "@angular/material/snack-bar";
import {MatDialog} from "@angular/material/dialog";
import {TransportsDialogComponent} from "./transports.dialog/transports.dialog.component";

@Component({
  selector: 'app-transports',
  templateUrl: './transports.component.html',
  styleUrls: ['./transports.component.css']
})
export class TransportsComponent implements OnInit, AfterViewInit {

  displayedColumns: string[] = ['transportName', 'transportType', 'cargoTypes', 'update', 'delete'];
  dataSource: MatTableDataSource<TransportDto>;
  selection = new SelectionModel<TransportDto>(true, []);
  @ViewChild('empTbSort') empTbSort = new MatSort();
  @ViewChild('paginator') paginator: MatPaginator;

  constructor(private transportService: TransportsService,
              private dialog: MatDialog,
              private snackbar: MatSnackBar) {
    this.dataSource = new MatTableDataSource();
  }

  ngOnInit(): void {
    this.getAllTransports();
  }

  reload() {
    this.getAllTransports();
  }

  applyFilter(event: Event) {
    const filterValue = (event.target as HTMLInputElement).value;
    this.dataSource.filter = filterValue.trim().toLowerCase();
  }

  public redirectToUpdate = (row: any) => {
    this.dialog.open(TransportsDialogComponent, {
      width: '30%',
      data: row
    }).afterClosed().subscribe(value => {
      if (value === 'update') {
        this.getAllTransports();
      }
    })

  }
  public redirectToDelete = (id: number) => {
    this.transportService.deleteTransports(id).subscribe({
      next: () => {
        this.snackbar.open("Deleted Successfully", 'Dismiss', {duration: 2000})
        this.getAllTransports();
      },
      error: () => {
        this.snackbar.open("Error while deleting the Transport", 'Dismiss');
      }
    })
  }

  getAllTransports() {
    return this.transportService.getTransports().subscribe({
      next: (data) => {

        this.dataSource = new MatTableDataSource<TransportDto>(data);
        this.dataSource.paginator = this.paginator;
        this.dataSource.sort = this.empTbSort;
      },
      error: () => {
        this.snackbar.open("Error while fetching the the record!!", 'Error', {duration: 2000});
      }
    });
  }

  getCargoTypeNames(element: any): string {
    let cargoTypes = "";

    element.cargoTypes.forEach(name => {
      cargoTypes += name.name + ", ";
    })
    return cargoTypes;
  }

  ngAfterViewInit(): void {

  }

  onRowClicked(row) {
    console.log('Row clicked: ', row);
  }

  openDialog() {
    this.dialog.open(TransportsDialogComponent, {
      width: '30%'
    }).afterClosed().subscribe(value => {
      if (value === 'save') {
        this.getAllTransports();
      }
    })
  }
}


