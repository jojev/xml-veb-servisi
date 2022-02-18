import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';

var o2x = require('object-to-xml');

@Injectable({
  providedIn: 'root'
})
export class VaccineStatusService {
  public headers = new HttpHeaders({ "Content-Type": "application/xml"});
  private parser = new DOMParser();
  private serializer = new XMLSerializer();

  constructor(public http: HttpClient) { }

  getVaccineTypes(): Observable<any> {
    return this.http.get<any>("api/v1/vaccine-status", {
      headers: this.headers,
      responseType: 'test/xml' as 'json'
    })
  }

  updateVaccineType(vaccine: any): Observable<any> {
    var xmlDoc = this.parser.parseFromString(o2x(vaccine), "text/xml");
    const korisnikNode = xmlDoc?.getElementsByTagName("stanjeVakcine")[0]
    korisnikNode?.setAttribute("xmlns", "http://www.ftn.uns.ac.rs/stanjeVakcine")
    korisnikNode?.setAttribute("xmlns:xsi", "http://www.w3.org/2001/XMLSchema-instance")
    korisnikNode?.setAttribute("xsi:schemaLocation", "sema")
  
    var xmlString = this.serializer.serializeToString(xmlDoc);

    return this.http.put<any>("api/v1/vaccine-status/" + vaccine.stanjeVakcine.vakcina, xmlString, {
      headers: this.headers,
      responseType: 'test/xml' as 'json'
    })
  }
}
