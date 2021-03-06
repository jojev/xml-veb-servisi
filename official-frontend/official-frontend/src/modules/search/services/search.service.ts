import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
var o2x = require('object-to-xml');

@Injectable({
  providedIn: 'root'
})
export class SearchService {

  public headers = new HttpHeaders({ "Content-Type": "application/xml" });
  private parser = new DOMParser();
  private serializer = new XMLSerializer();

  constructor(public http: HttpClient) { }


  getInteresovanja(searchDTO: any): Observable<any> {
    // const item = localStorage.getItem("user") || "";
    // const decodedItem = JSON.parse(item);
    // this.headers.append("Authorization Bearer", decodedItem.usertokenstate.accessToken)
    var xmlDoc = this.parser.parseFromString(o2x(searchDTO), "text/xml");
    var xmlString = this.serializer.serializeToString(xmlDoc);

    return this.http.post<any>("/api/v1/search/interesovanje/search_jmbg", xmlString, {
      headers: this.headers,
      responseType: 'test/xml' as 'json'
    })
  }
  getSaglasnosti(searchDTO: any): Observable<any> {
    // const item = localStorage.getItem("user") || "";
    // const decodedItem = JSON.parse(item);
    // this.headers.append("Authorization Bearer", decodedItem.usertokenstate.accessToken)
    var xmlDoc = this.parser.parseFromString(o2x(searchDTO), "text/xml");
    var xmlString = this.serializer.serializeToString(xmlDoc);

    return this.http.post<any>("/api/v1/search/obrazac/search_jmbg", xmlString, {
      headers: this.headers,
      responseType: 'test/xml' as 'json'
    })
  }

  getPotvrde(searchDTO: any): Observable<any> {
    // const item = localStorage.getItem("user") || "";
    // const decodedItem = JSON.parse(item);
    // this.headers.append("Authorization Bearer", decodedItem.usertokenstate.accessToken)
    var xmlDoc = this.parser.parseFromString(o2x(searchDTO), "text/xml");
    var xmlString = this.serializer.serializeToString(xmlDoc);

    return this.http.post<any>("/api/v1/search/potvrda_o_vakcinaciji/search_jmbg", xmlString, {
      headers: this.headers,
      responseType: 'test/xml' as 'json'
    })
  }

  getZahtevi(searchDTO: any): Observable<any> {
    // const item = localStorage.getItem("user") || "";
    // const decodedItem = JSON.parse(item);
    // this.headers.append("Authorization Bearer", decodedItem.usertokenstate.accessToken)
    var xmlDoc = this.parser.parseFromString(o2x(searchDTO), "text/xml");
    var xmlString = this.serializer.serializeToString(xmlDoc);

    return this.http.post<any>("/api/v1/search/zahtev_za_sertifikat/search_jmbg", xmlString, {
      headers: this.headers,
      responseType: 'test/xml' as 'json'
    })
  }

  getDigitalni(searchDTO: any): Observable<any> {
    // const item = localStorage.getItem("user") || "";
    // const decodedItem = JSON.parse(item);
    // this.headers.append("Authorization Bearer", decodedItem.usertokenstate.accessToken)
    var xmlDoc = this.parser.parseFromString(o2x(searchDTO), "text/xml");
    var xmlString = this.serializer.serializeToString(xmlDoc);

    return this.http.post<any>("/api/v1/search/digitalni_sertifikat/search_jmbg", xmlString, {
      headers: this.headers,
      responseType: 'test/xml' as 'json'
    })
  }

  getInteresovanjeHtmlTransformation(search: any): Observable<any> {
    var xmlDoc = this.parser.parseFromString(o2x(search), "text/xml");
    var xmlString = this.serializer.serializeToString(xmlDoc);
    return this.http.post<any>("/api/v1/html_transformation/interesovanje", xmlString, {
      headers: this.headers,
      responseType: 'test/xml' as 'json'
    })
  }

  getSaglasnostHtmlTransformation(search: any): Observable<any> {
    var xmlDoc = this.parser.parseFromString(o2x(search), "text/xml");
    var xmlString = this.serializer.serializeToString(xmlDoc);
    return this.http.post<any>("/api/v1/html_transformation/saglasnost", xmlString, {
      headers: this.headers,
      responseType: 'test/xml' as 'json'
    })
  }

  getZahtevHtmlTransformation(search: any): Observable<any> {
    var xmlDoc = this.parser.parseFromString(o2x(search), "text/xml");
    var xmlString = this.serializer.serializeToString(xmlDoc);
    return this.http.post<any>("/api/v1/html_transformation/zahtev", xmlString, {
      headers: this.headers,
      responseType: 'test/xml' as 'json'
    })
  }

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
  
  getInteresovanjeXSLFOTransformation(search: any): Observable<any> {
    console.log("tu");
    var xmlDoc = this.parser.parseFromString(o2x(search), "text/xml");
    var xmlString = this.serializer.serializeToString(xmlDoc);
    return this.http.post<Blob>("/api/v1/xslfo_transformation/interesovanje", xmlString, {
      headers: this.headers,
      responseType:  'blob' as 'json'
    })
  }

  getSaglasnostXSLFOTransformation(search: any): Observable<any> {
    var xmlDoc = this.parser.parseFromString(o2x(search), "text/xml");
    var xmlString = this.serializer.serializeToString(xmlDoc);
    return this.http.post<Blob>("/api/v1/xslfo_transformation/saglasnost", xmlString, {
      headers: this.headers,
      responseType: 'blob' as 'json'
    })
  }

  getZahtevXSLFOTransformation(search: any): Observable<any> {
    var xmlDoc = this.parser.parseFromString(o2x(search), "text/xml");
    var xmlString = this.serializer.serializeToString(xmlDoc);
    return this.http.post<Blob>("/api/v1/xslfo_transformation/zahtev", xmlString, {
      headers: this.headers,
      responseType: 'blob' as 'json'
    })
  }

  getPotvrdaXSLFOTransformation(search: any): Observable<any> {
    var xmlDoc = this.parser.parseFromString(o2x(search), "text/xml");
    var xmlString = this.serializer.serializeToString(xmlDoc);
    return this.http.post<Blob>("/api/v1/xslfo_transformation/potvrda", xmlString, {
      headers: this.headers,
      responseType: 'blob' as 'json'
    })
  }

  getDigitalniXSLFOTransformation(search: any): Observable<any> {
    var xmlDoc = this.parser.parseFromString(o2x(search), "text/xml");
    var xmlString = this.serializer.serializeToString(xmlDoc);
    return this.http.post<Blob>("/api/v1/xslfo_transformation/digitalni", xmlString, {
      headers: this.headers,
      responseType:  'blob' as 'json'
    })
  }

  getMetadataSearch(search: any):Observable<any> {
    var xmlDoc = this.parser.parseFromString(o2x(search), "text/xml");
    var xmlString = this.serializer.serializeToString(xmlDoc);
    return this.http.post<any>("/api/v1/search/metadata", xmlString, {
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


  getMetadataLogicalSearch(search: any):Observable<any> {
    var xmlDoc = this.parser.parseFromString(o2x(search), "text/xml");
    var xmlString = this.serializer.serializeToString(xmlDoc);
    return this.http.post<any>("/api/v1/search/logical", xmlString, {
      headers: this.headers,
      responseType: 'test/xml' as 'json'
    })
    
  }
  getMetadataRDFForInteresovanje(id: any):Observable<any> {
    return this.http.get<any>("/api/v1/interesovanje/metadata/" + id, {
      headers: this.headers,
      responseType: 'test/xml' as 'json'
    })
  }

  getMetadataRDFForSaglasnost(id: any):Observable<any> {
    return this.http.get<any>("/api/v1/saglasnost/metadata/" + id, {
      headers: this.headers,
      responseType: 'test/xml' as 'json'
    })
  }

  getMetadataRDFForPotvrda(id: any):Observable<any> {
    return this.http.get<any>("/api/v1/potvrda-o-vakcinaciji/metadata/" + id, {
      headers: this.headers,
      responseType: 'test/xml' as 'json'
    })
  }

  getMetadataRDFForZahtev(id: any):Observable<any> {
    return this.http.get<any>("/api/v1/zahtev_za_sertifikat/metadata/" + id, {
      headers: this.headers,
      responseType: 'test/xml' as 'json'
    })
  }

  getMetadataRDFForSertifikat(id: any):Observable<any> {
    return this.http.get<any>("/api/v1/digitalni-sertifikat/metadata/" + id, {
      headers: this.headers,
      responseType: 'test/xml' as 'json'
    })
  }

  
  getMetadataJsonForInteresovanje(id: any):Observable<any> {
    return this.http.get<any>("/api/v1/interesovanje/metadata-json/" + id, {
      headers: this.headers,
      responseType: 'test/xml' as 'json'
    })
  }

  getMetadataJsonForSaglasnost(id: any):Observable<any> {
    return this.http.get<any>("/api/v1/saglasnost/metadata-json/" + id, {
      headers: this.headers,
      responseType: 'test/xml' as 'json'
    })
  }

  getMetadataJsonForPotvrda(id: any):Observable<any> {
    return this.http.get<any>("/api/v1/potvrda-o-vakcinaciji/metadata-json/" + id, {
      headers: this.headers,
      responseType: 'test/xml' as 'json'
    })
  }

  getMetadataJsonForZahtev(id: any):Observable<any> {
    return this.http.get<any>("/api/v1/zahtev_za_sertifikat/metadata-json/" + id, {
      headers: this.headers,
      responseType: 'test/xml' as 'json'
    })
  }

  getMetadataJsonForSertifikat(id: any):Observable<any> {
    return this.http.get<any>("/api/v1/digitalni-sertifikat/metadata-json/" + id, {
      headers: this.headers,
      responseType: 'test/xml' as 'json'
    })
  }

  getWhereInteresovanjeReferenced(id: any): Observable<any> {
    return this.http.get<any>("/api/v1/interesovanje/referenced/" + id, {
      headers: this.headers,
      responseType: 'test/xml' as 'json'
    })
  }

  getWhereSaglasnostReferenced(id: any): Observable<any> {
    return this.http.get<any>("/api/v1/saglasnost/referenced/" + id, {
      headers: this.headers,
      responseType: 'test/xml' as 'json'
    })
  }

  getWhereZahtevReferenced(id: any): Observable<any> {
    return this.http.get<any>("/api/v1/zahtev_za_sertifikat/referenced/" + id, {
      headers: this.headers,
      responseType: 'test/xml' as 'json'
    })
  }

  getWhoReferencedInSaglasnost(id: any): Observable<any> {
    return this.http.get<any>("/api/v1/saglasnost/referencing/" + id, {
      headers: this.headers,
      responseType: 'test/xml' as 'json'
    })
  }

  getWhoReferencedInPotvrda(id: any): Observable<any> {
    return this.http.get<any>("/api/v1/potvrda-o-vakcinaciji/referencing/" + id, {
      headers: this.headers,
      responseType: 'test/xml' as 'json'
    })
  }
}
