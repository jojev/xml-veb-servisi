import { HttpHeaders, HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';

var o2x = require('object-to-xml');

@Injectable({
  providedIn: 'root'
})
export class IzvjestajService {
  public headers = new HttpHeaders({ "Content-Type": "application/xml"});
  private parser = new DOMParser();
  private serializer = new XMLSerializer();

  constructor(public http: HttpClient) { }

  create(periodOd: any, periodDo: any): Observable<any> {
    return this.http.get<any>("/api/v1/izvestaj?startDate=" + periodOd + "&endDate=" + periodDo, {
      headers: this.headers,
      responseType: 'test/xml' as 'json'
    })
  }
  getHtmlTransformation(search:any): Observable<any> {
    var xmlDoc = this.parser.parseFromString(o2x(search), "text/xml");
    var xmlString = this.serializer.serializeToString(xmlDoc);
    return this.http.post<Blob>("/api/v1/html_transformation/izvestaj", xmlString, {
      headers: this.headers,
      responseType: 'test/xml' as 'json'
    })
  }
  getPdfTransformation(search: any): Observable<Blob> {
    var xmlDoc = this.parser.parseFromString(o2x(search), "text/xml");
    var xmlString = this.serializer.serializeToString(xmlDoc);
    return this.http.post<Blob>("/api/v1/xslfo_transformation/izvestaj", xmlString, {
      headers: this.headers,
      responseType: 'blob' as 'json'
    })
  }
}
