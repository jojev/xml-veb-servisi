import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { DocumentsPreviewComponent } from './pages/documents-preview/documents-preview.component';
import { RegistrationComponent } from './pages/registration/registration.component';

const routes: Routes = [
  {
    path: "registration",
    pathMatch: "full",
    component: RegistrationComponent,
  },
  {
    path: "review",
    pathMatch: "full",
    component: DocumentsPreviewComponent,
  }
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class UserRoutingModule { }
