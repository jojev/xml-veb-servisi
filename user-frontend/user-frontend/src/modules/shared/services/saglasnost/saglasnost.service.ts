import { HttpHeaders, HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
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

  getAllSaglasnostByJmbg(search: any): Observable<any> {
    var xmlDoc = this.parser.parseFromString(o2x(search), "text/xml");
    var xmlString = this.serializer.serializeToString(xmlDoc);
    return this.http.post<any>("/api/v1/preview/obrazac/search_jmbg", xmlString, {
      headers: this.headers,
      responseType: 'test/xml' as 'json'
    })
  }

  getHtmlTransformation(search: any): Observable<any> {
    var xmlDoc = this.parser.parseFromString(o2x(search), "text/xml");
    var xmlString = this.serializer.serializeToString(xmlDoc);
    return this.http.post<any>("/api/v1/html_transformation/saglasnost", xmlString, {
      headers: this.headers,
      responseType: 'test/xml' as 'json'
    })
  }
}
