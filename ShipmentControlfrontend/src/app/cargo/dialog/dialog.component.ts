import {CargoTypeService} from "../../services/cargoType.service";
import {Component, Inject, OnInit} from '@angular/core';
import {FormGroup, FormBuilder, Validators, FormControl} from "@angular/forms";
import {MatDialogRef,MAT_DIALOG_DATA} from '@angular/material/dialog'
import {CargoTypeDto} from "../../model/cargoType.dto";
import {MatSnackBar} from "@angular/material/snack-bar";


@Component({
  selector: 'app-dialog',
  templateUrl: './dialog.component.html',
  styleUrls: ['./dialog.component.css']
})
export class DialogComponent implements OnInit {


  cargoTypeForm !: FormGroup;
  actionBtn: string = "Save"

  constructor(private formBuilder: FormBuilder,
              private api: CargoTypeService,
              @Inject(MAT_DIALOG_DATA) public editData: any,
              private dialogRef: MatDialogRef<DialogComponent>,
              private snackBar:MatSnackBar) {
  }

  ngOnInit(): void {
    this.cargoTypeForm = this.formBuilder.group({
      name: new FormControl('', [Validators.required])

    });

    if (this.editData) {
      this.actionBtn = "Update";
      this.cargoTypeForm.controls['name'].setValue(this.editData.name);
    }
  }

  addCargoType() {
    if (!this.editData) {
      if (this.cargoTypeForm.valid) {
        const data: CargoTypeDto = {
          name: this.cargoTypeForm.controls['name'].value
        }
        this.api.addCargoType(data)
          .subscribe({
            next: () => {
              this.snackBar.open("Created Successfully",'Dismiss');
              this.cargoTypeForm.reset();
              this.dialogRef.close('save');
            },
            error: () => {
              this.snackBar.open("Error while adding the product",'Dismiss');
            }
          })
      }
    } else {
      this.updateCargoType();
    }
  }
  updateCargoType(){
    this.api.putCargoType(this.cargoTypeForm.value, this.editData.id)
      .subscribe({
        next: () => {
          this.snackBar.open("Updated Successfully",'Dismiss');
          this.cargoTypeForm.reset();
          this.dialogRef.close('update');
        },
        error: () => {
          this.snackBar.open("Error while updating",'Dismiss');
        }
      })
  }
}




