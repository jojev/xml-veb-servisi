import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { DocumentsTableComponent } from './pages/documents-table/documents-table.component';
import { MetadataSearchComponent } from './pages/metadata-search/metadata-search.component';

const routes: Routes = [
  {
    path: "documents",
    pathMatch: "full",
    component: DocumentsTableComponent,
  },
  {
    path: "metadata",
    pathMatch: "full",
    component: MetadataSearchComponent,
  },
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class SearchRoutingModule { }
