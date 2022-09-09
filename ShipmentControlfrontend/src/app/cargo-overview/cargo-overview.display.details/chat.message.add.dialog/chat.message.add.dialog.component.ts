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
    private cargoChatMessageLogService:CargoChatMessageLogService,
    private formBuilder: FormBuilder,
    @Inject(MAT_DIALOG_DATA) public editData: any,
    private dialogRef: MatDialogRef<ChatMessageAddDialogComponent>,
    private snackBar: MatSnackBar
  ) { }

  ngOnInit(): void {
    this.chatMessageForm = this.formBuilder.group({
      messageText: new FormControl('', [Validators.required])

    });

    if (this.editData) {
      this.actionBtn = "Update";
      this.chatMessageForm.controls['messageText'].setValue(this.editData.messageText);
    }
  }

  addChatMessageLog() {
    if (!this.editData) {
      if (this.chatMessageForm.valid) {
        const data: CargoChatMessageLogCommand = {
          messageText: this.chatMessageForm.controls['messageText'].value,
          messageFrom:"test",
          senderRole:"test",
          cargoId:1
        }
        this.cargoChatMessageLogService.addCargoChatLogs(data)
          .subscribe({
            next: () => {
              this.snackBar.open("Created Successfully", 'Ok', {duration: 2000});
              this.chatMessageForm.reset();
              this.dialogRef.close('save');
            },
            error: () => {
              this.snackBar.open("Error while adding the product", 'Error', {duration: 2000});
            }
          })
      }
    } else {
      this.updateMessage();
    }




  }

  private updateMessage() {
    this.cargoChatMessageLogService.updateCargoChatLogs(this.editData.cargoId,this.chatMessageForm.value )
      .subscribe({
        next: () => {
          this.snackBar.open("Updated Successfully", 'Ok', {duration: 2000});
          this.chatMessageForm.reset();
          this.dialogRef.close('update');
        },
        error: () => {
          this.snackBar.open("Error while updating", 'Error', {duration: 2000});
        }
      })
  }


}
