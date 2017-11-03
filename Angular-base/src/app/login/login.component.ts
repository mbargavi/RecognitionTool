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

    public userDetails = [];
    public username;
    public password;

    constructor(private router: Router, @Inject(Http) private http: Http, private loginservice: LoginService) {
    }

    loginUser(e) {
      e.preventDefault();
      console.log(e);
      var username = e.target.elements[0].value;
      var password = e.target.elements[1].value;
      if(username == 'admin' && password == 'admin') {
       // this.router.navigateByUrl('/main');}
       this.fetch();
      this.router.navigate(['main']);}
    }

    getConnection(): Observable<any> {
      return this.http.get('http://localhost:8080/RecognitionTool/loginin', this.username);
    }

  fetch(): void {
        this.getConnection().subscribe((resp) => {
          this.userDetails = resp.json();
        });
        }
} 