import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { InteresovanjeFormComponent } from './pages/interesovanje-form/interesovanje-form.component';

const routes: Routes = [
  {
    path: "form",
    pathMatch: "full",
    component: InteresovanjeFormComponent,
  },
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class InteresovanjeRoutingModule { }
