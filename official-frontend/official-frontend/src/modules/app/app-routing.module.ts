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
    path: "saglasnost",
      children: [
        {
          path: "",
          loadChildren: () =>
            import("./../saglasnost/saglasnost.module").then((m) => m.SaglasnostModule),
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
  {
    path: "zahtev",
    children: [
      {
        path: "",
        loadChildren: () =>
          import("./../zahtev/zahtev.module").then((m) => m.ZahtevModule),
      },
    ],
  },
  {
    path: "search",
    children: [
      {
        path: "",
        loadChildren: () =>
          import("./../search/search.module").then((m) => m.SearchModule),
      },
    ],
  },
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
