import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { SearchRoutingModule } from './search-routing.module';
import { DocumentsTableComponent } from './pages/documents-table/documents-table.component';
import { MatCardModule } from '@angular/material/card';
import { MatFormFieldModule } from '@angular/material/form-field';
import { ReactiveFormsModule } from '@angular/forms';
import { MatInputModule } from '@angular/material/input';
import { MatSelectModule } from '@angular/material/select';
import { MatOptionModule } from '@angular/material/core';
import { MatTableModule } from '@angular/material/table';
import { MetadataSearchComponent } from './pages/metadata-search/metadata-search.component';


@NgModule({
  declarations: [
    DocumentsTableComponent,
    MetadataSearchComponent
  ],
  imports: [
    CommonModule,
    SearchRoutingModule,
    MatCardModule,
    MatFormFieldModule,
    ReactiveFormsModule,
    MatInputModule,
    MatOptionModule,
    MatSelectModule,
    MatTableModule
  ]
})
export class SearchModule { }
