import {AfterViewInit, Component, OnInit, ViewChild} from '@angular/core';
import {TransportDto} from "../model/transport.dto";
import {TransportsService} from "../services/transports.service";
import {MatTableDataSource} from "@angular/material/table";
import {SelectionModel} from "@angular/cdk/collections";
import {MatSort} from '@angular/material/sort';
import {MatPaginator} from "@angular/material/paginator";
import {MatSnackBar} from "@angular/material/snack-bar";

@Component({
  selector: 'app-transports',
  templateUrl: './transports.component.html',
  styleUrls: ['./transports.component.css']
})
export class TransportsComponent implements OnInit, AfterViewInit {

  displayedColumns: string[] = ['transportName', 'transportType', 'routeId', 'cargoTypes', 'update', 'delete'];
  dataSource: MatTableDataSource<TransportDto>;
  selection = new SelectionModel<TransportDto>(true, []);
  @ViewChild('empTbSort') empTbSort = new MatSort();
  @ViewChild('paginator') paginator: MatPaginator;

  constructor(private transportService: TransportsService,
              private snackbar: MatSnackBar) {
    this.dataSource = new MatTableDataSource();
  }

  ngOnInit(): void {
    this.getAllTransports();
  }

  applyFilter(event: Event) {
    const filterValue = (event.target as HTMLInputElement).value;
    this.dataSource.filter = filterValue.trim().toLowerCase();
  }

  public redirectToUpdate = (id: string) => {

  }
  public redirectToDelete = (id: number) => {
    this.transportService.deleteTransports(id).subscribe({
      next: () => {
        this.snackbar.open("Deleted Successfully", 'Dismiss')
        this.getAllTransports();
      },
      error: () => {
        this.snackbar.open("Error while deleting the Transport", 'Dismiss');
      }
    })
  }
getAllTransports(){
  return this.transportService.getTransports().subscribe(data => {
    this.dataSource.data = data;
    this.dataSource.sort = this.empTbSort;
    this.dataSource.paginator = this.paginator;
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
}


