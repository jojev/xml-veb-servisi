import { Component, OnInit } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import * as x2js from 'xml2js';
import { SearchService } from '../../services/search.service';
@Component({
  selector: 'app-metadata-search',
  templateUrl: './metadata-search.component.html',
  styleUrls: ['./metadata-search.component.scss']
})
export class MetadataSearchComponent implements OnInit {
  parser = new x2js.Parser();
  domParser = new DOMParser();
  o2x = require('object-to-xml');

  search: string = " ";
  option: string = " ";
  options: any[] = [
    { value: 'interesovanje', viewValue: 'Interesovanje' },
    { value: 'obrazac_za_imunizaciju', viewValue: 'Obrazac za sprovodjenje imunizacije' },
    { value: 'potvrda_o_vakcinaciji', viewValue: 'Potvrda o vakcinaciji' },
    { value: 'zahtev_za_sertifikat', viewValue: 'Zahtev za digitalni sertfikat' },
    { value: 'digitalni_sertifikat', viewValue: 'Digitalni sertifikat' },
  ];
  displayedColumns: string[] = ['dokument', 'id', 'prikaz'];
  dataSource: any[] = [];
  all: number = 0;

  form: FormGroup;
  constructor(private searchService: SearchService) {
    this.form = new FormGroup({
      meta: new FormControl('', [Validators.required,]),
      opcija: new FormControl('', [Validators.required,]),
    });
  }

  ngOnInit(): void {
  }

  metadataSearch(obj: any): void {
    this.searchService.getMetadataSearch(obj).subscribe(
      (result) => {
        this.parser.parseString(result, (err: any, res: any) => {
          result = res;
          if (result.interesovanjeList) {
            for (var i = 0; i < result.interesovanjeList.interesovanje_za_vakcinisanje.length; i++) {
              const id = result.interesovanjeList.interesovanje_za_vakcinisanje[i].$.about.split('/')[5];
              var object = { dokument: 'Interesovanje', id: id }
              this.dataSource.push(object);
              this.all = 1;
            }

          } else if (result.obrazacList) {
            for (var i = 0; i < result.obrazacList.obrazac_za_sprovodjenje_imunizacije.length; i++) {
              const id = result.obrazacList.obrazac_za_sprovodjenje_imunizacije[i].$.about.split('/')[4];
              var object = { dokument: 'Obrazac za sprovođenje imunizacije', id: id }
              this.dataSource.push(object);
              this.all = 1;
            }
          } else if (result.zahtevList) {
            for (var i = 0; i < result.zahtevList.zahtev_za_izdavanje_sertifikata.length; i++) {
              const id = result.zahtevList.zahtev_za_izdavanje_sertifikata[i].$.about.split('/')[5];
              var object = { dokument: 'Zahtev za izdavanje digitalnog sertifikata', id: id }
              this.dataSource.push(object);
              console.log(this.dataSource);
              this.all = 1;
            }
          } else if (result.potvrdaOVakcinacijiList) {
            for (var i = 0; i < result.potvrdaOVakcinacijiList.potvrda_o_vakcinaciji.length; i++) {
              const id = result.potvrdaOVakcinacijiList.potvrda_o_vakcinaciji[i].$.about.split('/')[5];
              var object = { dokument: 'Potvrda o vakcinaciji', id: id }
              this.dataSource.push(object);
              this.all = 1;
            }
          } else if (result.digitalniSertifikatList) {
            for (var i = 0; i < result.digitalniSertifikatList.digitalni_zeleni_sertifikat.length; i++) {
              const id = result.digitalniSertifikatList.digitalni_zeleni_sertifikat[i].$.about.split('/')[5];
              var object = { dokument: 'Digitalni zeleni sertifikat', id: id }
              this.dataSource.push(object);
              this.all = 1;
            }
          }
        }
        );
      })
  }


  submit(): void {
    this.dataSource = [];
    this.all = 0;
    this.search = this.form.value['meta'];
    this.option = this.form.value['opcija'];
    var obj = {
      metadatasearch: {
        '#': {
          search: this.search,
          collection: this.option
        }
      }
    };

    this.metadataSearch(obj);
  }

  showDocument(element: any): void {
    let documentId = element.id;
    const searchDTO = { searchdto: { search: documentId } };
    if (element.dokument === 'Interesovanje') {
      this.searchService.getInteresovanjeHtmlTransformation(searchDTO).subscribe(
        (result) => {

          (document.getElementById("preview") as any).innerHTML = result;
        }
      )
    } else if (element.dokument == 'Obrazac za sprovođenje imunizacije') {
      this.searchService.getSaglasnostHtmlTransformation(searchDTO).subscribe(
        (result) => {

          (document.getElementById("preview") as any).innerHTML = result;
        }
      )
    }
    else if (element.dokument == 'Zahtev za izdavanje digitalnog sertifikata') {
      this.searchService.getZahtevHtmlTransformation(searchDTO).subscribe(
        (result) => {

          (document.getElementById("preview") as any).innerHTML = result;
        }
      )
    }
    else if (element.dokument == 'Potvrda o vakcinaciji') {
      this.searchService.getPotvrdaHtmlTransformation(searchDTO).subscribe(
        (result) => {

          (document.getElementById("preview") as any).innerHTML = result;
        }
      )
    } else if (element.dokument == 'Digitalni zeleni sertifikat') {
      this.searchService.getDigitalniHtmlTransformation(searchDTO).subscribe(
        (result) => {

          (document.getElementById("preview") as any).innerHTML = result;
        }
      )
    }
  }


}
