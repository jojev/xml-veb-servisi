import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { PrikazComponent } from './prikaz/prikaz.component';
const routes: Routes = [
  {
    path: ":type/:id",
    pathMatch: "full",
    component: PrikazComponent,
  },
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class PrikazRoutingModule { }
