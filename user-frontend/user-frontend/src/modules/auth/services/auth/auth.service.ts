declare var require: any
import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';

var o2x = require('object-to-xml');

@Injectable({
  providedIn: 'root'
})
export class AuthService {
  public headers = new HttpHeaders({ "Content-Type": "application/xml"});

  constructor(public http: HttpClient) { }

  login(auth: any): Observable<any> {
    return this.http.post<any>("api/v1/auth/login", o2x(auth), {
      headers: this.headers,
      responseType: 'test/xml' as 'json'
    })
  }

  isLoggedIn(): boolean {
    if (!localStorage.getItem("user")) {
      return false;
    }
    return true;
  }

  logout() {
    localStorage.removeItem("role");
    localStorage.removeItem("user");
    localStorage.removeItem("username");
  }
}
