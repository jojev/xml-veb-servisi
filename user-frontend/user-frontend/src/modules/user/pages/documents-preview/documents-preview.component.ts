import { Component, OnInit } from '@angular/core';
import { InteresovanjeZaVakcinisanje } from 'src/modules/shared/model/InteresovanjeZaVakcinisanje';
import { DigitalniService } from 'src/modules/shared/services/digitalni/digitalni.service';
import { InteresovanjeService } from 'src/modules/shared/services/interesovanje/interesovanje.service';
import { PotvrdaService } from 'src/modules/shared/services/potvrda/potvrda.service';
import { SaglasnostService } from 'src/modules/shared/services/saglasnost/saglasnost.service';
import { ZahtevService } from 'src/modules/shared/services/zahtev-za-sertifikat/zahtev.service';
import * as download from 'downloadjs';
import * as x2js from 'xml2js';
@Component({
  selector: 'app-documents-preview',
  templateUrl: './documents-preview.component.html',
  styleUrls: ['./documents-preview.component.scss']
})
export class DocumentsPreviewComponent implements OnInit {
  interesovanjaList: InteresovanjeZaVakcinisanje[] = [];
  zahtevi: any = [];
  saglasnosti: any = [];
  digitalni: any = [];
  potvrde: any = [];
  parser = new x2js.Parser();
  domParser = new DOMParser();
  o2x = require('object-to-xml');
  searchDTO: any;
  displayedColumns: string[] = ['dokument','jmbg', 'datum', "prikaz", "pdf"];
  constructor(
    private interesovanjeService: InteresovanjeService,
    private zahtevService: ZahtevService,
    private saglasnostService: SaglasnostService,
    private digitalniService: DigitalniService,
    private potvrdaService: PotvrdaService) { }

  ngOnInit(): void {
    const item = localStorage.getItem("user") || "";
    const decodedItem = JSON.parse(item);
    this.searchDTO = { searchdto: {search: decodedItem.usertokenstate.name[0] || ""}};
    this.getInteresovanje();
    this.getZahtev();
    this.getSaglasnost();
    //this.getDigitalni();
    this.getPotvrda();
    
  }
  getInteresovanje(): void {
    const _this = this;
    this.interesovanjeService.getAllInteresovanjeByJmbg(this.searchDTO).subscribe(
      (result) => {
        this.parser.parseString(result, function(err: any, res: any) {
          result = res;
          if (result.interesovanjeList.interesovanje_za_vakcinisanje.length > 0) {
            _this.interesovanjaList = result.interesovanjeList.interesovanje_za_vakcinisanje;
          }
        })
      }
    ) 
  }
  getZahtev(): void {
    const _this = this;
    this.zahtevService.getAllZahtevByJmbg(this.searchDTO).subscribe(
      (result) => {
        this.parser.parseString(result, function(err: any, res: any) {
          result = res;
          if(result.zahtevList.zahtev_za_izdavanje_sertifikata.length > 0) {
            _this.zahtevi = result.zahtevList.zahtev_za_izdavanje_sertifikata;
          }
        })
      }
    ) 
  }

  getSaglasnost(): void {
    const _this = this;
    this.saglasnostService.getAllSaglasnostByJmbg(this.searchDTO).subscribe(
      (result) => {
        this.parser.parseString(result, function(err: any, res: any) {
          result = res;
          if(result.obrazacList.obrazac_za_sprovodjenje_imunizacije.length > 0) {
            _this.saglasnosti = result.obrazacList.obrazac_za_sprovodjenje_imunizacije;
          }
        })
      }
    ) 
  }
  getDigitalni(): void {
    const _this = this;
    this.digitalniService.getAllDigitalniByJmbg(this.searchDTO).subscribe(
      (result) => {
        this.parser.parseString(result, function(err: any, res: any) {
          result = res;
          if(result.length > 0) {
            _this.digitalni = result.digitalniSertifikatList.digitalni_zeleni_sertifikat;
            console.log(_this.digitalni);
          }
        })
      }
    ) 
  }
  getPotvrda(): void {
    const _this = this;
    this.potvrdaService.getAllPotvrdaByJmbg(this.searchDTO).subscribe(
      (result) => {
        this.parser.parseString(result, function(err: any, res: any) {
          result = res;
          if(result.potvrdaOVakcinacijiList.potvrda_o_vakcinaciji.length > 0) {
            _this.potvrde = result.potvrdaOVakcinacijiList.potvrda_o_vakcinaciji;
          }
          
        })
      }
    ) 
  }

  showInteresovanje(element: any) {
    let tokens : string[] = element.$.about.toString().split("/");
    let documentId = tokens[tokens.length-1];
    console.log(documentId);
    const searchDTO = { searchdto: {search: documentId}};
    this.interesovanjeService.getHtmlTransformation(searchDTO).subscribe(
      (result) => {
        
        (document.getElementById("preview") as any).innerHTML = result;
        download( result, "interesovanje.html", "text/plain" );
      }
    )
  }

  showZahtev(element: any): void {
    let tokens : string[] = element.$.about.toString().split("/");
    let documentId = tokens[tokens.length-1];
    console.log(documentId);
    const searchDTO = { searchdto: {search: documentId}};
    this.zahtevService.getHtmlTransformation(searchDTO).subscribe(
      (result) => {
        
        (document.getElementById("preview") as any).innerHTML = result;
        download( result, "zahtev.html", "text/plain" );
      }
    )
  }
  showSaglasnost(element: any): void {
    let tokens : string[] = element.$.about.toString().split("/");
    let documentId = tokens[tokens.length-1];
    console.log(documentId);
    const searchDTO = { searchdto: {search: documentId}};
    this.saglasnostService.getHtmlTransformation(searchDTO).subscribe(
      (result) => {
        
        (document.getElementById("preview") as any).innerHTML = result;
        download( result, "saglasnost.html", "text/plain" );
      }
    )
  }
  showPotvrda(element: any): void {
    let tokens : string[] = element.$.about.toString().split("/");
    let documentId = tokens[tokens.length-1];
    console.log(documentId);
    const searchDTO = { searchdto: {search: documentId}};
    this.potvrdaService.getHtmlTransformation(searchDTO).subscribe(
      (result) => {
        (document.getElementById("preview") as any).innerHTML = result;
        download( result, "potvrda.html", "text/plain" );
      }
    )
  }
  showDigitalni(element: any): void {
    let tokens : string[] = element.$.about.toString().split("/");
    let documentId = tokens[tokens.length-1];
    console.log(documentId);
    const searchDTO = { searchdto: {search: documentId}};
    this.digitalniService.getHtmlTransformation(searchDTO).subscribe(
      (result) => {
        (document.getElementById("preview") as any).innerHTML = result;
        download( result, "digitalni.html", "text/plain" );
      }
    )
  }

  pdfInteresovanje(element: any) {
    let tokens : string[] = element.$.about.toString().split("/");
    let documentId = tokens[tokens.length-1];
    console.log(documentId);
    const searchDTO = { searchdto: {search: documentId}};
    this.interesovanjeService.getPdfTransformation(searchDTO).subscribe(
      (result) => {
        download( result, "interesovanje.pdf", "application/pdf" );
      }
    )
  }

  pdfZahtev(element: any): void {
    let tokens : string[] = element.$.about.toString().split("/");
    let documentId = tokens[tokens.length-1];
    console.log(documentId);
    const searchDTO = { searchdto: {search: documentId}};
    this.zahtevService.getPdfTransformation(searchDTO).subscribe(
      (result) => {
        download( result, "zahtev.pdf", "text/plain" );
      }
    )
  }
  pdfSaglasnost(element: any): void {
    let tokens : string[] = element.$.about.toString().split("/");
    let documentId = tokens[tokens.length-1];
    console.log(documentId);
    const searchDTO = { searchdto: {search: documentId}};
    this.saglasnostService.getPdfTransformation(searchDTO).subscribe(
      (result) => {
        download( result, "saglasnost.pdf", "text/plain" );
      }
    )
  }
  pdfPotvrda(element: any): void {
    let tokens : string[] = element.$.about.toString().split("/");
    let documentId = tokens[tokens.length-1];
    console.log(documentId);
    const searchDTO = { searchdto: {search: documentId}};
    this.potvrdaService.getPdfTransformation(searchDTO).subscribe(
      (result) => {
        download( result, "potvrda.pdf", "text/plain" );
      }
    )
  }
  pdfDigitalni(element: any): void {
    let tokens : string[] = element.$.about.toString().split("/");
    let documentId = tokens[tokens.length-1];
    console.log(documentId);
    const searchDTO = { searchdto: {search: documentId}};
    this.digitalniService.getPdfTransformation(searchDTO).subscribe(
      (result) => {
        download( result, "digitalni.pdf", "text/plain" );
      }
    )
  }
}
