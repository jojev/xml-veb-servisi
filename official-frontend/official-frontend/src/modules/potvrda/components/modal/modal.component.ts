import { Component, Inject, OnInit, Optional } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { MatDialogRef, MAT_DIALOG_DATA } from '@angular/material/dialog';
import { Doza } from '../../model/Doza';

@Component({
  selector: 'app-modal',
  templateUrl: './modal.component.html',
  styleUrls: ['./modal.component.scss']
})
export class ModalComponent implements OnInit {
  form: FormGroup;
  doza: Doza = {
    datum_davanja: new Date(),
    serija: ''
  };

  constructor(private dialogRef: MatDialogRef<ModalComponent>,
        @Optional() @Inject(MAT_DIALOG_DATA) public mydata: any) {
        this.form = new FormGroup({
          datum_davanja: new FormControl(null, Validators.required),
          serija: new FormControl('', Validators.required)
        });
      }

  ngOnInit(): void {
  }

  closeDialog() { this.dialogRef.close({ event: 'close'}); }

  save() {
    this.doza = {
      ...this.form.value
    };
    this.dialogRef.close({ event: 'add', data: this.doza });
  }

  public errorHandling = (control: string, error: string) => {
    return this.form.controls[control].hasError(error) && this.form.get(control)?.touched;
  }
}
