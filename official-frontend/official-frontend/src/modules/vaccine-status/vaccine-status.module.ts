import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { VaccineStatusRoutingModule } from './vaccine-status-routing.module';
import { VaccineStatusViewAndUpdateComponent } from './pages/vaccine-status-view-and-update/vaccine-status-view-and-update.component';
import { MatCardModule } from '@angular/material/card';
import { MatFormFieldModule } from '@angular/material/form-field'
import { ReactiveFormsModule } from "@angular/forms";
import { MatInputModule } from '@angular/material/input'
import { MatTableModule } from '@angular/material/table'
import { MatButtonModule } from '@angular/material/button';
import { ModalComponent } from './components/modal/modal.component';
import { MatDialogModule } from "@angular/material/dialog"


@NgModule({
  declarations: [
    VaccineStatusViewAndUpdateComponent,
    ModalComponent
  ],
  imports: [
    CommonModule,
    VaccineStatusRoutingModule,
    MatCardModule,
    MatFormFieldModule,
    ReactiveFormsModule,
    MatInputModule,
    MatTableModule,
    MatButtonModule,
    MatDialogModule
  ]
})
export class VaccineStatusModule { }
