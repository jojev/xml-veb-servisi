import { HttpHeaders, HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class IzvjestajService {
  public headers = new HttpHeaders({ "Content-Type": "application/xml"});
  private parser = new DOMParser();
  private serializer = new XMLSerializer();

  constructor(public http: HttpClient) { }

  create(periodOd: any, periodDo: any): Observable<any> {
    return this.http.get<any>("/api/v1/izvjestaj?startDate=" + periodOd + "&endDate=" + periodDo, {
      headers: this.headers,
      responseType: 'test/xml' as 'json'
    })
  }
  
}
