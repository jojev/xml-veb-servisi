import { Component, OnInit } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import * as x2js from 'xml2js';
import { SearchService } from '../../services/search.service';
import * as download from 'downloadjs';
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

  displayedColumns: string[] = ['dokument', 'id', 'prikaz','preuzmi', 'metapodaci'];
  dataSource: any[] = [];
  all: number = 0;

  referenced: any = { id: ""};
  referencing: any = { id: ""};

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
              var object = { dokument: 'Obrazac za sprovo??enje imunizacije', id: id }
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
    this.dataSource = [];
    this.all = 0;
    this.search = this.form.value['jmbg'];
    var obj = {
      searchdto: {
        '#': {
          search: this.search,
        }
      }
    };

    this.searchInteresovanje(obj);
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
      this.searchService.getWhereInteresovanjeReferenced(element.id).subscribe(
        (result) => {
            var object = { dokument: 'Obrazac za sprovo??enje imunizacije', id: result };
            this.referenced = object;
        },
        (error) => {

        }
      )
    } else if (element.dokument == 'Obrazac za sprovo??enje imunizacije') {
      this.searchService.getSaglasnostHtmlTransformation(searchDTO).subscribe(
        (result) => {

          (document.getElementById("preview") as any).innerHTML = result;
        }
      )
      this.searchService.getWhereSaglasnostReferenced(element.id).subscribe(
        (result) => {
            console.log(result)
            var object = { dokument: 'Potvrda o vakcinaciji', id: result };
            this.referenced = object;
            console.log(object)
        },
        (error) => {

        }
      )
    }
    else if (element.dokument == 'Zahtev za izdavanje digitalnog sertifikata') {
      this.searchService.getZahtevHtmlTransformation(searchDTO).subscribe(
        (result) => {

          (document.getElementById("preview") as any).innerHTML = result;
        }
      )
      this.searchService.getWhereZahtevReferenced(element.id).subscribe(
        (result) => {
            console.log(result)
            var object = { dokument: 'Digitalni zeleni sertifikat', id: result };
            this.referenced = object;
            console.log(object)
        },
        (error) => {

        }
      )
    }
    else if (element.dokument == 'Potvrda o vakcinaciji') {
      this.searchService.getPotvrdaHtmlTransformation(searchDTO).subscribe(
        (result) => {

          (document.getElementById("preview") as any).innerHTML = result;
        }
      )
      this.referenced = {dokument: '', id: ''};
    } else if (element.dokument == 'Digitalni zeleni sertifikat') {
      this.searchService.getDigitalniHtmlTransformation(searchDTO).subscribe(
        (result) => {

          (document.getElementById("preview") as any).innerHTML = result;
        }
      )
    }
  }

  downloadPDF(element: any): void {
    let documentId = element.id;
    console.log(documentId);
    const searchDTO = { searchdto: { search: documentId } };
    if (element.dokument === 'Interesovanje') {
      this.searchService.getInteresovanjeXSLFOTransformation(searchDTO).subscribe(
        (result) => {
          download(result, "interesovanje.pdf", "text/plain");

        }
      )
    } else if (element.dokument === 'Digitalni zeleni sertifikat') {
      this.searchService.getDigitalniXSLFOTransformation(searchDTO).subscribe(
        (result) => {
          download(result, "digitalni.pdf", "text/plain");
        }
      )
    } else if (element.dokument === 'Obrazac za sprovo??enje imunizacije') {
      this.searchService.getSaglasnostXSLFOTransformation(searchDTO).subscribe(
         (result) => {
          download(result, "saglasnost.pdf", "text/plain");
        }
      )
    } 
    else if (element.dokument === 'Zahtev za izdavanje digitalnog sertifikata') {
      this.searchService.getZahtevXSLFOTransformation(searchDTO).subscribe(
        (result) => {
          download(result, "zahtev.pdf", "text/plain");
        }
      )
    }
    else if (element.dokument === 'Potvrda o vakcinaciji') {
      this.searchService.getPotvrdaXSLFOTransformation(searchDTO).subscribe(
        (result) => {
          download(result, "potvrda.pdf", "text/plain");
        }
      )
    }

  }

  downloadXHTML(element: any): void {
    let documentId = element.id;
    const searchDTO = { searchdto: { search: documentId } };
    if (element.dokument === 'Interesovanje') {
      this.searchService.getInteresovanjeHtmlTransformation(searchDTO).subscribe(
        (result) => {
          download(result, "interesovanje.html", "application/html");
        }
      )
    } else if (element.dokument === 'Obrazac za sprovo??enje imunizacije') {
      this.searchService.getSaglasnostHtmlTransformation(searchDTO).subscribe(
        (result) => {
          download(result, "obrazac.html", "application/html");
        }
      )
    }
    else if (element.dokument === 'Zahtev za izdavanje digitalnog sertifikata') {
      this.searchService.getZahtevHtmlTransformation(searchDTO).subscribe(
        (result) => {
          download(result, "zahtev.html", "application/html");
        }
      )
    }
    else if (element.dokument === 'Potvrda o vakcinaciji') {
      this.searchService.getPotvrdaHtmlTransformation(searchDTO).subscribe(
        (result) => {
          download(result, "potvrda.html", "application/html");
        }
      )
    } else if (element.dokument === 'Digitalni zeleni sertifikat') {
      this.searchService.getDigitalniHtmlTransformation(searchDTO).subscribe(
        (result) => {
          download(result, "digitalni.html", "application/html");
        }
      )
    }

  }


  downloadRdfMetadata(element: any): void {
    let documentId = element.id;
    if (element.dokument === 'Interesovanje') {
      this.searchService.getMetadataRDFForInteresovanje(documentId).subscribe(
        (result) => {
            download( result, "interesovanje.txt", "text/plain" );
        }
      )
    }else if (element.dokument == 'Obrazac za sprovo??enje imunizacije') {
      this.searchService.getMetadataRDFForSaglasnost(documentId).subscribe(
        (result) => {
          download( result, "saglasnost.txt", "text/plain" );
      }
      )
    }
    else if (element.dokument == 'Zahtev za izdavanje digitalnog sertifikata') {
      this.searchService.getMetadataRDFForZahtev(documentId).subscribe(
        (result) => {
          download( result, "zahtev_za_sertifikat.txt", "text/plain" );
      }
      )
    }
    else if (element.dokument == 'Potvrda o vakcinaciji') {
      this.searchService.getMetadataRDFForPotvrda(documentId).subscribe(
        (result) => {
          download( result, "potvrda_o_vakcinaciji.txt", "text/plain" );
      }
      )
    } else if (element.dokument == 'Digitalni zeleni sertifikat') {
      this.searchService.getMetadataRDFForSertifikat(documentId).subscribe(
        (result) => {
          download( result, "digitalni_sertifikat.txt", "text/plain" );
      }
      )
    }
  }

  downloadJsonMetadata(element: any): void {
    let documentId = element.id;
    if (element.dokument === 'Interesovanje') {
      this.searchService.getMetadataJsonForInteresovanje(documentId).subscribe(
        (result) => {
            download( result, "interesovanje.json", "text/plain" );
        }
      )
    }else if (element.dokument == 'Obrazac za sprovo??enje imunizacije') {
      this.searchService.getMetadataJsonForSaglasnost(documentId).subscribe(
        (result) => {
          download( result, "saglasnost.json", "text/plain" );
      }
      )
    }
    else if (element.dokument == 'Zahtev za izdavanje digitalnog sertifikata') {
      this.searchService.getMetadataJsonForZahtev(documentId).subscribe(
        (result) => {
          download( result, "zahtev_za_sertifikat.json", "text/plain" );
      }
      )
    }
    else if (element.dokument == 'Potvrda o vakcinaciji') {
      this.searchService.getMetadataJsonForPotvrda(documentId).subscribe(
        (result) => {
          download( result, "potvrda_o_vakcinaciji.json", "text/plain" );
      }
      )
    } else if (element.dokument == 'Digitalni zeleni sertifikat') {
      this.searchService.getMetadataJsonForSertifikat(documentId).subscribe(
        (result) => {
          download( result, "digitalni_sertifikat.json", "text/plain" );
      }
      )
    }
  }

}

