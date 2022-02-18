import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { ZahtevTableComponent } from './pages/zahtev-table/zahtev-table.component';

const routes: Routes = [
  {
    path: "table",
    pathMatch: "full",
    component: ZahtevTableComponent,
  },
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class ZahtevRoutingModule { }
