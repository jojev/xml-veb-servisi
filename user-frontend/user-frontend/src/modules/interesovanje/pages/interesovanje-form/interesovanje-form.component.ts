import { Component, OnInit } from '@angular/core';
import { FormControl, FormGroup, FormGroupDirective, NgForm, Validators } from '@angular/forms';
import { NotificationService } from 'src/modules/shared/services/notification/notification.service';
import * as x2js from 'xml2js';
import { InteresovanjeService } from '../../services/interesovanje.service';
import * as moment from 'moment';

@Component({
  selector: 'app-interesovanje-form',
  templateUrl: './interesovanje-form.component.html',
  styleUrls: ['./interesovanje-form.component.scss']
})
export class InteresovanjeFormComponent implements OnInit {

  form: FormGroup;
  parser = new x2js.Parser();
  options: any[] = [
    { value: 'Drzavljanin Republike Srbije', viewValue: 'Državljanin Republike Srbije' },
    { value: 'Strani drzavljanin sa boravkom u RS', viewValue: 'Strani državljanin sa boravkom u RS' },
    { value: 'Strani drzavljanin bez boravka u RS', viewValue: 'Strani državljanin bez boravka u RS' },
  ];
  vaccines: any[] = [
    { value: 'Pfizer-BioNtech' },
    { value: 'Sputnik V' },
    { value: 'Sinopharm' },
    { value: 'AstraZeneca' },
    { value: 'Moderna' },
    { value: 'Bilo koja' },
  ];
  yesNo: any[] = [
    { value: 'Da' },
    { value: 'Ne' },

  ];
  opstine: any[] = [
    { value: 'Novi Sad' },
    { value: 'Petrovaradin' },
    { value: 'Temerin' },
    { value: 'Titel' },
    { value: 'Irig' },
    { value: 'Inđija' },
    { value: 'Ruma' },
    { value: 'Sremska Mitrovica' },
    { value: 'Zrenjanin' },
    { value: 'Kikinda' },
  ];

  constructor(private notificationService: NotificationService, private interesovanjeService: InteresovanjeService) {
    this.form = new FormGroup({
      jmbg: new FormControl('', [Validators.required, Validators.pattern('[0-9 ]{13}')]),
      ime: new FormControl('', [Validators.required]),
      prezime: new FormControl('', [Validators.required]),
      email: new FormControl('', [Validators.required, Validators.email]),
      brMobilni: new FormControl('', [Validators.required, Validators.pattern('06[0-9 ]{8,9}')]),
      brFiksni: new FormControl('', [Validators.required, Validators.pattern('0[0-9 ]{8}')]),
      opcija: new FormControl('', [Validators.required]),
      vakcina: new FormControl('', [Validators.required]),
      davalac: new FormControl('', [Validators.required]),
      opstina: new FormControl('', [Validators.required]),

    });
  }

  ngOnInit(): void {
  }

  submit(): void {
    var obj = {
      interesovanje_za_vakcinisanje: {
        '#': {
          licni_podaci: {
            jmbg: this.form.get('jmbg')?.value,
            drzavljanstvo: this.form.get('opcija')?.value,
            ime: this.form.get('ime')?.value,
            prezime: this.form.get('prezime')?.value,
            adresa_elektronske_poste: this.form.get('email')?.value,
            broj_mobilnog_telefona: this.form.get('brMobilni')?.value,
            broj_fiksnog_telefona: this.form.get('brFiksni')?.value,
          },
          podaci_o_vakcinisanju: {
            opstina_vakcinisanja: this.form.get('opstina')?.value,
            tip_vakcine: this.form.get('vakcina')?.value,
            dobrovoljni_davalac_krvi: this.form.get('davalac')?.value,
          },
          datum_podnosenja: (moment(new Date())).format('YYYY-MM-DD')
        }
      }
    };
    this.interesovanjeService.create(obj).subscribe(
      (result) => {
        this.parser.parseString(result, function (err: any, res: any) {
        });
        this.notificationService.success("Uspešno kreirano interesovanje.")
      },
      (error) => {
        if (error.status === 400) {
          this.notificationService.error('Pogrešni podaci')
        }
        else {
          this.notificationService.error("Došlo je do greške, pokušajte ponovo.")
        }
      }
    )
  }

}
