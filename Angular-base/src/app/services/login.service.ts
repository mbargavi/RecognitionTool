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
    return this.http.get('http://localhost:8080/RecognitionTool/login', {search: this.myParams});
  }

  fetch(Params): void {
    this.myParams = Params;
    this.getConnection().subscribe((resp) => {
      if ((resp.status === 200 && this.userDetails === undefined)) {
        this.userDetails = resp.json();
        this.router.navigate(['main']);
        console.log(this.userDetails); }}, 
        (error) => {
           if (error.status === 400) {
              this.message = 'Those credentials were invalid!';
           }
    });

}

}
