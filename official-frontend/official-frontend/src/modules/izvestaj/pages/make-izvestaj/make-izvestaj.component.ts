import { Component, OnInit } from '@angular/core';
import { FormControl, FormGroup, FormGroupDirective, NgForm, Validators } from '@angular/forms';
import { NotificationService } from 'src/modules/shared/services/notifications/notification.service';
import * as x2js from 'xml2js';
import { IzvjestajService } from '../../services/izvjestaj/izvjestaj.service';
import * as moment from 'moment';

@Component({
  selector: 'app-make-izvestaj',
  templateUrl: './make-izvestaj.component.html',
  styleUrls: ['./make-izvestaj.component.scss']
})
export class MakeIzvestajComponent implements OnInit {

  form: FormGroup;
  parser = new x2js.Parser();
  constructor(
    private notificationService: NotificationService,
    private izvjestajService: IzvjestajService
    ) {
    this.form = new FormGroup({
      periodOd: new FormControl('', Validators.required),
      periodDo: new FormControl('', Validators.required),
    });
   }

  ngOnInit(): void {
  }
  submit(): void {
    const startDate = this.form.get('periodOd')?.value;
    const endDate = this.form.get('periodDo')?.value;
    let formatStart = moment(startDate).format("yyyy-MM-DD");
    let formatEnd = moment(endDate).format("yyyy-MM-DD");

    this.izvjestajService.create(formatStart, formatEnd).subscribe(
      (result) => {
        (document.getElementById("izvestaj") as any).innerHTML = result;
      }
    )
  }
}

