import { Component, OnInit } from '@angular/core';
import { JwtHelperService } from '@auth0/angular-jwt';
import { VaccineStatus } from '../../model/VaccineStatus';
import { VaccineStatusService } from '../../services/vaccineStatus/vaccine-status.service';
import * as x2js from 'xml2js';
import { NotificationService } from 'src/modules/shared/services/notifications/notification.service';
import { MatDialog } from '@angular/material/dialog';
import { ModalComponent } from '../../components/modal/modal.component';

@Component({
  selector: 'app-vaccine-status-view-and-update',
  templateUrl: './vaccine-status-view-and-update.component.html',
  styleUrls: ['./vaccine-status-view-and-update.component.scss']
})
export class VaccineStatusViewAndUpdateComponent implements OnInit {
  vaccines: VaccineStatus[] = [];
  parser = new x2js.Parser();
  displayedColumns: string[] = ['vakcina', 'kolicina', "select"];

  constructor(private vaccineStatusService: VaccineStatusService,
    private notificationService:NotificationService,
    public dialog: MatDialog) { }

  ngOnInit(): void {
    this.getAllVaccines();
  }

  getAllVaccines(): void {
    this.vaccineStatusService.getVaccineTypes().subscribe(
      (result) => {
        this.parser.parseString(result, function(err: any,res: any){
          result = res.stanjeVakcineList.stanjeVakcine;
        });
        this.vaccines = []
        result.forEach((element: any) => {
          this.vaccines.push({
            kolicina: element.kolicina[0],
            vakcina: element.vakcina[0]
          });
        });
      },
      (error) => {
        if(error.status === 401) {
          this.notificationService.error("Pogrešni kredencijali")
        }
      }
    )
  }

  openCompDialog(vaccine: string, numberOfAvailable: number) {
    const newVaccineStatus = {
      stanjeVakcine: {
        vakcina: vaccine,
        kolicina: numberOfAvailable
      }
    }
    const modal = this.dialog.open(ModalComponent, { data: numberOfAvailable });
    modal.afterClosed().subscribe((res) => {
      if(res.event === 'update') {
        newVaccineStatus.stanjeVakcine.kolicina = res.data;
        this.vaccineStatusService.updateVaccineType(newVaccineStatus).subscribe(
          (result) => {
            this.getAllVaccines();
          },
          (error) => {
            if(error.status === 401) {
              this.notificationService.error("Došlo je do greške, pokušajte ponovo.")
            }
          }
        )
      }
    });
  }
}
