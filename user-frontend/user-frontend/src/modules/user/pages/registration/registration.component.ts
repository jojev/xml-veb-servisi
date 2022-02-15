import { Component, OnInit } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { UserService } from '../../services/user.service';
import * as x2js from 'xml2js';
import { JwtHelperService } from '@auth0/angular-jwt';
import { NotificationService } from 'src/modules/shared/services/notification/notification.service';

@Component({
  selector: 'app-registration',
  templateUrl: './registration.component.html',
  styleUrls: ['./registration.component.scss']
})
export class RegistrationComponent implements OnInit {
  form: FormGroup;
  parser = new x2js.Parser();

  constructor(private userService: UserService,
    private notificationService: NotificationService) { 
    this.form = new FormGroup({
      ime: new FormControl(null, Validators.required),
      prezime: new FormControl(null, Validators.required),
      korisnickoIme: new FormControl(null, Validators.required),
      lozinka: new FormControl('', Validators.required),
      uloga: new FormControl([]),
      email: new FormControl(null, Validators.required),
    });}

  ngOnInit(): void {
  }

  submit(): void {
    this.form.patchValue({uloga: [{
      naziv: "ROLE_GRADJANIN"
    }]});
    const user = {
      korisnik: {
        ...this.form.value
      }
    }

    this.userService.register(user).subscribe(
      (result) => {
        this.parser.parseString(result, function(err: any,res: any){
        });
        this.notificationService.success("Uspešno ste se registrovali.")
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

  public errorHandling = (control: string, error: string) => {
    return this.form.controls[control].hasError(error) && this.form.get(control)?.touched;
  }
}
