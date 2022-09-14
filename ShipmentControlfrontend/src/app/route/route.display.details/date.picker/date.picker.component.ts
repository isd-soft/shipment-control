import {Component, Inject, OnInit} from '@angular/core';
import {FormBuilder, FormControl, FormGroup, Validators} from "@angular/forms";
import {MatSnackBar} from "@angular/material/snack-bar";
import {BookingRequestService} from "../../../services/BookingRequest.service";
import {MAT_DIALOG_DATA, MatDialogRef} from "@angular/material/dialog";
import {BookingRequestCommand} from "../../../services/BookingRequestCommand";
import {DatePipe} from "@angular/common";

@Component({
  selector: 'app-date.picker',
  templateUrl: './date.picker.component.html',
  styleUrls: ['./date.picker.component.css']
})
export class DatePickerComponent implements OnInit {
  dateForm!: FormGroup;

  constructor(private snackbar: MatSnackBar,
              private bookingRequestService: BookingRequestService,
              private formBuilder: FormBuilder,
              private datePipe: DatePipe,
              private dialogRef: MatDialogRef<DatePickerComponent>,
              @Inject(MAT_DIALOG_DATA) public data: any) {
  }

  ngOnInit(): void {
    this.dateForm = this.formBuilder.group({
      pickedDate: new FormControl('', [Validators.required])
    });
  }

  dateFilter = (d: Date): boolean => {
    const finalArr = this.data.finalArr;
    const day = d.getDay();
    return day !== finalArr[0] && day !== finalArr[1]
      && day !== finalArr[2] && day !== finalArr[3]
      && day !== finalArr[4] && day !== finalArr[5]
      && day !== finalArr[6]
  }

  addDateRequest() {
    const date = this.dateForm.controls['pickedDate'].value;
    if (date != '') {
      const data: BookingRequestCommand = {
        routeId: this.data.routeId,
        localDateRequested: this.datePipe.transform(date, "yyyy-MM-dd"),
      }
      this.bookingRequestService.addBookingRequest(data)
        .subscribe({
          next: () => {
            this.dialogRef.close("ok");
            this.snackbar.open("The request was sent successfully!", 'Ok', {duration: 2000});
          },
          error: () => {
            this.snackbar.open("Error while requesting the day", 'Error', {duration: 2000});
          }
        })
    } else {
      this.snackbar.open("Please provide a valid day!", 'Error', {duration: 2000});
    }
  }
}
