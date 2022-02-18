import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { RootLayoutComponent } from './root-layout/root-layout.component';
const routes: Routes = [
  {
    path: "",
    component: RootLayoutComponent,
    children:[
      {
        path: "saglasnost",
        loadChildren: () =>
          import("./../saglasnost/saglasnost.module").then((m) => m.SaglasnostModule),
      },
      {
        path: "izvestaj",
        loadChildren: () =>
          import("./../izvestaj/izvestaj.module").then((m) => m.IzvestajModule),
      },
      {
        path: "vaccine-status",
        loadChildren: () =>
          import("./../vaccine-status/vaccine-status.module").then((m) => m.VaccineStatusModule),
      },
      {
        path: "zahtev",
        loadChildren: () =>
          import("./../zahtev/zahtev.module").then((m) => m.ZahtevModule),
      },
      {
        path: "search",
        loadChildren: () =>
          import("./../search/search.module").then((m) => m.SearchModule),
      },
      {
        path: "prikaz",
        loadChildren: () =>
          import("./../prikaz/prikaz.module").then((m) => m.PrikazModule),
      },
      {
        path: "potvrda",
        loadChildren: () =>
          import("./../potvrda/potvrda.module").then((m) => m.PotvrdaModule),
      },
    ]
  },
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
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
