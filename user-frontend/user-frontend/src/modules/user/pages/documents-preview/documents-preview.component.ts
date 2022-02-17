import { Component, OnInit } from '@angular/core';
import { InteresovanjeZaVakcinisanje } from 'src/modules/shared/model/InteresovanjeZaVakcinisanje';
import { DigitalniService } from 'src/modules/shared/services/digitalni/digitalni.service';
import { InteresovanjeService } from 'src/modules/shared/services/interesovanje/interesovanje.service';
import { PotvrdaService } from 'src/modules/shared/services/potvrda/potvrda.service';
import { SaglasnostService } from 'src/modules/shared/services/saglasnost/saglasnost.service';
import { ZahtevService } from 'src/modules/shared/services/zahtev-za-sertifikat/zahtev.service';
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
  displayedColumns: string[] = ['dokument','jmbg', 'datum', "prikaz"];
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
          _this.interesovanjaList = result.interesovanjeList.interesovanje_za_vakcinisanje;
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
          _this.zahtevi = result.zahtevList.zahtev_za_izdavanje_sertifikata;
        })
      }
    ) 
  }

  getSaglasnost(): void {
    const _this = this;
    this.saglasnostService.getAllSaglasnostByJmbg(this.searchDTO).subscribe(
      (result) => {
        console.log(result);
        this.parser.parseString(result, function(err: any, res: any) {
          result = res;
          _this.saglasnosti = result.obrazacList.obrazac_za_sprovodjenje_imunizacije;
          console.log(_this.saglasnosti);
        })
      }
    ) 
  }
  getDigitalni(): void {
    const _this = this;
    this.digitalniService.getAllDigitalniByJmbg(this.searchDTO).subscribe(
      (result) => {
        console.log(result);
        this.parser.parseString(result, function(err: any, res: any) {
          result = res;
          _this.digitalni = result.digitalniSertifikatList.digitalni_zeleni_sertifikat;
          console.log(_this.digitalni);
        })
      }
    ) 
  }
  getPotvrda(): void {
    const _this = this;
    this.potvrdaService.getAllPotvrdaByJmbg(this.searchDTO).subscribe(
      (result) => {
        console.log(result);
        this.parser.parseString(result, function(err: any, res: any) {
          result = res;
          _this.potvrde = result.potvrdaOVakcinacijiList.potvrda_o_vakcinaciji;
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
        console.log(result);
        (document.getElementById("preview") as any).innerHTML = result;
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
        console.log(result);
        (document.getElementById("preview") as any).innerHTML = result;
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
        console.log(result);
        (document.getElementById("preview") as any).innerHTML = result;
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
        console.log(result);
        (document.getElementById("preview") as any).innerHTML = result;
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
        console.log(result);
        (document.getElementById("preview") as any).innerHTML = result;
      }
    )
  }
}
