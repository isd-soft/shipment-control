import {AfterViewInit, Component, OnInit, ViewChild} from '@angular/core';
import {MatTable, MatTableDataSource} from "@angular/material/table";
import {SelectionModel} from "@angular/cdk/collections";
import {MatSort} from "@angular/material/sort";
import {MatPaginator} from "@angular/material/paginator";
import {CargoOverviewDTO} from "../model/cargoOverview.dto";
import {MatDialog} from "@angular/material/dialog";
import {CargoOverviewService} from "../services/cargoOverview.service";
import {MatSnackBar} from "@angular/material/snack-bar";
import {DialogCargoOverviewComponent} from "./dialog/dialogCargoOverview.component";
import {ConfirmDialogCargoComponent , ConfirmDialogCargoModel} from "../cargo-overview/dialog/confirm-dialogCargo.component";
import {Router} from "@angular/router";
import {CargoDto} from "../model/cargo.dto";
import {CargoService} from "../services/cargoService";
import {MatInput} from "@angular/material/input";


@Component({
  selector: 'app-cargo-overview',
  templateUrl: './cargo-overview.component.html',
  styleUrls: ['./cargo-overview.component.css']
})
export class CargoOverviewComponent implements OnInit {

  displayedColumns: string[] = ['goodsCompanyName', 'bookingDate', 'trackingNumber','origin', 'destination', 'cargoStatus','action'];
  dataSource: MatTableDataSource<CargoOverviewDTO>;
  selection = new SelectionModel<CargoOverviewDTO>(true, []);

  @ViewChild(MatPaginator) paginator!: MatPaginator;
  @ViewChild(MatSort) sort!: MatSort;

  constructor(private dialog :MatDialog ,
              private api : CargoOverviewService,
              private cargoService: CargoService,
              private snackbar:MatSnackBar,
              private router: Router) {

  }

  ngOnInit(): void {
    // this.getCargoInfo();
    this.getAllCargoOverview();
  }

  reload() {
    // this.getCargoInfo();
    this.getAllCargoOverview();
  }

  applyFilter(event: Event) {
    const filterValue = (event.target as HTMLInputElement).value;
    this.dataSource.filter = filterValue.trim().toLowerCase();
  }

  openDetails(row) {
    this.router.navigateByUrl('/dashboard/cargo/details/' + row.id);
    console.log('selected cargo: ', row);
  }

  // trackingNumber: string;
  // getTrackingNumber(){
  //   if(this.dataSource['trackingNumber'] === "" || this.dataSource['trackingNumber'] === null){
  //     console.log("No tracking number provided");
  //     this.trackingNumber = "No tracking number provided";
  //   } else {
  //     console.log("there is a tracking number");
  //     this.trackingNumber = this.dataSource['trackingNumber'];
  //   }
    // return this.trackingNumber;
  // }

/*  openDialog() {
    this.dialog.open(DialogCargoOverviewComponent, {
      width:'30%'
    }).afterClosed().subscribe(value => {
      if(value === 'save'){
        this.getAllCargoOverview();
      }
    })
  }*/

  getAllCargoOverview(){
    this.api.getCargoOverview()
      .subscribe({
        next:(res)=>{
          this.dataSource =new MatTableDataSource<CargoOverviewDTO>(res);
          this.dataSource.paginator = this.paginator;
          this.dataSource.sort = this.sort;
          // console.log("res =")
          // console.log(res);
        },
        error : ()=>{
          this.snackbar.open("Error while fetching the record!!",'Error',{duration:2000});
        }
      })
    // this.cargoService.findAllCargoes()
    //     .subscribe({
    //       next:(res2)=>{
    //         this.dataSource2 = new MatTableDataSource<CargoDto>(res2);
    //         // console.log("res2 =")
    //         // console.log(res2);
    //       },
    //       error:()=>{
    //         console.log("cant fetch cargoDto");
    //         this.snackbar.open("error while fetching cargoDTO", "Error", {duration:2000});
    //       }
    //     })
  }

  // getCargoInfo(){
  //   this.cargoService.findAllCargoes()
  //       .subscribe({
  //         next:(res2)=>{
  //           this.dataSource2 = new MatTableDataSource<CargoDto>(res2);
  //           // console.log("res2 =")
  //           // console.log(res2);
  //         },
  //         error:()=>{
  //           console.log("cant fetch cargoDto");
  //           this.snackbar.open("error while fetching cargoDTO", "Error", {duration:2000});
  //         }
  //       })
  // }

  editCargoOverview(row :any){
    this.dialog.open(DialogCargoOverviewComponent,{
      width:'30%',
      data:row
    }).afterClosed().subscribe(value => {
      if(value === 'update'){
        this.getAllCargoOverview();
      }
    })
  }


  deleteCargoOverview(id: number) {
    this.confirmDialog(id);
  }



  result: boolean;
  confirmDialog(id: number): void {
    const message = `Are you sure you want to delete this?`;
    const dialogDataCargo = new ConfirmDialogCargoModel("Confirm Action", message);
    const dialogRef = this.dialog.open(ConfirmDialogCargoComponent, {
      maxWidth: "400px",
      data: dialogDataCargo
    });

    dialogRef.afterClosed().subscribe(dialogResultCargo => {
      if (dialogResultCargo) {
        this.api.deleteCargoOverview(id)
          .subscribe({
            next: () => {
              this.ngOnInit();
              this.snackbar.open("Deleted Successfully", 'Ok', {duration: 2000})
            },
            error: () => {
              this.snackbar.open("Error while deleting the Cargo", 'Error', {duration: 2000});
            }
          });
      }
    });
  }

}
