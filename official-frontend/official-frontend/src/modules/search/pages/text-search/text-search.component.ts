import { Component, OnInit } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import * as x2js from 'xml2js';
import { SearchService } from '../../services/search.service';

@Component({
  selector: 'app-text-search',
  templateUrl: './text-search.component.html',
  styleUrls: ['./text-search.component.scss']
})
export class TextSearchComponent implements OnInit {
  parser = new x2js.Parser();
  domParser = new DOMParser();
  o2x = require('object-to-xml');

  search: string = " ";

  displayedColumns: string[] = ['dokument', 'id', 'prikaz'];
  dataSource: any[] = [];
  all: number = 0;
  documents: any[] = [
    {value: 'obrazac', viewValue: 'Obrazac za saglasnost o vakcinisanju'},
    {value: 'potvrda_o_vakcinaciji',viewValue: 'Potvrda o vakcinaciji'},
    {value: 'digitalni_sertifikat', viewValue: 'Digitalni sertifikat'},
    {value: 'zahtev_za_sertifikat',viewValue: 'Zahtev za digitalni sertifikat'},
    {value: 'interesovanje', viewValue: 'Interesovanje za vakcinisanje'},
  ];
  form: FormGroup;
  constructor(private searchService: SearchService) { 
    this.form = new FormGroup({
      text: new FormControl('', [Validators.required]),
      documentType: new FormControl('', [Validators.required])

    });
  }

  ngOnInit(): void {
  }


  submit(): void {
    this.search = this.form.value['text'];
    var docType = this.form.get('documentType')?.value
    var obj = {
      searchdto: {
        '#': {
          search: this.search,
        }
      }
    };
    this.dataSource = [];
    this.searchService.getSearchByText(obj,docType).subscribe(
      (result) => {
        this.parser.parseString(result, (err: any, res: any) => {
          result = res;
          if(docType==="interesovanje"){
            if (result.interesovanjeList.interesovanje_za_vakcinisanje) {
              for (var i = 0; i < result.interesovanjeList.interesovanje_za_vakcinisanje.length; i++) {
                const id = result.interesovanjeList.interesovanje_za_vakcinisanje[i].$.about.split('/')[5];
                var object = { dokument: 'Interesovanje', id: id }
                this.dataSource.push(object);
              }
            }
            this.all = 1;
          }
          if(docType==="obrazac"){
            if (result.obrazacList.obrazac_za_sprovodjenje_imunizacije) {
              for (var i = 0; i < result.obrazacList.obrazac_za_sprovodjenje_imunizacije.length; i++) {
                const id = result.obrazacList.obrazac_za_sprovodjenje_imunizacije[i].$.about.split('/')[4];
                var object = { dokument: 'Obrazac za sprovođenje imunizacije', id: id }
                this.dataSource.push(object);
              }
            }
            this.all = 1;
          }
          if(docType==="zahtev_za_sertifikat"){
            if (result.zahtevList.zahtev_za_izdavanje_sertifikata) {
              for (var i = 0; i < result.zahtevList.zahtev_za_izdavanje_sertifikata.length; i++) {
                const id = result.zahtevList.zahtev_za_izdavanje_sertifikata[i].$.about.split('/')[5];
                var object = { dokument: 'Zahtev za izdavanje digitalnog sertifikata', id: id }
                this.dataSource.push(object);
              }
            }
            this.all = 1;
          }
          if(docType==="digitalni_sertifikat"){
            if (result.digitalniSertifikatList.digitalni_zeleni_sertifikat) {
              for (var i = 0; i < result.digitalniSertifikatList.digitalni_zeleni_sertifikat.length; i++) {
                const id = result.digitalniSertifikatList.digitalni_zeleni_sertifikat[i].$.about.split('/')[5];
                var object = { dokument: 'Digitalni zeleni sertifikat', id: id }
                this.dataSource.push(object);
              }
            }
            this.all = 1;
          }
          if(docType==="potvrda_o_vakcinaciji"){
            if (result.potvrdaOVakcinacijiList.potvrda_o_vakcinaciji) {
              for (var i = 0; i < result.potvrdaOVakcinacijiList.potvrda_o_vakcinaciji.length; i++) {
                const id = result.potvrdaOVakcinacijiList.potvrda_o_vakcinaciji[i].$.about.split('/')[5];
                var object = { dokument: 'Potvrda o vakcinaciji', id: id }
                this.dataSource.push(object);
              }
            }
            this.all = 1;
          }
        }
        );
      })
    
  }

  showDocument(element: any): void {
    let documentId = element.id;
    const searchDTO = { searchdto: { search: documentId } };
    if (this.form.get('documentType')?.value === 'interesovanje') {
      this.searchService.getInteresovanjeHtmlTransformation(searchDTO).subscribe(
        (result) => {

          (document.getElementById("preview") as any).innerHTML = result;
        }
      )
    } else if (this.form.get('documentType')?.value == 'obrazac') {
      this.searchService.getSaglasnostHtmlTransformation(searchDTO).subscribe(
        (result) => {

          (document.getElementById("preview") as any).innerHTML = result;
        }
      )
    }
    else if (this.form.get('documentType')?.value == 'zahtev_za_sertifikat') {
      this.searchService.getZahtevHtmlTransformation(searchDTO).subscribe(
        (result) => {

          (document.getElementById("preview") as any).innerHTML = result;
        }
      )
    }
    else if (this.form.get('documentType')?.value == 'potvrda_o_vakcinaciji') {
      this.searchService.getPotvrdaHtmlTransformation(searchDTO).subscribe(
        (result) => {

          (document.getElementById("preview") as any).innerHTML = result;
        }
      )
    } else if (this.form.get('documentType')?.value == 'digitalni_sertifikat') {
      this.searchService.getDigitalniHtmlTransformation(searchDTO).subscribe(
        (result) => {

          (document.getElementById("preview") as any).innerHTML = result;
        }
      )
    }
  }

}
