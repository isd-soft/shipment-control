import {CargoTypeService} from "../../services/cargoType.service";
import {Component, Inject, OnInit} from '@angular/core';
import {FormGroup, FormBuilder, Validators, FormControl} from "@angular/forms";
import {MatDialogRef,MAT_DIALOG_DATA} from '@angular/material/dialog'


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
              private dialogRef: MatDialogRef<DialogComponent>) {
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
        this.api.addCargoType(this.cargoTypeForm.value)
          .subscribe({
            next: () => {
              alert("CargoType added successfully")
              this.cargoTypeForm.reset();
              this.dialogRef.close('save');
            },
            error: () => {
              alert("Error while adding the product")
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
          alert("CargoType updated Successfully");
          this.cargoTypeForm.reset();
          this.dialogRef.close('update');
        },
        error: () => {
          alert("Error while updating ");
        }
      })
  }
}




