import {CargoOverviewService} from "../../services/cargoOverview.service";
import {Component, Inject, OnInit} from '@angular/core';
import {FormGroup, FormBuilder, Validators, FormControl} from "@angular/forms";
import {MatDialogRef,MAT_DIALOG_DATA} from '@angular/material/dialog'
import {MatSnackBar} from "@angular/material/snack-bar";
import {CargoDTO} from "../../model/cargoOverview.dto";


@Component({
  selector: 'app-dialog',
  templateUrl: './dialogCargoOverview.component.html',
  styleUrls: ['./dialogCargoOverview.component.css']
})
export class DialogCargoOverviewComponent implements OnInit {


  cargoOverviewForm !: FormGroup;
  actionBtn: string = "Save"

  constructor(private formBuilder: FormBuilder,
              private api: CargoOverviewService,
              @Inject(MAT_DIALOG_DATA) public editData: any,
              private dialogRef: MatDialogRef<DialogCargoOverviewComponent>,
              private snackBar:MatSnackBar) {
  }

  ngOnInit(): void {
    this.cargoOverviewForm = this.formBuilder.group({
      name: new FormControl('', [Validators.required])

    });

    if (this.editData) {
      this.actionBtn = "Update";
      this.cargoOverviewForm.controls['name'].setValue(this.editData.name);
    }
  }

  addCargoOverview() {
    if (!this.editData) {
      if (this.cargoOverviewForm.valid) {
        const data: { trackingNumber: any } = {
          trackingNumber: this.cargoOverviewForm.controls['trackingNumber'].value
        }
        this.api.addCargoOverview(data)
          .subscribe({
            next: () => {
              this.snackBar.open("Created Successfully",'Ok',{duration:2000});
              this.cargoOverviewForm.reset();
              this.dialogRef.close('save');
            },
            error: () => {
              this.snackBar.open("Error while adding the product",'Error',{duration:2000});
            }
          })
      }
    } else {
      this.updateCargoOverview();
    }
  }
  updateCargoOverview(){
    this.api.putCargoOverview(this.cargoOverviewForm.value, this.editData.id)
      .subscribe({
        next: () => {
          this.snackBar.open("Updated Successfully",'Ok',{duration:2000});
          this.cargoOverviewForm.reset();
          this.dialogRef.close('update');
        },
        error: () => {
          this.snackBar.open("Error while updating",'Error',{duration:2000});
        }
      })
  }
}
