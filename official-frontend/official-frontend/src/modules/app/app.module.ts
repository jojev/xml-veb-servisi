import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { NoopAnimationsModule } from '@angular/platform-browser/animations';
import { HttpClientModule, HTTP_INTERCEPTORS } from '@angular/common/http';
import { InterceptorInterceptor } from '../shared/interceptors/interceptor.interceptor';
import { ToastrModule } from 'ngx-toastr';
import { MatToolbarModule } from '@angular/material/toolbar';
import { MatButtonModule } from '@angular/material/button';
import { RootLayoutComponent } from './root-layout/root-layout.component';
import { NavbarComponent } from './navbar/navbar.component';


@NgModule({
  declarations: [
    AppComponent,
    RootLayoutComponent,
    NavbarComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    NoopAnimationsModule,
    HttpClientModule,
    MatToolbarModule,
    MatButtonModule,
    ToastrModule.forRoot({
      positionClass :'toast-top-center'
    })
  ],
  providers: [{ provide: HTTP_INTERCEPTORS, useClass: InterceptorInterceptor, multi: true }],
  bootstrap: [AppComponent]
})
export class AppModule { }
