import { Component, OnInit } from '@angular/core';
import { ZahtevService } from '../../services/zahtev.service';
import * as x2js from 'xml2js';
import { MatDialog, MatDialogConfig } from '@angular/material/dialog';
import { ResponseModalComponent } from '../../components/response-modal/response-modal.component';
import { NotificationService } from 'src/modules/shared/services/notifications/notification.service';
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
  dataSource: any[] = [];
  constructor(private zahtevService: ZahtevService, public dialog: MatDialog, private notificationService: NotificationService) {
    const that = this;
    this.zahtevService.getPendingZahtev().subscribe(
      (result) => {
        this.parser.parseString(result, function (err: any, res: any) {
          result = res;
          that.dataSource = result.zahtevList.zahtev_za_izdavanje_sertifikata;
        })
      }
    )
  }

  ngOnInit(): void {
  }

  accept(about: string): void {
    const id = about.split("/")[5];

    var obj = {
      razlogdto: {
        '#': {
          razlog: "",
          odobren: true,
          zahtev: id,
        }
      }
    };
    this.zahtevService.sendResponse(obj).subscribe(
      (result) => {
        this.parser.parseString(result, function (err: any, res: any) {
        });
        this.notificationService.success("Uspešno ste odgovorili na zahtev.")
        this.dataSource = this.dataSource.filter(object => object.$.about.split("/")[5] !== id);
      },
      (error) => {
        if (error.status === 400) {
          this.notificationService.error('Greška!')
        }
        else {
          this.notificationService.error("Došlo je do greške, pokušajte ponovo.")
        }
      }
    )


  }
  decline(about: any) {
    const id = about.split("/")[5];

    const dialogConfig = new MatDialogConfig();
    dialogConfig.disableClose = true;
    dialogConfig.autoFocus = true;
    
    const d = this.dialog.open(ResponseModalComponent, dialogConfig);
    d.afterClosed().subscribe((result) => {
      if (result.event === 'send') {
        var obj = {
          razlogdto: {
            '#': {
              razlog: result.data,
              odobren: false,
              zahtev: id,
            }
          }
        };

        this.zahtevService.sendResponse(obj).subscribe(
          (result) => {
            this.parser.parseString(result, function (err: any, res: any) {
            });
            this.notificationService.success("Uspešno ste odgovorili na zahtev.")
            this.dataSource = this.dataSource.filter(object => object.$.about.split("/")[5] !== id);
          },
          (error) => {
            if (error.status === 400) {
              this.notificationService.error('Greška!')
            }
            else {
              this.notificationService.error("Došlo je do greške, pokušajte ponovo.")
            }
          }
        )
      }
    });

  }
}
