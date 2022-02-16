import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';

const routes: Routes = [
  {
    path: "auth",
    children: [
      {
        path: "",
        loadChildren: () =>
          import("./../auth/auth.module").then((m) => m.AuthModule),
      },
    ],
  },
  {
    path: "vaccine-status",
    children: [
      {
        path: "",
        loadChildren: () =>
          import("./../vaccine-status/vaccine-status.module").then((m) => m.VaccineStatusModule),
      },
    ],
  },
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
