import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';

var o2x = require('object-to-xml');

@Injectable({
  providedIn: 'root'
})
export class ZahtevService {
  public headers = new HttpHeaders({ "Content-Type": "application/xml"});
  private parser = new DOMParser();
  private serializer = new XMLSerializer();

  constructor(public http: HttpClient) { }

  create(zahtev: any):  Observable<any> {
    var xmlDoc = this.parser.parseFromString(o2x(zahtev), "text/xml");
    const zahtevNode = xmlDoc?.getElementsByTagName("zahtev_za_izdavanje_sertifikata")[0]
    zahtevNode?.setAttribute("xmlns", "http://www.ftn.uns.ac.rs/zahtev_za_sertifikat")
    zahtevNode?.setAttribute("xmlns:xsi", "http://www.w3.org/2001/XMLSchema-instance")
    zahtevNode?.setAttribute("xsi:schemaLocation", "http://www.ftn.uns.ac.rs/zahtev_za_sertifikat /xml-veb-servisi/official-backend/data/schemes/zahtev.xsd")
  
    var xmlString = this.serializer.serializeToString(xmlDoc);
    
    return this.http.post<any>("/api/v1/zahtev_za_sertifikat", xmlString, {
      headers: this.headers,
      responseType: 'test/xml' as 'json'
    })
    
  }

}
