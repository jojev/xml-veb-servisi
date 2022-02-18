import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { PrikazRoutingModule } from './prikaz-routing.module';
import { PrikazComponent } from './prikaz/prikaz.component';


@NgModule({
  declarations: [
    PrikazComponent
  ],
  imports: [
    CommonModule,
    PrikazRoutingModule
  ]
})
export class PrikazModule { }
