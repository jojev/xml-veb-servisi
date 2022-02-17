import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { ZahtevFormComponent } from './pages/zahtev-form/zahtev-form.component';
const routes: Routes = [
  {
    path: "form",
    pathMatch: "full",
    component: ZahtevFormComponent,
  },
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class ZahtevRoutingModule { }
