import {Component, Inject, OnInit} from '@angular/core';
import {FormBuilder, FormControl, FormGroup, Validators} from "@angular/forms";
import {CargoChatMessageLogService} from "../../../services/cargoChatMessageLog.service";
import {MAT_DIALOG_DATA, MatDialogRef} from "@angular/material/dialog";
import {MatSnackBar} from "@angular/material/snack-bar";
import {CargoChatMessageLogCommand} from "../../../services/CargoChatMessageLogCommand";

@Component({
  selector: 'app-chat.message.add.dialog',
  templateUrl: './chat.message.add.dialog.component.html',
  styleUrls: ['./chat.message.add.dialog.component.css']
})
export class ChatMessageAddDialogComponent implements OnInit {
  chatMessageForm !: FormGroup;
  actionBtn: string = "Save";

  constructor(
    private cargoChatMessageLogService: CargoChatMessageLogService,
    private formBuilder: FormBuilder,
    @Inject(MAT_DIALOG_DATA) public data: any,
    private dialogRef: MatDialogRef<ChatMessageAddDialogComponent>,
    private snackBar: MatSnackBar
  ) {
  }

  ngOnInit(): void {
    this.chatMessageForm = this.formBuilder.group({
      messageText: new FormControl('', [Validators.required])

    });
    if (this.data.messageText) {
      this.actionBtn = "Update";
      this.chatMessageForm.controls['messageText'].setValue(this.data.messageText);
    }
  }

  addChatMessageLog() {
    if (!this.data.messageText) {
      if (this.chatMessageForm.valid) {
        const data: CargoChatMessageLogCommand = {
          messageText: this.chatMessageForm.controls['messageText'].value,
          cargoId: this.data.cargoId
        }
        this.cargoChatMessageLogService.addCargoChatLogs(data)
          .subscribe({
            next: () => {
              this.snackBar.open("Message added, reloading...", 'Ok', {duration: 2000});
              this.chatMessageForm.reset();
              this.dialogRef.close('save');
              window.location.reload();
            },
            error: () => {
              this.snackBar.open("Error while adding", 'Error', {duration: 2000});
            }
          })
      }
    } else {
      this.updateMessage();
    }

  }

  updateMessage() {
    if (this.chatMessageForm.valid) {
      const data: CargoChatMessageLogCommand = {
        messageText: this.chatMessageForm.controls['messageText'].value,
        cargoId: this.data.cargoId
      }
      this.cargoChatMessageLogService.updateCargoChatLogs(this.data.chatId, data)
        .subscribe({
          next: () => {
            this.snackBar.open("Message updated, reloading...", 'Ok', {duration: 2000});
            this.chatMessageForm.reset();
            this.dialogRef.close('update');
            window.location.reload();
          },
          error: () => {
            this.snackBar.open("Error while updating", 'Error', {duration: 2000});
          }
        })
    }
  }
}
