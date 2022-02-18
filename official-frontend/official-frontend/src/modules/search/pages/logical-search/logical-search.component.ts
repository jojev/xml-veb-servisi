import { Component, OnInit } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import * as x2js from 'xml2js';
import { SearchService } from '../../services/search.service';
@Component({
  selector: 'app-logical-search',
  templateUrl: './logical-search.component.html',
  styleUrls: ['./logical-search.component.scss']
})
export class LogicalSearchComponent implements OnInit {
  parser = new x2js.Parser();
  domParser = new DOMParser();
  o2x = require('object-to-xml');

  search: string = " ";
  option: string = " ";
  metadata: string = " ";
  operator: string = " ";
  query: string = "?doc ";
  counter: number = 0;
  map: any[] = [];
  z: string = " ";
  options: any[] = [
    { value: 'interesovanje', viewValue: 'Interesovanje' },
    { value: 'obrazac_za_imunizaciju', viewValue: 'Obrazac za sprovodjenje imunizacije' },
    { value: 'potvrda_o_vakcinaciji', viewValue: 'Potvrda o vakcinaciji' },
    { value: 'zahtev_za_sertifikat', viewValue: 'Zahtev za digitalni sertfikat' },
    { value: 'digitalni_sertifikat', viewValue: 'Digitalni sertifikat' },
  ];
  interesovanja: any[] = [
    { value: '<http://www.ftn.uns.ac.rs/rdf/interesovanje/predicate/Kreiran>', viewValue: 'Datum kreiranja' },
    { value: '<http://www.ftn.uns.ac.rs/rdf/interesovanje/predicate/Kreirao>', viewValue: 'Jmbg građanina' },
    { value: '<http://www.ftn.uns.ac.rs/rdf/interesovanje/predicate/Zeljena>', viewValue: 'Tip vakcine' },
  ];
  obrasci: any[] = [
    { value: '<http://www.ftn.uns.ac.rs/rdf/saglasnosti/predicate/KreiranDatuma>', viewValue: 'Datum kreiranja' },
    { value: '<http://www.ftn.uns.ac.rs/rdf/saglasnosti/predicate/Kreirao>', viewValue: 'Jmbg građanina' },
    { value: '<http://www.ftn.uns.ac.rs/rdf/saglasnosti/predicate/ZeljenaVakcina>', viewValue: 'Tip vakcine' },
  ];
  potvrde: any[] = [
    { value: '<http://www.ftn.uns.ac.rs/rdf/potvrda_o_vakcinaciji/predicate/Izdat>', viewValue: 'Datum izdavanja' },
    { value: '<http://www.ftn.uns.ac.rs/rdf/potvrda_o_vakcinaciji/predicate/KreiranZa>', viewValue: 'Jmbg građanina' },
  ];
  zahtevi: any[] = [
    { value: '<http://www.ftn.uns.ac.rs/rdf/zahtev_za_sertifikat/predicate/IzdatDatuma>', viewValue: 'Datum izdavanja' },
    { value: '<http://www.ftn.uns.ac.rs/rdf/zahtev_za_sertifikat/predicate/PodneoBrPasosa>', viewValue: 'Broj pasoša' },
    { value: '<http://www.ftn.uns.ac.rs/rdf/zahtev_za_sertifikat/predicate/PodneoJmbg>', viewValue: 'Jmbg građanina' },
  ];
  digitalni: any[] = [
    { value: '<http://www.ftn.uns.ac.rs/rdf/digitalni_sertifikat/predicate/Izdat>', viewValue: 'Datum izdavanja' },
    { value: '<http://www.ftn.uns.ac.rs/rdf/digitalni_sertifikat/predicate/ZatrazioBrojPasosa>', viewValue: 'Broj pasoša' },
    { value: '<http://www.ftn.uns.ac.rs/rdf/digitalni_sertifikat/predicate/ZatrazioJmbg>', viewValue: 'Jmbg građanina' },
  ];
  displayedColumns: string[] = ['dokument', 'id', 'prikaz'];
  dataSource: any[] = [];
  all: number = 0;

  form: FormGroup;
  constructor(private searchService: SearchService) {
    this.form = new FormGroup({
      meta: new FormControl('', [Validators.required,]),
      opcija: new FormControl('', [Validators.required,]),
      metapodatak: new FormControl('', [Validators.required,]),
      znak: new FormControl('', [Validators.required,]),
  
    });
  }

  ngOnInit(): void {
  }

  submit(element: any): void {
    this.search = this.form.value['meta'];
    this.option = this.form.value['opcija'];
    this.metadata += this.form.value['metapodatak'];
    this.z = this.form.value['znak'];
    this.counter++;
    this.query += "  " + this.metadata + " ?var" + this.counter + ". ?doc ";
    this.operator = element;
    this.map.push({ var: "?var" + this.counter, value: this.search, op: this.operator, znak: this.z });

  }

  searchMetadata(): void {
    this.option = this.form.value['opcija'];
    this.metadata = this.form.value['metapodatak'];
    this.search = this.form.value['meta'];
    this.z = this.form.value['znak'];
    this.counter++;
    this.query += "  " + this.metadata + " ?var" + this.counter + " ";
    this.map.push({ var: "?var" + this.counter, value: this.search ,  znak: this.z });
    this.query += " FILTER (";
    for (let i = 0; i < this.map.length; i++) {
      this.query += this.map[i].var + " "+this.map[i].znak +" '" + this.map[i].value + "'";
      if (this.map[i].op !== undefined) {
        this.query += " " + this.map[i].op + " ";
      }
    }
    this.query += ")";
    var obj = {
      metadatasearch: {
        '#': {
          search: this.query,
          collection: this.option
        }
      }
    };

    this.searchService.getMetadataLogicalSearch(obj).subscribe(
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
        })
      });

      this.search =  " ";
      this.option = " ";
      this.metadata = " ";
      this.operator= " ";
      this.query = "?doc ";
      this.z = " ";
      this.counter = 0;
      this.map = [];
      this.dataSource = [];
}


}