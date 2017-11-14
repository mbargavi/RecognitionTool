import { Injectable, Inject } from '@angular/core';
import { Observable } from 'rxjs/Observable';
import { Http } from '@angular/http';
import { Router } from '@angular/router';
import {LoginComponent} from '../login/login.component';


@Injectable()
export class LoginService {
  public userDetails;
  public myParams = new URLSearchParams();
  public message = '';


  constructor( @Inject(Http) private http: Http, private router: Router) {

  }

  getConnection(): Observable<any> {
    console.log('serverURL is ' + localStorage.getItem('serverURL'));
    return this.http.get(localStorage.getItem('serverURL') + 'login', {search: this.myParams});
  }

  fetch(Params): void {
    this.myParams = Params;
    this.getConnection().subscribe((resp) => {
      if ((resp.status === 200 )) {
        this.userDetails = resp.json();
        localStorage.setItem('Fname', this.userDetails.firstName);
        localStorage.setItem('Lname', this.userDetails.lastName);
        localStorage.setItem('titleId', this.userDetails.title.titleId);
        localStorage.setItem('user', this.userDetails);
        localStorage.setItem('empId', this.userDetails.employeeId);
        localStorage.setItem('teamId', this.userDetails.teamId);
        localStorage.setItem('Title', this.userDetails.title.titleName);
        localStorage.setItem('empId', this.userDetails.employeeId);
        localStorage.setItem('roleId', this.userDetails.roleId);
        this.message = '';
        console.log(this.userDetails);
        this.router.navigate(['main']);
         }},
        (error) => {
           if (error.status === 400) {
              this.message = 'Those credentials were invalid!';
           }
    });

}

}
