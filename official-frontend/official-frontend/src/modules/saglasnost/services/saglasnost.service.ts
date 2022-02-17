import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Console } from 'console';
import { Observable } from 'rxjs';

var o2x = require('object-to-xml');
@Injectable({
  providedIn: 'root'
})
export class SaglasnostService {
  public headers = new HttpHeaders({ "Content-Type": "application/xml"});
  private parser = new DOMParser();
  private serializer = new XMLSerializer();

  constructor(public http: HttpClient) { }

  create(saglasnost: any,jmbg:any):  Observable<any> {
    var xmlDoc = this.parser.parseFromString(o2x(saglasnost), "text/xml");
    const interesovanjeNode = xmlDoc?.getElementsByTagName("obrazac_za_sprovodjenje_imunizacije")[0]
    interesovanjeNode?.setAttribute("xmlns:xsi", "http://www.w3.org/2001/XMLSchema-instance")
    interesovanjeNode?.setAttribute("xmlns", "http://www.ftn.uns.ac.rs/obrazac_za_sprovodjenje_imunizacije")
    var xmlString = this.serializer.serializeToString(xmlDoc);
    xmlString = xmlString.replace("<podaci_koje_je_popunio_zdravstveni_radnik", '<podaci_koje_je_popunio_zdravstveni_radnik xmlns="http://www.ftn.uns.ac.rs/obrazac_za_sprovodjenje_imunizacije"');
    console.log(xmlString)
    return this.http.put<any>(`/api/v1/saglasnost/${jmbg}`, xmlString, {
      headers: this.headers,
      responseType: 'test/xml' as 'json'
    })
    
  }

  
}