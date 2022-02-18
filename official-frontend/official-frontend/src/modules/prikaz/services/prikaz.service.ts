import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
var o2x = require('object-to-xml');

@Injectable({
  providedIn: 'root'
})
export class PrikazService {

  public headers = new HttpHeaders({ "Content-Type": "application/xml" });
  private parser = new DOMParser();
  private serializer = new XMLSerializer();

  constructor(public http: HttpClient) { }

  getPotvrdaHtmlTransformation(search: any): Observable<any> {
    var xmlDoc = this.parser.parseFromString(o2x(search), "text/xml");
    var xmlString = this.serializer.serializeToString(xmlDoc);
    return this.http.post<any>("/api/v1/html_transformation/potvrda", xmlString, {
      headers: this.headers,
      responseType: 'test/xml' as 'json'
    })
  }

  getDigitalniHtmlTransformation(search: any): Observable<any> {
    var xmlDoc = this.parser.parseFromString(o2x(search), "text/xml");
    var xmlString = this.serializer.serializeToString(xmlDoc);
    return this.http.post<any>("/api/v1/html_transformation/digitalni", xmlString, {
      headers: this.headers,
      responseType: 'test/xml' as 'json'
    })
  }

  getSearchByText(searchDTO: any,path: any): Observable<any> {
    const item = localStorage.getItem("user") || "";
    const decodedItem = JSON.parse(item);
    this.headers.append("Authorization Bearer", decodedItem.usertokenstate.accessToken)
    var xmlDoc = this.parser.parseFromString(o2x(searchDTO), "text/xml");
    var xmlString = this.serializer.serializeToString(xmlDoc);

    return this.http.post<any>(`/api/v1/search/${path}/search_by_text`, xmlString, {
      headers: this.headers,
      responseType: 'test/xml' as 'json'
    })
  }
  
}