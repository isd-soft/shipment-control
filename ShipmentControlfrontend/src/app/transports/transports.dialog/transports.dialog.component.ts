import {Component, Inject, OnInit} from '@angular/core';
import {FormBuilder, FormControl, FormGroup, Validators} from "@angular/forms";
import {CargoTypeService} from "../../services/cargoType.service";
import {MAT_DIALOG_DATA, MatDialogRef} from "@angular/material/dialog";
import {MatSnackBar} from "@angular/material/snack-bar";
import {TransportsService} from "../../services/transports.service";
import {CargoTypeDto} from "../../model/cargoType.dto";
import {TransportCommand} from "../../services/TransportCommand";
import {TransportTypeDto} from "../../model/transportTypeDto";

@Component({
  selector: 'app-transports.dialog',
  templateUrl: './transports.dialog.component.html',
  styleUrls: ['./transports.dialog.component.css']
})
export class TransportsDialogComponent implements OnInit {
  transportTypeForm !: FormGroup;
  actionBtn: string = "Save"
  cargoTypesValues = new FormControl();
  cargoTypeDtoList: CargoTypeDto[];
  transportTypeDtoList: TransportTypeDto[] = [];

  constructor(private formBuilder: FormBuilder,
              private transportsService: TransportsService,
              @Inject(MAT_DIALOG_DATA) public editData: any,
              private dialogRef: MatDialogRef<TransportsDialogComponent>,
              private snackBar: MatSnackBar,
              private cargoTypeService: CargoTypeService,
  ) {
  }

  ngOnInit(): void {
    this.transportTypeForm = this.formBuilder.group({
      transportName: new FormControl('', [Validators.required]),
      transportType: new FormControl('', [Validators.required]),
     /* cargoTypes: new FormControl( '',[Validators.required]),*/
      routeId: new FormControl('', [Validators.required])
    })
    if (this.editData) {
      this.actionBtn = "Update";
      this.transportTypeForm.controls['transportName'].setValue(this.editData.transportName);
      this.transportTypeForm.controls['routeId'].setValue(this.editData.routeId);
      /*let selectedType = this.transportTypeDtoList.find(value => value.label==this.editData.transportType);
      this.transportTypeForm.controls['transportType'].setValue(selectedType);
    this.transportTypeForm.controls['cargoTypes'].setValue(this.editData.cargoTypes);*/
    }

    this.getAllCargoTypes();
    this.getAllTransportTypes();
  }

  getAllCargoTypes() {
    this.cargoTypeService.getCargoType()
      .subscribe({
        next: (res) => {
          this.cargoTypeDtoList = res;
        }
      });
  }
  addTransports() {
    if (!this.editData) {
      if (this.transportTypeForm.valid) {
        const data: TransportCommand = {
          routeId: this.transportTypeForm.controls['routeId'].value,
          transportName: this.transportTypeForm.controls['transportName'].value,
          transportType: this.transportTypeForm.controls['transportType'].value,
          cargoTypes: this.cargoTypesValues.value,
        }
        this.transportsService.addTransports(data)
          .subscribe({
            next: () => {
              this.snackBar.open("Created Successfully", 'Ok', {duration: 2000});
              this.transportTypeForm.reset();
              this.dialogRef.close('save');
            },
            error: () => {
              this.snackBar.open("Error while adding the product", 'Error', {duration: 2000});
            }
          })
      }
    } else {
      this.updateTransport();
    }
  }

  updateTransport() {
    this.transportTypeForm.value.cargoTypes = this.cargoTypesValues.value;
    this.transportsService.putTransports(this.transportTypeForm.value, this.editData.transportId)
      .subscribe({
        next: () => {
          this.snackBar.open("Updated Successfully", 'Ok', {duration: 2000});
          this.transportTypeForm.reset();
          this.dialogRef.close('update');
        },
        error: () => {
          this.snackBar.open("Error while updating", 'Error', {duration: 2000});
        }
      })
  }

  getAllTransportTypes() {
    this.transportsService.getTransportTypes()
      .subscribe({
        next: (res) => {
          this.transportTypeDtoList = res;
          console.log(this.transportTypeDtoList);
        }
      });
  }
}
