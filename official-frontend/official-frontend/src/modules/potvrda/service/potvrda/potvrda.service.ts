import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';

var o2x = require('object-to-xml');

@Injectable({
  providedIn: 'root'
})
export class PotvrdaService {
  public headers = new HttpHeaders({ "Content-Type": "application/xml"});
  private parser = new DOMParser();
  private serializer = new XMLSerializer();

  constructor(public http: HttpClient) { }

  createPotvrdaOVakcinaciji(potvrda: any): Observable<any> {
    var xmlDoc = this.parser.parseFromString(o2x(potvrda), "text/xml");
    const korisnikNode = xmlDoc?.getElementsByTagName("potvrda_o_vakcinaciji")[0]
    korisnikNode?.setAttribute("xmlns", "http://www.ftn.uns.ac.rs/potvrda_o_vakcinaciji")
    korisnikNode?.setAttribute("xmlns:xsi", "http://www.w3.org/2001/XMLSchema-instance")
    korisnikNode?.setAttribute("xsi:schemaLocation", "sema")
  
    var xmlString = this.serializer.serializeToString(xmlDoc);

    return this.http.post<any>("api/v1/potvrda-o-vakcinaciji/", xmlString, {
      headers: this.headers,
      responseType: 'test/xml' as 'json'
    })
  }
}
