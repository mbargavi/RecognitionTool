import { Component } from '@angular/core';
import { Injectable, Inject } from '@angular/core';
import { FormBuilder, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { Observable } from 'rxjs/Observable';
import { Http, RequestOptions, URLSearchParams } from '@angular/http';
import {LoginService} from '../services/login.service';
import { HttpParams, HttpClient } from '@angular/common/http';


@Component({
    selector: 'app-login',
    templateUrl: './login.component.html',
    styleUrls: ['./login.component.css']
  })

  @Injectable()
  export class LoginComponent {


    // public userDetails = {userName: 'David', password: 'pass'};
    public userDetails;
    public username;
    public password;
    public myParams = new URLSearchParams();
    public show = false;
    public message = '';

    constructor(private router: Router, @Inject(Http) private http: Http, private loginservice: LoginService) {
    }

    loginUser(e) {
      e.preventDefault();
      console.log(e);
      const username = e.target.elements[0].value;
      const password = e.target.elements[1].value;
      // if (username === 'admin' && password === 'admin') {
       // this.router.navigateByUrl('/main');}

      this.myParams.set('UserId', username);
      this.myParams.set('Password', password);

       this.fetch();
      // this.router.navigate(['main']); }
      // }

    }

    getConnection(): Observable<any> {
      // return this.http.get('http://localhost:8080/RecognitionTool/loginin', this.username);
      return this.http.get('http://localhost:8080/RecognitionTool/login', {search: this.myParams});

    }

    fetch(): void {
        this.getConnection().subscribe((resp) => {
          if ((resp.status === 200)) {
            this.userDetails = resp.json();
            console.log(this.userDetails);
          }
          if ( !(this.userDetails === undefined)) {
            this.router.navigate(['main']);
          }else {
            // Could run a modal here to say invalid
            console.log('turning "show" to true');
            this.message = 'Those credentials were invalid!';
            this.show = true;
          }
        }, (error) => {
               if (error.status === 400) {
                  // Could run a modal here to say invalid
                  console.log('turning "show" to true');
                  this.message = 'Those credentials were invalid!';
                  this.show = true;
               }
        });

    }
}

