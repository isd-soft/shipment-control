import {AfterViewInit, Component, OnInit, ViewChild} from '@angular/core';
import {MatTableDataSource} from "@angular/material/table";
import {SelectionModel} from "@angular/cdk/collections";
import {MatSort} from "@angular/material/sort";
import {MatPaginator} from "@angular/material/paginator";
import {CargoDTO} from "../model/cargoOverview.dto";
import {TransportsService} from "../services/transports.service";

@Component({
  selector: 'app-cargo-overview',
  templateUrl: './cargo-overview.component.html',
  styleUrls: ['./cargo-overview.component.css']
})
export class CargoOverviewComponent implements OnInit {

  displayedColumns: string[] = ['trackingNumber', 'destination', 'cargoStatus'];
  dataSource: MatTableDataSource<CargoDTO>;
  selection = new SelectionModel<CargoDTO>(true, []);
  @ViewChild('empTbSort') empTbSort = new MatSort();
  @ViewChild('paginator') paginator: MatPaginator;

  constructor() { }

  ngOnInit(): void {
  }

}
