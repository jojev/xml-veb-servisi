import { Component, OnInit } from '@angular/core';
import { FormControl, FormGroup, FormGroupDirective, NgForm, Validators } from '@angular/forms';
import { NotificationService } from 'src/modules/shared/services/notifications/notification.service';
import * as x2js from 'xml2js';
import { SaglasnostService } from '../services/saglasnost.service';
import { DatePipe } from '@angular/common'
@Component({
  selector: 'app-saglasnost',
  templateUrl: './saglasnost.component.html',
  styleUrls: ['./saglasnost.component.scss']
})
export class SaglasnostComponent implements OnInit {
  form: FormGroup;
  parser = new x2js.Parser();
  vaccines: any[] = [
    {value: 'Pfizer-BioNtech'},
    {value: 'Sputnik V'},
    {value: 'Sinopharm'},
    {value: 'AstraZeneca'},
    {value: 'Moderna'},
    {value: 'Bilo koja'},
  ];
  ekstremitet: any[] = [
    {value:'DR', viewValue: 'Desna ruka'},
    {value:'LR', viewValue: 'Leva ruka'},
  ];
  constructor(private notificationService: NotificationService, private saglasnostService:SaglasnostService,public datepipe: DatePipe) { 
    this.form = new FormGroup({
     jmbg: new FormControl('', [Validators.pattern('[0-9 ]{13}')]),
     zdravstvenaUstanova: new FormControl('',[Validators.required]),
     vakcinacijskiPunkt: new FormControl('',[Validators.required]),
     podaciOLekaru: new FormControl('',[Validators.required]),
     vakcina: new FormControl('', [Validators.required]),
     datumDavanjaVakcine: new FormControl(new Date(),[Validators.required]),
     nacinDavanjaVakcine: new FormControl("IM",[Validators.required]),
     ekstremitet: new FormControl('',[Validators.required]),
     serijaVakcine: new FormControl('',[Validators.required]),
     proizvodjac: new FormControl('',[Validators.required]),
     nezeljenaReakcija: new FormControl('',[Validators.required])
   });
 }

  ngOnInit(): void {
  }

  submit(){
    console.log(this.form.value)
    var obj;
    let datumDavanja  = this.datepipe.transform(this.form.get('datumDavanjaVakcine')?.value, 'yyyy-MM-dd')?.split('T')[0];
    obj = {
      podaci_koje_je_popunio_zdravstveni_radnik : {
        '#': {
            zdravstvena_ustanova: this.form.get('zdravstvenaUstanova')?.value,
            vakcinacijski_punkt: this.form.get('vakcinacijskiPunkt')?.value,
            podaci_o_lekaru: this.form.get('podaciOLekaru')?.value,
              doze:{
                doza:{
                  vakcina: this.form.get('vakcina')?.value,
                  datum_davanja_vakcine: datumDavanja,
                  nacin_davanja_vakcine: this.form.get('nacinDavanjaVakcine')?.value,
                  ekstremitet: this.form.get('ekstremitet')?.value,
                  serija_vakcine: this.form.get('serijaVakcine')?.value,
                  proizvodjac: this.form.get('proizvodjac')?.value,
                  nezeljena_reakcija: this.form.get('nezeljenaReakcija')?.value
                }
          }
        }
      }
    }
    this.saglasnostService.create(obj,this.form.get('jmbg')?.value).subscribe(
      (result) => {
        this.parser.parseString(result, function(err: any,res: any){
        });
        this.notificationService.success("Uspešno kreirana saglasnost.")
      },
      (error) => {
        if(error.status === 400) {
          this.notificationService.error('Pogrešni podaci')
        }
        else {
          this.notificationService.error("Došlo je do greške, pokušajte ponovo.")
        }
      }
    )
  }
}
