import {Component, OnInit, ViewChild} from '@angular/core';
import {MatTableDataSource} from "@angular/material/table";
import {MatSnackBar} from "@angular/material/snack-bar";
import {BookingRequestService} from "../services/BookingRequest.service";
import {BookingRequestDto} from "../model/bookingRequest.dto";
import {MatPaginator} from "@angular/material/paginator";
import {MatSort} from "@angular/material/sort";
import {
  RouteConfirmDialogComponent,
  RouteConfirmDialogModel
} from "../route/route.confirm.dialog/route.confirm.dialog.component";
import {MatDialog} from "@angular/material/dialog";

@Component({
  selector: 'app-booking.request',
  templateUrl: './booking.request.component.html',
  styleUrls: ['./booking.request.component.css']
})
export class BookingRequestComponent implements OnInit {
  displayedColumns: string[] = ['goodsCompanyName', 'requestedDay', 'routeDescription', 'sanction', 'refuse'];

  dataSource: MatTableDataSource<BookingRequestDto>;
  @ViewChild('paginator') paginator: MatPaginator;
  @ViewChild('empTbSort') sort = new MatSort();


  constructor(private snackbar: MatSnackBar,
              private dialog: MatDialog,
              private bookingRequestService: BookingRequestService) {
    this.dataSource = new MatTableDataSource();
  }

  ngOnInit(): void {
    this.getAllBookingRequests();

  }

  getAllBookingRequests() {
    return this.bookingRequestService.getBookingRequest().subscribe({
      next: (data) => {

        this.dataSource = new MatTableDataSource<BookingRequestDto>(data);
        this.dataSource.paginator = this.paginator;
        this.dataSource.sort = this.sort;
      },
      error: () => {
        this.snackbar.open("Error while fetching the record!!", 'Error', {duration: 2000});
      }
    });
  }

  applyFilter(event: Event) {
    const filterValue = (event.target as HTMLInputElement).value;
    this.dataSource.filter = filterValue.trim().toLowerCase();
    if (this.dataSource.paginator) {
      this.dataSource.paginator.firstPage();
    }
  }

  redirectToAccept(id: number): void {
    const message = `Are you sure you want to ACCEPT?`;

    const dialogData = new RouteConfirmDialogModel("Confirm Action", message);

    const dialogRef = this.dialog.open(RouteConfirmDialogComponent, {
      maxWidth: "400px",
      data: dialogData
    });

    dialogRef.afterClosed().subscribe(dialogResult => {
      if (dialogResult) {
        this.bookingRequestService.acceptBookingRequest(id)
          .subscribe({
            next: () => {
              this.snackbar.open("Executed Successfully", 'Ok', {duration: 2000})
              this.getAllBookingRequests();
            },
            error: () => {
              this.snackbar.open("Error while executing", 'Error', {duration: 2000});
            }
          })

      }
    });
  }

  redirectToDeny(id: number): void {
    const message = `Are you sure you want to DENY?`;

    const dialogData = new RouteConfirmDialogModel("Confirm Action", message);

    const dialogRef = this.dialog.open(RouteConfirmDialogComponent, {
      maxWidth: "400px",
      data: dialogData
    });

    dialogRef.afterClosed().subscribe(dialogResult => {
      if (dialogResult) {
        this.bookingRequestService.denyBookingRequest(id)
          .subscribe({
            next: () => {
              this.snackbar.open("Executed Successfully", 'Ok', {duration: 2000})
              this.getAllBookingRequests();
            },
            error: () => {
              this.snackbar.open("Error while executing", 'Error', {duration: 2000});
            }
          })
      }
    });
  }

}
