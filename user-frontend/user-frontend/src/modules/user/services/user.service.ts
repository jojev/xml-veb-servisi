import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';

var o2x = require('object-to-xml');

@Injectable({
  providedIn: 'root'
})
export class UserService {
  public headers = new HttpHeaders({ "Content-Type": "application/xml"});
  private parser = new DOMParser();
  private serializer = new XMLSerializer();

  constructor(public http: HttpClient) { }

  register(user: any): Observable<any> {
    var xmlDoc = this.parser.parseFromString(o2x(user), "text/xml");
    const korisnikNode = xmlDoc?.getElementsByTagName("korisnik")[0]
    korisnikNode?.setAttribute("xmlns", "http://www.ftn.uns.ac.rs/korisnik")
    korisnikNode?.setAttribute("xmlns:xsi", "http://www.w3.org/2001/XMLSchema-instance")
    korisnikNode?.setAttribute("xsi:schemaLocation", "http://www.ftn.uns.ac.rs/korisnik Faks/CetvrtaGodina/XML/xml-veb-servisi/official-backend/data/schemes/korisnik.xsd")
  
    var xmlString = this.serializer.serializeToString(xmlDoc);
    
    return this.http.post<any>("/api/v1/user", xmlString, {
      headers: this.headers,
      responseType: 'test/xml' as 'json'
    })
  }
}
