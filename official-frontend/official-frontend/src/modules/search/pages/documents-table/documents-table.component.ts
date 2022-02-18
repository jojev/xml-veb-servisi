import { Component, OnInit } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import * as x2js from 'xml2js';
import { SearchService } from '../../services/search.service';
@Component({
  selector: 'app-documents-table',
  templateUrl: './documents-table.component.html',
  styleUrls: ['./documents-table.component.scss']
})
export class DocumentsTableComponent implements OnInit {
  parser = new x2js.Parser();
  domParser = new DOMParser();
  o2x = require('object-to-xml');

  search: string = " ";

  displayedColumns: string[] = ['dokument', 'id', 'prikaz'];
  dataSource: any[] = [];
  all: number = 0;

  form: FormGroup;
  constructor(private searchService: SearchService) {
    this.form = new FormGroup({
      jmbg: new FormControl('', [Validators.required, Validators.pattern('[0-9 ]{13}')]),

    });
  }

  ngOnInit(): void {
  }


  searchInteresovanje(obj: any): void {
    this.searchService.getInteresovanja(obj).subscribe(
      (result) => {
        this.parser.parseString(result, (err: any, res: any) => {
          result = res;
          if (result.interesovanjeList.interesovanje_za_vakcinisanje) {
            for (var i = 0; i < result.interesovanjeList.interesovanje_za_vakcinisanje.length; i++) {
              const id = result.interesovanjeList.interesovanje_za_vakcinisanje[i].$.about.split('/')[5];
              var object = { dokument: 'Interesovanje', id: id }
              this.dataSource.push(object);
            }

          }
          this.searchObrazac(obj);
        }
        );
      })
  }

  searchObrazac(obj: any): void {
    this.searchService.getSaglasnosti(obj).subscribe(
      (result) => {
        this.parser.parseString(result, (err: any, res: any) => {
          result = res;
          if (result.obrazacList.obrazac_za_sprovodjenje_imunizacije) {
            for (var i = 0; i < result.obrazacList.obrazac_za_sprovodjenje_imunizacije.length; i++) {
              const id = result.obrazacList.obrazac_za_sprovodjenje_imunizacije[i].$.about.split('/')[4];
              var object = { dokument: 'Obrazac za sprovođenje imunizacije', id: id }
              this.dataSource.push(object);
            }
          }
          this.searchZahtev(obj);
        }
        );
      })

  }
  searchZahtev(obj: any): void {
    this.searchService.getZahtevi(obj).subscribe(
      (result) => {
        this.parser.parseString(result, (err: any, res: any) => {
          result = res;
          if (result.zahtevList.zahtev_za_izdavanje_sertifikata) {
            for (var i = 0; i < result.zahtevList.zahtev_za_izdavanje_sertifikata.length; i++) {
              const id = result.zahtevList.zahtev_za_izdavanje_sertifikata[i].$.about.split('/')[5];
              var object = { dokument: 'Zahtev za izdavanje digitalnog sertifikata', id: id }
              this.dataSource.push(object);
            }
          }
          this.searchPotvrda(obj);
        }
        );
      })
  }
  searchPotvrda(obj: any): void {
    this.searchService.getPotvrde(obj).subscribe(
      (result) => {
        this.parser.parseString(result, (err: any, res: any) => {
          result = res;
          if (result.potvrdaOVakcinacijiList.potvrda_o_vakcinaciji) {
            for (var i = 0; i < result.potvrdaOVakcinacijiList.potvrda_o_vakcinaciji.length; i++) {
              const id = result.potvrdaOVakcinacijiList.potvrda_o_vakcinaciji[i].$.about.split('/')[5];
              var object = { dokument: 'Potvrda o vakcinaciji', id: id }
              this.dataSource.push(object);
            }
          }
          this.searchDigitalni(obj);
        }
        );
      })
  }

  searchDigitalni(obj: any): void {
    this.searchService.getDigitalni(obj).subscribe(
      (result) => {
        this.parser.parseString(result, (err: any, res: any) => {
          result = res;
          if (result.digitalniSertifikatList.digitalni_zeleni_sertifikat) {
            for (var i = 0; i < result.digitalniSertifikatList.digitalni_zeleni_sertifikat.length; i++) {
              const id = result.digitalniSertifikatList.digitalni_zeleni_sertifikat[i].$.about.split('/')[5];
              var object = { dokument: 'Digitalni zeleni sertifikat', id: id }
              this.dataSource.push(object);
            }
          }
          this.all = 1;
        }
        );
      })
  }

  submit(): void {
    this.search = this.form.value['jmbg'];
    var obj = {
      searchdto: {
        '#': {
          search: this.search,
        }
      }
    };

    this.searchInteresovanje(obj);
    this.dataSource = [];
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