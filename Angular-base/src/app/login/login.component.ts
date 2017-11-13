import { Component, Input } from '@angular/core';
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
    constructor(private router: Router, @Inject(Http) private http: Http, private loginservice: LoginService) {
    }
    public userDetails;
    public username;
    public password;
    public myParams = new URLSearchParams();
    public show = false;
    public message = this.loginservice.message;
    public ENVIRONMENT = 'TEST'; // CHANGE TO 'PROD' before "npm run build" to build production "dist" folder
    // public ENVIRONMENT = 'PROD';  // CHANGE TO 'TEST' before testing on 4200 or localhost


    loginUser(e) {
      e.preventDefault();
      if (this.ENVIRONMENT === 'TEST') {
        localStorage.setItem('serverURL', 'http://localhost:8080/RecognitionTool/');
      } else {
        localStorage.setItem('serverURL', 'http://ec2-54-159-198-200.compute-1.amazonaws.com:8080/RecognitionTool/');
      }
      console.log('SERVER URL WE ARE USING IS : ' + localStorage.getItem('serverURL'));
      const username = e.target.elements[0].value;
      const password = e.target.elements[1].value;
      this.myParams.set('UserId', username);
      this.myParams.set('Password', password);
      this.loginservice.fetch(this.myParams);
    }
}

