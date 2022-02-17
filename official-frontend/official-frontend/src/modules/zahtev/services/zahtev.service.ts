import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class ZahtevService {
  public headers = new HttpHeaders({ "Content-Type": "application/xml"});
  private parser = new DOMParser();
  private serializer = new XMLSerializer();
  
  constructor(public http: HttpClient) { }


  getPendingZahtev(): Observable<any> {
    const item = localStorage.getItem("user") || "";
    const decodedItem = JSON.parse(item);
    console.log(decodedItem.usertokenstate.accessToken)
    this.headers.append("Authorization Bearer", decodedItem.usertokenstate.accessToken)
    return this.http.get<any>("/api/v1/preview/zahtev_za_sertifikat/pending",  {
      headers: this.headers,
      responseType: 'test/xml' as 'json'
    })
  }
}
