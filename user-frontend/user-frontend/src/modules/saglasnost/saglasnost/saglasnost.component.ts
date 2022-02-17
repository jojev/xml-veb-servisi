import { Component, OnInit } from '@angular/core';
import { FormControl, FormGroup, FormGroupDirective, NgForm, Validators } from '@angular/forms';
import { NotificationService } from 'src/modules/shared/services/notification/notification.service';
import * as x2js from 'xml2js';
import { SaglasnostService } from '../services/saglasnost.service';
import { DatePipe } from '@angular/common'
import * as moment from 'moment';
@Component({
  selector: 'app-saglasnost',
  templateUrl: './saglasnost.component.html',
  styleUrls: ['./saglasnost.component.scss']
})
export class SaglasnostComponent implements OnInit {
  form: FormGroup;
  parser = new x2js.Parser();
  opstine: any[] = [
    {value: 'Novi Sad'},
    {value: 'Petrovaradin'},
    {value: 'Temerin'},
    {value: 'Titel'},
    {value: 'Irig'},
    {value: 'Inđija'},
    {value: 'Ruma'},
    {value: 'Sremska Mitrovica'},
    {value: 'Zrenjanin'},
    {value: 'Kikinda'},
  ];
  yesNo: any[] = [
    {value: 'DA'},
    {value: 'NE'},

  ];

  saglasan: any[] = [
    {value: 'SAGLASAN SAM'},
    {value: 'NISAM SAGLASAN'},

  ];
  gender: any[] = [
    {value:'M', viewValue: 'Muško'},
    {value:'Z', viewValue: 'Žensko'},
  ];
  vaccines: any[] = [
    {value: 'Pfizer-BioNtech'},
    {value: 'Sputnik V'},
    {value: 'Sinopharm'},
    {value: 'AstraZeneca'},
    {value: 'Moderna'},
    {value: 'Bilo koja'},
  ];
  options: any[] = [
    {value:'Drzavljanin Republike Srbije', viewValue: 'Državljanin Republike Srbije'},
    {value: 'Strani drzavljanin', viewValue: 'Strani državljanin'},
  ];
  workingStatuss: any[] = [
    {value:'zaposlen', viewValue:'Zaposlen'},
    {value:'nezaposlen', viewValue:'Nezapsolen'},
    {value:'penzioner', viewValue:'Penzioner'},
    {value:'ucenik', viewValue:'Učenik'},
    {value:'student', viewValue:'Student'},
    {value:'dete', viewValue:'Dete'},
  ]
  jobRole: any[] = [
    {value:'zdravstvena zastita',viewValue: 'Zdravstvena zaštita'},
    {value:'socijalna zastita', viewValue: 'Socijalna zaštita'},
    {value:'prosveta',viewValue: 'Prosveta'},
    {value:'MUP',viewValue: 'MUP'},
    {value:'Vojska RS',viewValue: 'Vojska RS'},
    {value:'drugo',viewValue: 'Drugo'},
  ]
  constructor(private notificationService: NotificationService, private saglasnostService:SaglasnostService,public datepipe: DatePipe) { 
     this.form = new FormGroup({
      jmbg: new FormControl('', [Validators.pattern('[0-9 ]{13}')]),
      stranoDrzavljanstvo: new FormControl(''),
      brPasosa: new FormControl(''),
      imeRoditelja: new FormControl('',[Validators.required]),
      datumRodjenja: new FormControl(new Date(),[Validators.required]),
      mestoRodjenja: new FormControl('',[Validators.required]),
      ime: new FormControl('', [Validators.required]),
      prezime: new FormControl('', [Validators.required]),
      email: new FormControl('', [Validators.required, Validators.email]),
      brMobilni: new FormControl('', [Validators.required, Validators.pattern('06[0-9 ]{8,9}')]),
      brFiksni: new FormControl('', [Validators.required, Validators.pattern('0[0-9 ]{8}')]),
      vakcina: new FormControl('', [Validators.required]),

      opstina: new FormControl('', [Validators.required]),
      naselje: new FormControl('',[Validators.required]),
      ulicaBroj: new FormControl('',[Validators.required]),
      opstinaSedistaZastite: new FormControl('',[]),
      radniStatus: new FormControl('',[]),
      zanimanjeZaposlenog: new FormControl('',[]),
      korisnikSZ: new FormControl('',[Validators.required]),
      saglasan: new FormControl('',[Validators.required]),
      opcija: new FormControl(''),
      pol: new FormControl('',[Validators.required]),
      nazivLekara: new FormControl('',[Validators.required])
    });
  }
  
  
  ngOnInit(): void {
  }

  submit(): void {
    var obj;
    let datumRodjenja1 = this.datepipe.transform(this.form.get('datumRodjenja')?.value, 'yyyy-MM-dd')?.split('T')[0];
    let danasnji =  this.datepipe.transform(new Date(), 'yyyy-MM-dd');
    if(this.form.value.opcija==='Drzavljanin Republike Srbije'){
    obj = {
      obrazac_za_sprovodjenje_imunizacije : {
        '#': {
          podaci_koje_je_popunio_pacijent :{
              licni_podaci: {
                drzavljanstvo: 'Republike Srbije',
                jmbg: this.form.get('jmbg')?.value,
                prezime: this.form.get('prezime')?.value,
                ime: this.form.get('ime')?.value,
                ime_roditelja: this.form.get('imeRoditelja')?.value,
                pol: this.form.get('pol')?.value,
                datum_rodjenja: datumRodjenja1,
                mesto_rodjenja: this.form.get('mestoRodjenja')?.value,
                adresa:{
                  ulica_broj: this.form.get('ulicaBroj')?.value,
                  naselje: this.form.get('naselje')?.value,
                  grad: this.form.get('opstina')?.value
                },
                broj_mobilnog_telefona: this.form.get('brMobilni')?.value,
                broj_fiksnog_telefona: this.form.get('brFiksni')?.value,
                imejl: this.form.get('email')?.value,
                radni_status: this.form.get('radniStatus')?.value,
                zanimanje_zaposlenog: this.form.get('zanimanjeZaposlenog')?.value,
                korisnik_ustanove_socijalne_zastite: this.form.get('korisnikSZ')?.value,
                naziv_sedista_ustanove: this.form.get('opstinaSedistaZastite')?.value
              },
              saglasnost: this.form.get('saglasan')?.value,
              zeljena_vakcina: this.form.get('vakcina')?. value,
              datum:  danasnji
          }
        }
      }
    }
    }
    else{
      obj = {
        obrazac_za_sprovodjenje_imunizacije : {
          '#': {
            podaci_koje_je_popunio_pacijent :{
                licni_podaci: {
                  strano_drzavljanstvo: this.form.get('stranoDrzavljanstvo')?.value,
                  broj_pasosa: this.form.get('brPasosa')?.value,
                  jmbg: this.form.get('jmbg')?.value,
                  prezime: this.form.get('prezime')?.value,
                  ime: this.form.get('ime')?.value,
                  ime_roditelja: this.form.get('imeRoditelja')?.value,
                  pol: this.form.get('pol')?.value,
                  datum_rodjenja: datumRodjenja1,
                  mesto_rodjenja: this.form.get('mestoRodjenja')?.value,
                  adresa:{
                    ulica_broj: this.form.get('ulicaBroj')?.value,
                    naselje: this.form.get('naselje')?.value,
                    grad: this.form.get('opstina')?.value
                  },
                  broj_mobilnog_telefona: this.form.get('brMobilni')?.value,
                  broj_fiksnog_telefona: this.form.get('brFiksni')?.value,
                  imejl: this.form.get('email')?.value,
                  radni_status: this.form.get('radniStatus')?.value,
                  zanimanje_zaposlenog: this.form.get('zanimanjeZaposlenog')?.value,
                  korisnik_ustanove_socijalne_zastite: this.form.get('korisnikSZ')?.value,
                  naziv_sedista_ustanove: this.form.get('opstinaSedistaZastite')?.value
                },
                saglasnost: this.form.get('saglasan')?.value,
                zeljena_vakcina: this.form.get('vakcina')?. value,
                datum:  danasnji
            }
          }
        }
      }
    }
    this.saglasnostService.create(obj).subscribe(
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
