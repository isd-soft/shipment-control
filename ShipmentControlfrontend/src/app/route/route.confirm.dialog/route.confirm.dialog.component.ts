import {Component, Inject, OnInit} from '@angular/core';
import {MAT_DIALOG_DATA, MatDialogRef} from "@angular/material/dialog";


@Component({
  selector: 'app-route.confirm.dialog',
  templateUrl: './route.confirm.dialog.component.html',
  styleUrls: ['./route.confirm.dialog.component.css']
})
export class RouteConfirmDialogComponent implements OnInit {
  title: string;
  message: string;
  constructor(public dialogRef: MatDialogRef<RouteConfirmDialogComponent>,
              @Inject(MAT_DIALOG_DATA) public data: RouteConfirmDialogModel) {
    this.title = data.title;
    this.message = data.message;
  }

  ngOnInit(): void {
  }
  onConfirm(): void {
    // Close the dialog, return true
    this.dialogRef.close(true);
  }

  onDismiss(): void {
    // Close the dialog, return false
    this.dialogRef.close(false);
  }
}

export class RouteConfirmDialogModel {

  constructor(public title: string, public message: string) {
  }
}
