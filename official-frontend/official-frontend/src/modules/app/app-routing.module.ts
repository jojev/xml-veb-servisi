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
    path: "izvestaj",
    children: [
      {
        path: "",
        loadChildren: () =>
          import("./../izvestaj/izvestaj.module").then((m) => m.IzvestajModule),
      }
    ]
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
  {
    path: "prikaz",
    children: [
      {
        path: "",
        loadChildren: () =>
          import("./../prikaz/prikaz.module").then((m) => m.PrikazModule),
      },
    ],
  },
  {
    path: "potvrda",
    children: [
      {
        path: "",
        loadChildren: () =>
          import("./../potvrda/potvrda.module").then((m) => m.PotvrdaModule),
      },
    ],
  },
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
