import { Component, OnInit } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { JwtHelperService } from '@auth0/angular-jwt';
import { AuthService } from '../../services/auth/auth.service';
import * as x2js from 'xml2js';
import { NotificationService } from 'src/modules/shared/services/notifications/notification.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.scss']
})
export class LoginComponent implements OnInit {
  form: FormGroup;
  parser = new x2js.Parser();

  constructor(private authService: AuthService,
    private notificationService:NotificationService, private router: Router) { 
    this.form = new FormGroup({
      username: new FormControl(null, Validators.required),
      password: new FormControl('', Validators.required),
    });
  }

  ngOnInit(): void {
  }

  submit(): void {
    const auth = {
      jwtauthenticationrequest: {
        ...this.form.value
      }
    }
    this.authService.login(auth).subscribe(
      (result) => {
        this.parser.parseString(result, function(err: any,res: any){
          const jwtUser = JSON.stringify(res);
          console.log(jwtUser)
          const jwt: JwtHelperService = new JwtHelperService();
          const role = jwt.decodeToken(jwtUser).role;
          const username = jwt.decodeToken(jwtUser).username;
          localStorage.setItem("user", jwtUser);
          localStorage.setItem("username", username);
          localStorage.setItem("role", role);
        });
        this.router.navigate(["/search/documents"]);
      },
      (error) => {
        if(error.status === 401) {
          this.notificationService.error("Pogrešni kredencijali")
        }
      }
    );
  }

  public errorHandling = (control: string, error: string) => {
    return this.form.controls[control].hasError(error) && this.form.get(control)?.touched;
  }
}

