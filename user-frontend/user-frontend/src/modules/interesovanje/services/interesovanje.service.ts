import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';

var o2x = require('object-to-xml');

@Injectable({
  providedIn: 'root'
})
export class InteresovanjeService {
  public headers = new HttpHeaders({ "Content-Type": "application/xml"});
  private parser = new DOMParser();
  private serializer = new XMLSerializer();

  constructor(public http: HttpClient) { }

  create(interesovanje: any):  Observable<any> {
    var xmlDoc = this.parser.parseFromString(o2x(interesovanje), "text/xml");
    const interesovanjeNode = xmlDoc?.getElementsByTagName("interesovanje_za_vakcinisanje")[0]
    interesovanjeNode?.setAttribute("xmlns", "http://www.ftn.uns.ac.rs/interesovanje")
    interesovanjeNode?.setAttribute("xmlns:xsi", "http://www.w3.org/2001/XMLSchema-instance")
    interesovanjeNode?.setAttribute("xsi:schemaLocation", "http://www.ftn.uns.ac.rs/interesovanje /xml-veb-servisi/official-backend/data/schemes/interesovanje.xsd")
  
    var xmlString = this.serializer.serializeToString(xmlDoc);
    
    return this.http.post<any>("/api/v1/interesovanje", xmlString, {
      headers: this.headers,
      responseType: 'test/xml' as 'json'
    })
    
  }

 
}
