import {Component, OnInit, ViewChild} from '@angular/core';
import {ActivatedRoute, Router} from "@angular/router";
import {MatTableDataSource} from "@angular/material/table";
import {LegDto} from "../../model/leg.dto";
import {CargoDto} from "../../model/cargo.dto";
import {MatPaginator} from "@angular/material/paginator";
import {MatSort,} from "@angular/material/sort";
import {RouteService} from "../../services/route.service";
import {MatSnackBar} from "@angular/material/snack-bar";
import {CargoService} from "../../services/cargoService";
import {
  RouteConfirmDialogComponent,
  RouteConfirmDialogModel
} from "../../route/route.confirm.dialog/route.confirm.dialog.component";
import {MatDialog} from "@angular/material/dialog";
import {EventLogDto} from "../../model/eventLog.Dto";
import {EventLogService} from "../../services/eventLog.service";
import decode from "jwt-decode";
import {CargoChatMessageLogDto} from "../../model/cargoChatMessageLogDto";
import {CargoChatMessageLogService} from "../../services/cargoChatMessageLog.service";
import {ChatMessageAddDialogComponent} from "./chat.message.add.dialog/chat.message.add.dialog.component";


export interface CargoDetails {
  name: string;
  content: string;
}


@Component({
  selector: 'app-cargo-overview.display.details',
  templateUrl: './cargo-overview.display.details.component.html',
  styleUrls: ['./cargo-overview.display.details.component.css']
})
export class CargoOverviewDisplayDetailsComponent implements OnInit {

  cargoDetails: CargoDetails[];
  cargoDetailsDisplayedColumns: string[] = ['name', 'content'];
  legDisplayedColumns: string [] = ['name', 'address', 'country', 'countryCode', 'price', 'currency'];
  eventLogDisplayColumns: string [] = ['createdAt', 'eventType', 'cargoStatus', 'leg'];
  legDataSource: MatTableDataSource<LegDto>;
  dataSource: CargoDto;
  matTableDataSource: MatTableDataSource<CargoDto>;
  chatLogDataSource: CargoChatMessageLogDto[];
  eventLogDataSource: MatTableDataSource<EventLogDto>;
  @ViewChild('paginator') paginator: MatPaginator;
  @ViewChild('empTbSort') sort = new MatSort();
  @ViewChild('legPaginator') legPaginator: MatPaginator;
  @ViewChild('legSort') legSort = new MatSort();
  @ViewChild('eventPaginator') eventPaginator: MatPaginator;
  @ViewChild('eventSort') eventSort: MatSort;
  @ViewChild('chatPaginator') chatPaginator: MatPaginator;
  // @ts-ignore
  userRole = decode(localStorage.getItem('token')).sub;
  cargoStatusString: string;
  cargoStatusANALYZING = "ANALYZING";
  cargoStatusARRIVED = "ARRIVED";

  shipmentRole = "SHIPMENT_COMPANY";
  goodsRole = "GOODS_COMPANY";
  currentChatItemsToShow: CargoChatMessageLogDto[];

  constructor(
    private routeService: RouteService,
    private cargoService: CargoService,
    private snackbar: MatSnackBar,
    private router: ActivatedRoute,
    private route: Router,
    private dialog: MatDialog,
    private dialog2: MatDialog,
    private cargoChatMessageLogService: CargoChatMessageLogService,
    private eventLogService: EventLogService
  ) {
    this.legDataSource = new MatTableDataSource();

  }

  cargoId = this.router.snapshot.params["id"];
  trackingNumber: string;


  ngOnInit(): void {
    this.getCargoById();
    this.getCargoChatMessageLogsById();

  }

  getCargoTypeNames(element: any): string {
    let cargoTypes = "";

    element.forEach(name => {
      cargoTypes += name.name + ", ";
    })
    return cargoTypes;
  }

  show(): boolean {
    return this.userRole === '[ROLE_SHIPMENT_COMPANY]'
      && this.cargoStatusANALYZING === this.cargoStatusString;
  }

  messageFromInit(): string {
    if (this.userRole === '[ROLE_SHIPMENT_COMPANY]') {
      return this.shipmentRole;
    }
    return this.goodsRole;

  }

  getTrackingNumber() {
    if (this.dataSource.trackingNumber === "" || this.dataSource.trackingNumber === null) {
      this.trackingNumber = "No tracking number provided";
    } else {
      this.trackingNumber = this.dataSource.trackingNumber;
    }
    return this.trackingNumber;
  }

  getCargoById() {
    this.cargoService.getCargoById(this.router.snapshot.params["id"])
      .subscribe({
        next: (res) => {
          this.dataSource = res;
          this.cargoDetails = [
            {name: "Cargo Status", content: this.dataSource.cargoStatus.toString()},
            {name: "Tracking Number", content: this.getTrackingNumber()},
            {name: "Booking Date", content: this.dataSource.bookingDate.toString()},
            {name: "Total Volume", content: this.dataSource.totalVolume.toString()},
            {name: "Total Weight", content: this.dataSource.totalWeight.toString()},
            {name: "Cargo Types", content: this.getCargoTypeNames(this.dataSource.cargoTypes)},
            {name: "Origin", content: this.dataSource.origin},
            {name: "Destination", content: this.dataSource.destination},
            {name: "Estimate time for delivering", content: res.itineraryDTO.executionTime.toString()}
          ];
          console.log(res.itineraryDTO.legDTOS)
          this.legDataSource = new MatTableDataSource<LegDto>(res.itineraryDTO.legDTOS);
          this.legDataSource.paginator = this.legPaginator;
          this.legDataSource.sort = this.legSort;
          this.getAllEventTypeLogs(this.dataSource.trackingNumber);
          this.cargoStatusString = this.dataSource.cargoStatus;
          // console.log(res);
        },
        error: () => {
          this.snackbar.open("Error while fetching the the record!!", 'Error', {duration: 2000});

        }
      })
  }

  getCargoChatMessageLogsById() {
    this.cargoChatMessageLogService.getCargoChatLogs(this.cargoId).subscribe({
      next: (chat) => {
        this.chatLogDataSource = chat;
        this.currentChatItemsToShow = this.chatLogDataSource;
      },
      error: () => {
        this.snackbar.open("No chat logged!", 'Ok', {duration: 2000});
      }
    });
  }

  getAllCargo() {
    return this.cargoService.getCargo().subscribe({
      next: (data) => {

        this.matTableDataSource = new MatTableDataSource<CargoDto>(data);
        this.matTableDataSource.paginator = this.paginator;
        this.matTableDataSource.sort = this.sort;
      },
      error: () => {
        this.snackbar.open("Error while fetching the record!!", 'Error', {duration: 2000});
      }
    });
  }

  getAllEventTypeLogs(trackingNumber: string) {
    return this.eventLogService.getEventLogs(trackingNumber).subscribe({
      next: (res) => {

        this.eventLogDataSource = new MatTableDataSource<EventLogDto>(res);
        this.eventLogDataSource.paginator = this.eventPaginator;
        this.eventLogDataSource.sort = this.eventSort;

      },
      error: () => {
        this.snackbar.open("No events logged!", 'Ok', {duration: 2000});
      }
    });
  }


  redirectToApprove() {
    console.log("status Analyzing you clicked Approve");
    const message = `Are you sure you want to Approve?`;
    const dialogData = new RouteConfirmDialogModel("Confirm Action", message);
    const dialogRef = this.dialog.open(RouteConfirmDialogComponent, {
      maxWidth: "400px",
      data: dialogData
    });

    dialogRef.afterClosed().subscribe(dialogResult => {
      if (dialogResult) {
        this.cargoService.approveCargo(this.cargoId)
          .subscribe({
            next: () => {
              this.snackbar.open("Executed Successfully, the cargo status changed to PREPARING", 'Ok', {duration: 6000})
              this.getAllCargo();
              location.reload();
            },
            error: () => {
              this.snackbar.open("Error while executing", 'Error', {duration: 2000});
            }
          })

      }
    });

  }

  redirectToCargoOverview() {
    // @ts-ignore
    this.route.navigate(['dashboard', 'cargo']);
  }

  redirectToCargoOverviewDetails() {
    this.route.navigate(['dashboard', 'cargo' + this.cargoId]);
  }


  redirectToReject() {
    const message = `Are you sure you want to Reject?`;
    const dialogData = new RouteConfirmDialogModel("Confirm Action", message);
    const dialogRef = this.dialog.open(RouteConfirmDialogComponent, {
      maxWidth: "400px",
      data: dialogData
    });

    dialogRef.afterClosed().subscribe(dialogResult => {
      if (dialogResult) {
        this.cargoService.rejectCargo(this.cargoId)
          .subscribe({
            next: () => {
              this.snackbar.open("Executed Successfully, the cargo was rejected", 'Ok', {duration: 6000})
              this.redirectToCargoOverview();

            },
            error: () => {
              this.snackbar.open("Error while executing", 'Error', {duration: 2000});
            }
          })
      }
    });
  }
  openToAddMessage() {
    this.dialog2.open(ChatMessageAddDialogComponent, {
      width: '50%',
      data: {
        cargoId: this.cargoId,
        messageFrom: this.messageFromInit(),
        senderRole: this.userRole
      }
    }).afterClosed().subscribe(value => {

      this.getCargoChatMessageLogsById();
    })
  }

  openToEditMessage(msg: any) {
    this.dialog2.open(ChatMessageAddDialogComponent, {
      width: '50%',
      data: {
        chatId: msg.chatId,
        cargoId: this.cargoId,
        messageText: msg.messageText,
      }
    }).afterClosed().subscribe(value => {
      this.getCargoChatMessageLogsById();

    })
  }

  deleteMessageLog(chatId: number) {
    const message = `Are you sure you want to DELETE?`;
    const dialogData = new RouteConfirmDialogModel("Confirm Action", message);
    const dialogRef = this.dialog.open(RouteConfirmDialogComponent, {
      maxWidth: "400px",
      data: dialogData
    });

    dialogRef.afterClosed().subscribe(dialogResult => {
      if (dialogResult) {
        this.cargoChatMessageLogService.deleteCargoChatLogs(chatId)
          .subscribe({
            next: () => {
              this.snackbar.open("Executed Successfully", 'Ok', {duration: 4000})
              window.location.reload();
            },
            error: () => {
              this.snackbar.open("You can't delete that!", 'Forbidden', {duration: 6000});

            }
          })
      }
    });

  }

  onPageChange($event) {
    this.currentChatItemsToShow = this.chatLogDataSource.slice($event.pageIndex * $event.pageSize,
      $event.pageIndex * $event.pageSize + $event.pageSize);
  }


}

