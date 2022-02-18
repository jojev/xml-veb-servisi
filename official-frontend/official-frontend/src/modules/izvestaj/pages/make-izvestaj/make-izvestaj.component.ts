import { Component, OnInit } from '@angular/core';
import { FormControl, FormGroup, FormGroupDirective, NgForm, Validators } from '@angular/forms';
import { NotificationService } from 'src/modules/shared/services/notifications/notification.service';
import * as x2js from 'xml2js';
import { IzvjestajService } from '../../services/izvjestaj/izvjestaj.service';
import * as moment from 'moment';
import * as download from 'downloadjs';

@Component({
  selector: 'app-make-izvestaj',
  templateUrl: './make-izvestaj.component.html',
  styleUrls: ['./make-izvestaj.component.scss']
})
export class MakeIzvestajComponent implements OnInit {

  form: FormGroup;
  parser = new x2js.Parser();
  izvestajId: string = "";

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
    const _this = this;
    this.izvjestajService.create(formatStart, formatEnd).subscribe(
      (result) => {
        this.parser.parseString(result, function(err: any, res: any){
          result = res;
          const tokens = result.izvestaj_o_imunizaciji.$.about.split("/")
          _this.izvestajId = tokens[tokens.length - 1]
          _this.showHtml();
        })
      }
    )
  }

  showHtml() {
    console.log(this.izvestajId)
    const searchDTO = { searchdto: {search: this.izvestajId}};
    this.izvjestajService.getHtmlTransformation(searchDTO).subscribe(
      (result) => {
        (document.getElementById("izvestaj") as any).innerHTML = result;
      }
    )
  }

  preuzmi() {
    console.log(this.izvestajId);
    const searchDTO = { searchdto: {search: this.izvestajId}};
    this.izvjestajService.getPdfTransformation(searchDTO).subscribe(
      (result) => {
        download( result, "izvjestaj.pdf", "application/pdf" );
      }
    )
  }

  preuzmiJson() {
    this.izvjestajService.getMetadataJsonForIzvestaj(this.izvestajId).subscribe(
      (result) => {
        download( result, "izvjestaj.json", "text/plain" );
      }
    )
  }

  preuzmiRdf() {
    this.izvjestajService.getMetadataRDFForIzvestaj(this.izvestajId).subscribe(
      (result) => {
        download( result, "izvjestaj.txt", "text/plain" );
      }
    )
  }
}

