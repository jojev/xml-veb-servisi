import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { VaccineStatusViewAndUpdateComponent } from './pages/vaccine-status-view-and-update/vaccine-status-view-and-update.component';

const routes: Routes = [{
    path: "",
    pathMatch: "full",
    component: VaccineStatusViewAndUpdateComponent,
  },];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class VaccineStatusRoutingModule { }
