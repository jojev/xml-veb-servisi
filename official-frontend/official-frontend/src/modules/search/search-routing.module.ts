import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { DocumentsTableComponent } from './pages/documents-table/documents-table.component';

const routes: Routes = [
  {
    path: "documents",
    pathMatch: "full",
    component: DocumentsTableComponent,
  },
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class SearchRoutingModule { }
