import {Component, OnInit, ViewChild} from '@angular/core';
import {DialogCargoTypeComponent} from "./dialog/dialogCargoType.component";
import {MatDialog} from "@angular/material/dialog";
import {MatTableDataSource} from "@angular/material/table";
import {MatPaginator} from "@angular/material/paginator";
import {MatSort} from "@angular/material/sort";
import {CargoTypeService} from "../services/cargoType.service";
import {CargoTypeDto} from "../model/cargoType.dto";
import {MatSnackBar} from "@angular/material/snack-bar";


@Component({
  selector: 'app-cargo',
  templateUrl: './cargoType.component.html',
  styleUrls: ['./cargoType.component.css']
})
export class CargoTypeComponent implements OnInit {

  displayedColumns: string[] = ['name', 'action'];
  dataSource!: MatTableDataSource<any>;

  @ViewChild(MatPaginator) paginator!: MatPaginator;
  @ViewChild(MatSort) sort!: MatSort;

  constructor(private dialog :MatDialog ,
              private api : CargoTypeService,
              private snackbar:MatSnackBar) { }


  ngOnInit(): void {
    this.getAllCargoType();
  }

  openDialog() {
    this.dialog.open(DialogCargoTypeComponent, {
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
          this.dataSource =new MatTableDataSource<CargoTypeDto>(res);
          this.dataSource.paginator = this.paginator;
          this.dataSource.sort = this.sort;
        },
        error : ()=>{
          this.snackbar.open("Error while fetching the the record!!",'Error',{duration:2000});
        }
      })
  }

  editCargoType(row :any){
    this.dialog.open(DialogCargoTypeComponent,{
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
          this.snackbar.open("Deleted Successfully",'Ok',{duration:2000})
          this.getAllCargoType();
        },
        error : ()=>{
          this.snackbar.open("Error while deleting the CargoType",'Error',{duration:2000});
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
