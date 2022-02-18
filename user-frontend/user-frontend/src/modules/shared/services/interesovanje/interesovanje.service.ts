import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { SearchDTO } from '../../dto/SearchDTO';

var o2x = require('object-to-xml');

@Injectable({
  providedIn: 'root'
})
export class InteresovanjeService {

  public headers = new HttpHeaders({ "Content-Type": "application/xml"});
  private parser = new DOMParser();
  private serializer = new XMLSerializer();

  constructor(public http: HttpClient) { }

  getAllInteresovanjeByJmbg(search: any): Observable<any> {
    var xmlDoc = this.parser.parseFromString(o2x(search), "text/xml");
    var xmlString = this.serializer.serializeToString(xmlDoc);
    return this.http.post<any>("/api/v1/preview/interesovanje/search_jmbg", xmlString, {
      headers: this.headers,
      responseType: 'test/xml' as 'json'
    })
  }

  getHtmlTransformation(search: any): Observable<any> {
    var xmlDoc = this.parser.parseFromString(o2x(search), "text/xml");
    var xmlString = this.serializer.serializeToString(xmlDoc);
    return this.http.post<any>("/api/v1/html_transformation/interesovanje", xmlString, {
      headers: this.headers,
      responseType: 'test/xml' as 'json'
    })
  }

  getPdfTransformation(search: any): Observable<Blob> {
    var xmlDoc = this.parser.parseFromString(o2x(search), "text/xml");
    var xmlString = this.serializer.serializeToString(xmlDoc);
    return this.http.post<Blob>("/api/v1/xslfo_transformation/interesovanje", xmlString, {
      headers: this.headers,
      responseType: 'blob' as 'json'
    })
  }
  
}

