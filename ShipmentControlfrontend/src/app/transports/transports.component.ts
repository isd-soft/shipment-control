import {Component, OnInit} from '@angular/core';
import {TransportDto} from "../model/transport.dto";
import {TransportsService} from "../services/transports.service";
import {MatTableDataSource} from "@angular/material/table";
import {SelectionModel} from "@angular/cdk/collections";

@Component({
  selector: 'app-transports',
  templateUrl: './transports.component.html',
  styleUrls: ['./transports.component.css']
})
export class TransportsComponent implements OnInit {

  displayedColumns: string[] = ['select', 'transportName', 'transportType', 'routeId', 'cargoTypes'];
  dataSource: MatTableDataSource<TransportDto>;
  selection = new SelectionModel<TransportDto>(true, []);

  constructor(private transportService: TransportsService) {
    this.dataSource = new MatTableDataSource();
  }

  ngOnInit(): void {
    this.transportService.getTransports().subscribe(data => {
      this.dataSource.data = data;
    });
  }

  applyFilter(event: Event) {
    const filterValue = (event.target as HTMLInputElement).value;
    this.dataSource.filter = filterValue.trim().toLowerCase();
  }

  isAllSelected() {
    const numSelected = this.selection.selected.length;
    const numRows = this.dataSource.data.length;
    return numSelected === numRows;
  }

  masterToggle() {
    this.isAllSelected() ?
      this.selection.clear() :
      this.dataSource.data.forEach(row => this.selection.select(row));
  }
}


