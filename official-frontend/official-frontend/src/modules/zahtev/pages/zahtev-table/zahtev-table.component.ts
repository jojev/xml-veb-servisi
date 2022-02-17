import { Component, OnInit } from '@angular/core';
import { ZahtevService } from '../../services/zahtev.service';

@Component({
  selector: 'app-zahtev-table',
  templateUrl: './zahtev-table.component.html',
  styleUrls: ['./zahtev-table.component.scss']
})
export class ZahtevTableComponent implements OnInit {

  displayedColumns: string[] = ['ime_prezime', 'jmbg', 'datum', 'odgovor'];
  dataSource = [];
  constructor(private zahtevService: ZahtevService) { }

  ngOnInit(): void {
  }


}
