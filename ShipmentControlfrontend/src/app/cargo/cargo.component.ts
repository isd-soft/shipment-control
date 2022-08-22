import {Component, OnInit, ViewChild} from '@angular/core';
import {DialogComponent} from "./dialog/dialog.component";
import {MatDialog} from "@angular/material/dialog";
import {MatTableDataSource} from "@angular/material/table";
import {MatPaginator} from "@angular/material/paginator";
import {MatSort} from "@angular/material/sort";
import {CargoTypeService} from "../services/cargoType.service";

@Component({
  selector: 'app-cargo',
  templateUrl: './cargo.component.html',
  styleUrls: ['./cargo.component.css']
})
export class CargoComponent implements OnInit {

  displayedColumns: string[] = ['name', 'action'];
  dataSource!: MatTableDataSource<any>;

  @ViewChild(MatPaginator) paginator!: MatPaginator;
  @ViewChild(MatSort) sort!: MatSort;

  constructor(private dialog :MatDialog , private api : CargoTypeService) { }


  ngOnInit(): void {
    this.getAllCargoType();
  }

  openDialog() {
    this.dialog.open(DialogComponent, {
      width:'30%'
    }).afterClosed().subscribe(value => {
      if(value === 'save'){
        this.getAllCargoType();
      }
    })
  }

  getAllCargoType(){
    this.api.getCargoType()
      .subscribe({
        next:(res)=>{
          this.dataSource =new MatTableDataSource<any>(res);
          this.dataSource.paginator = this.paginator;
          this.dataSource.sort = this.sort;
        },
        error : ()=>{
          alert("Error while fetching the the record!!")
        }
      })
  }

  editCargoType(row :any){
    this.dialog.open(DialogComponent,{
      width:'30%',
      data:row
    }).afterClosed().subscribe(value => {
      if(value === 'update'){
        this.getAllCargoType();
      }
    })
  }

  deleteCargoType(id : number){
    this.api.deleteCargoType(id)
      .subscribe({
        next:()=>{
          alert("Deleted Successfully");
          this.getAllCargoType();
        },
        error : ()=>{
          alert("Error while deleting the route");
        }
      })

  }

  applyFilter(event: Event) {
    const filterValue = (event.target as HTMLInputElement).value;
    this.dataSource.filter = filterValue.trim().toLowerCase();

    if (this.dataSource.paginator) {
      this.dataSource.paginator.firstPage();
    }
  }
}
