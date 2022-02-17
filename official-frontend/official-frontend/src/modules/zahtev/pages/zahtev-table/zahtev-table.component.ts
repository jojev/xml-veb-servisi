import { Component, OnInit } from '@angular/core';
import { ZahtevService } from '../../services/zahtev.service';
import * as x2js from 'xml2js';
@Component({
  selector: 'app-zahtev-table',
  templateUrl: './zahtev-table.component.html',
  styleUrls: ['./zahtev-table.component.scss']
})
export class ZahtevTableComponent implements OnInit {
  parser = new x2js.Parser();
  domParser = new DOMParser();
  o2x = require('object-to-xml');

  displayedColumns: string[] = ['ime_prezime', 'jmbg', 'datum', 'odgovor'];
  dataSource = [];
  constructor(private zahtevService: ZahtevService) { 
    this.zahtevService.getPendingZahtev().subscribe(
      (result) => {
        this.parser.parseString(result, function(err: any, res: any) {
        result = res;
        console.log(result);
        })
      }
    ) 
  }

  ngOnInit(): void {
  }


}
