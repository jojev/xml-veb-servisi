import { Component, OnInit } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import * as moment from 'moment';
import { NotificationService } from 'src/modules/shared/services/notification/notification.service';
import * as x2js from 'xml2js';
import { ZahtevService } from '../../services/zahtev.service';
@Component({
  selector: 'app-zahtev-form',
  templateUrl: './zahtev-form.component.html',
  styleUrls: ['./zahtev-form.component.scss']
})
export class ZahtevFormComponent implements OnInit {

  form: FormGroup;
  parser = new x2js.Parser();
  options: any[] = [
    { value: 'Muski', viewValue: 'Muško' },
    { value: 'Zenski', viewValue: 'Žensko' },
  ];

  constructor(private notificationService: NotificationService, private zahtevService: ZahtevService) {
    this.form = new FormGroup({
      jmbg: new FormControl('', [Validators.required, Validators.pattern('[0-9 ]{13}')]),
      ime: new FormControl('', [Validators.required]),
      prezime: new FormControl('', [Validators.required]),
      datum: new FormControl('', [Validators.required, Validators.pattern('[0-9 ]{4}-[0-9 ]{2}-[0-9]{2}')]),
      brPasosa: new FormControl('', [Validators.required, Validators.pattern('[0-9 ]{9}')]),
      pol: new FormControl('', [Validators.required]),
      razlog: new FormControl('', [Validators.required]),

    });
  }

  ngOnInit(): void {
  }

  submit(): void {
    var obj = {
      zahtev_za_izdavanje_sertifikata: {
        '#': {
          podnosilac_zahteva: {
            ime_prezime: this.form.get('ime')?.value + " " + this.form.get('prezime')?.value,
            datum_rodjenja: this.form.get('datum')?.value,
            pol: this.form.get('pol')?.value,
            jmbg: this.form.get('jmbg')?.value,
            broj_pasosa: this.form.get('brPasosa')?.value,
          },
          razlog_za_podnosenje: this.form.get('razlog')?.value,
          datum_podnosenja_zahteva: (moment(new Date())).format('YYYY-MM-DD'),
          mesto_podnosenja_zahteva: 'Novi Sad',
          odgovor: 'ceka odgovor',
        }
      }
    };
    this.zahtevService.create(obj).subscribe(
      (result) => {
        this.parser.parseString(result, function (err: any, res: any) {
        });
        this.notificationService.success("Uspešno kreiran zahtev za sertifikat.")
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

