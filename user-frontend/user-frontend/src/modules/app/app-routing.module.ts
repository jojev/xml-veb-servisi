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
        path: "user",
        loadChildren: () =>
          import("./../user/user.module").then((m) => m.UserModule),
      },
      {
        path: "interesovanje",
        loadChildren: () =>
          import("./../interesovanje/interesovanje.module").then((m) => m.InteresovanjeModule),
      },
      {
        path: "zahtev",
        loadChildren: () =>
          import("./../zahtev/zahtev.module").then((m) => m.ZahtevModule),
      },
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
