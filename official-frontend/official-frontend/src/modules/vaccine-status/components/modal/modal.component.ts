import { Component, Inject, OnInit, Optional } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import {MAT_DIALOG_DATA, MatDialogRef} from "@angular/material/dialog";

@Component({
  selector: 'app-modal',
  templateUrl: './modal.component.html',
  styleUrls: ['./modal.component.scss']
})
export class ModalComponent implements OnInit {
  form: FormGroup;
  
  constructor(
        private dialogRef: MatDialogRef<ModalComponent>,
        @Optional() @Inject(MAT_DIALOG_DATA) public mydata: any) {
        this.form = new FormGroup({
          kolicina: new FormControl(null, Validators.required),
        });
    }

  ngOnInit(): void {
  }

  closeDialog() { this.dialogRef.close({ event: 'close'}); }

  save() {
    this.dialogRef.close({ event: 'update', data: this.form.value.kolicina });
  }

  public errorHandling = (control: string, error: string) => {
    return this.form.controls[control].hasError(error) && this.form.get(control)?.touched;
  }
}
