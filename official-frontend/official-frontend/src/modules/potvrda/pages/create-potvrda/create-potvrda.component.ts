import { Component, OnInit } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { MatDialog } from '@angular/material/dialog';
import { NotificationService } from 'src/modules/shared/services/notifications/notification.service';
import * as x2js from 'xml2js';
import { ModalComponent } from '../../components/modal/modal.component';
import { Doza } from '../../model/Doza';
import { PotvrdaService } from '../../service/potvrda/potvrda.service';

@Component({
  selector: 'app-create-potvrda',
  templateUrl: './create-potvrda.component.html',
  styleUrls: ['./create-potvrda.component.scss']
})
export class CreatePotvrdaComponent implements OnInit {
  form: FormGroup;
  parser = new x2js.Parser();
  gender: any[] = [
    {value:'Musko', viewValue: 'Muško'},
    {value:'Zensko', viewValue: 'Žensko'},
  ];
  vaccines: any[] = [
    {value: 'Pfizer-BioNtech'},
    {value: 'Sputnik V'},
    {value: 'Sinopharm'},
    {value: 'AstraZeneca'},
    {value: 'Moderna'},
  ];
  doze: Doza[] = [];
  displayedColumns: string[] = ['datum_davanja', 'serija'];

  constructor(private notificationService: NotificationService,
    private potvrdaService: PotvrdaService,
    public dialog: MatDialog) {
    this.form = new FormGroup({
      imePrezime: new FormControl('', [Validators.required]),
      datumRodjenja: new FormControl('', [Validators.required]),
      pol: new FormControl('', [Validators.required]),
      jmbg: new FormControl('', [Validators.pattern('[0-9]{13}'), Validators.required]),
      vakcina: new FormControl('', [Validators.required]),
      
    })
   }

  ngOnInit(): void {
  }

  submit(): void {
    if(this.doze.length == 0) {
      this.notificationService.error("Morate uneti bar jednu dozu.")
      return;
    }

    const dozeXml: { doza: { datum_davanja: Date; serija: string; }; }[] = [];

    this.doze.forEach(doza => {
      dozeXml.push({
        doza: {
          datum_davanja: doza.datum_davanja,
          serija: doza.serija
        }
      })
    })

    const potvrda = {
      potvrda_o_vakcinaciji: {
        sifra_potvrde_vakcinacije: "sifra",
        licni_podaci: {
          ime_prezime: this.form.value.imePrezime,
          datum_rodjenja: this.form.value.datumRodjenja,
          pol: this.form.value.pol,
          jmbg: this.form.value.jmbg
        },
        podaci_o_vakcinaciji: {
          doze: dozeXml,
          zdravstvena_ustanova: "ustanova",
          naziv_vakcine: this.form.value.vakcina
        },
        datum_izdavanja_potvrde: new Date(),
        qr_kod: "kod"
      }
    };
    console.log(potvrda);

    this.potvrdaService.createPotvrdaOVakcinaciji(potvrda).subscribe(
      (res) => {
        this.notificationService.success("Uspešno ste kreirali potvrdu o vakcinaciji.")
      },
      (error) => {
        if(error.status === 401) {
          this.notificationService.error("Podaci su pogrešni")
        }
        else {
          this.notificationService.error("Došlo je do greške, pokušajte ponovo.")
        }
      }
    )
  }

  openCompDialog(): void {
    const modal = this.dialog.open(ModalComponent, {
      height: '300px',
      width: '400px',
    });
    modal.afterClosed().subscribe((res) => {
      if(res.event === 'add') {
        console.log(res.data)
        const list = []
        this.doze.forEach(doza => list.push(doza))
        list.push(res.data)
        this.doze = list;
      }
    });
  }
}
