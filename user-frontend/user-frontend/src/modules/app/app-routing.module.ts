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
    path: "user",
      children: [
        {
          path: "",
          loadChildren: () =>
            import("./../user/user.module").then((m) => m.UserModule),
        },
      ],
  },
  {
    path: "interesovanje",
      children: [
        {
          path: "",
          loadChildren: () =>
            import("./../interesovanje/interesovanje.module").then((m) => m.InteresovanjeModule),
        },
      ],
  }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
