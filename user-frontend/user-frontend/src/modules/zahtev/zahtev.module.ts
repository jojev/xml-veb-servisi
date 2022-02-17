import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { ZahtevRoutingModule } from './zahtev-routing.module';
import { ZahtevFormComponent } from './pages/zahtev-form/zahtev-form.component';
import { MatCardModule } from '@angular/material/card';
import { MatFormFieldModule } from '@angular/material/form-field';
import { ReactiveFormsModule } from '@angular/forms';
import { MatInputModule } from '@angular/material/input';
import { MatOptionModule } from '@angular/material/core';
import { MatSelectModule } from '@angular/material/select';


@NgModule({
  declarations: [
    ZahtevFormComponent
  ],
  imports: [
    CommonModule,
    ZahtevRoutingModule,
    MatCardModule,
    MatFormFieldModule,
    ReactiveFormsModule,
    MatInputModule,
    MatOptionModule,
    MatSelectModule
  ]
})
export class ZahtevModule { }
