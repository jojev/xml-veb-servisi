import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
var o2x = require('object-to-xml');

@Injectable({
  providedIn: 'root'
})
export class ZahtevService {
  public headers = new HttpHeaders({ "Content-Type": "application/xml" });
  private parser = new DOMParser();
  private serializer = new XMLSerializer();

  constructor(public http: HttpClient) { }


  getPendingZahtev(): Observable<any> {
    const item = localStorage.getItem("user") || "";
    const decodedItem = JSON.parse(item);
    this.headers.append("Authorization Bearer", decodedItem.usertokenstate.accessToken)
    return this.http.get<any>("/api/v1/zahtev_za_sertifikat/pending", {
      headers: this.headers,
      responseType: 'test/xml' as 'json'
    })
  }

  sendResponse(reason: any): Observable<any> {
    const item = localStorage.getItem("user") || "";
    const decodedItem = JSON.parse(item);
    this.headers.append("Authorization Bearer", decodedItem.usertokenstate.accessToken)

    var xmlDoc = this.parser.parseFromString(o2x(reason), "text/xml");
    var xmlString = this.serializer.serializeToString(xmlDoc);

    return this.http.post<any>("/api/v1/zahtev_za_sertifikat/odgovor", xmlString, {
      headers: this.headers,
      responseType: 'test/xml' as 'json'
    })
  }
}

