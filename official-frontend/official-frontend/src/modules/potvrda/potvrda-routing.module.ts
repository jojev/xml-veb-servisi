import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { CreatePotvrdaComponent } from './pages/create-potvrda/create-potvrda.component';

const routes: Routes = [
  {
    path: "",
    pathMatch: "full",
    component: CreatePotvrdaComponent,
  },
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class PotvrdaRoutingModule { }
