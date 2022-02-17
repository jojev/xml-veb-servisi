import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { InteresovanjeRoutingModule } from './interesovanje-routing.module';
import { InteresovanjeFormComponent } from './pages/interesovanje-form/interesovanje-form.component';
import { MatCardModule } from '@angular/material/card';
import { MatFormFieldModule } from '@angular/material/form-field';
import { ReactiveFormsModule } from '@angular/forms';
import { MatInputModule } from '@angular/material/input';
import { MatOptionModule } from '@angular/material/core';
import {MatSelectModule} from '@angular/material/select';


@NgModule({
  declarations: [
    InteresovanjeFormComponent
  ],
  imports: [
    CommonModule,
    InteresovanjeRoutingModule,
    MatCardModule,
    MatFormFieldModule,
    ReactiveFormsModule,
    MatInputModule,
    MatOptionModule,
    MatSelectModule
  ]
})
export class InteresovanjeModule { }
