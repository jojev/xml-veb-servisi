import { Component, OnInit } from '@angular/core';
import {ActivatedRoute} from "@angular/router";
import { Params } from '@angular/router';
import * as x2js from 'xml2js';
import { PrikazService } from 'src/modules/prikaz/services/prikaz.service';

@Component({
  selector: 'app-prikaz',
  templateUrl: './prikaz.component.html',
  styleUrls: ['./prikaz.component.scss']
})
export class PrikazComponent implements OnInit {
  parser = new x2js.Parser();
  domParser = new DOMParser();
  o2x = require('object-to-xml');
  id: any = ""
  type: any = ""
  document_id: any = ""
  constructor(private route: ActivatedRoute, private prikazService: PrikazService) {
    this.route.params.subscribe(
      (params: Params) => {
        this.id =  params["id"]
        this.type = params["type"]
      }
    )
   }

  ngOnInit(): void {
    var obj = {
      searchdto: {
        '#': {
          search: this.id,
        }
      }
    };
    this.prikazService.getSearchByText(obj,this.type).subscribe(
      (result) => {
        this.parser.parseString(result, (err: any, res: any) => {
          result = res;
          if(this.type==="potvrda_o_vakcinaciji"){
            if (result.potvrdaOVakcinacijiList.potvrda_o_vakcinaciji) {
              for (var i = 0; i < result.potvrdaOVakcinacijiList.potvrda_o_vakcinaciji.length; i++) {
                const id = result.potvrdaOVakcinacijiList.potvrda_o_vakcinaciji[i].$.about.split('/')[5];
                this.document_id = id;
                this.showDocument()
              }
            }
          }
          else if(this.type==="digitalni_sertifikat"){
            if (result.digitalniSertifikatList.digitalni_zeleni_sertifikat) {
              for (var i = 0; i < result.digitalniSertifikatList.digitalni_zeleni_sertifikat.length; i++) {
                const id = result.digitalniSertifikatList.digitalni_zeleni_sertifikat[i].$.about.split('/')[5];
                this.document_id = id;
                this.showDocument()
              }
            }
          }
        })
      }
    );
  }

  showDocument(): void {
    const searchDTO = { searchdto: { search: this.document_id } };
    if(this.type == 'potvrda_o_vakcinaciji') {
      this.prikazService.getPotvrdaHtmlTransformation(searchDTO).subscribe(
        (result) => {

          (document.getElementById("preview") as any).innerHTML = result;
        }
      )
    } else if (this.type == 'digitalni_sertifikat') {
      this.prikazService.getDigitalniHtmlTransformation(searchDTO).subscribe(
        (result) => {

          (document.getElementById("preview") as any).innerHTML = result;
        }
      )
    }
  }

}
