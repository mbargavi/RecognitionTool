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

    loginUser(e) {
      e.preventDefault();
      const username = e.target.elements[0].value;
      const password = e.target.elements[1].value;
      this.myParams.set('UserId', username);
      this.myParams.set('Password', password);
      this.loginservice.fetch(this.myParams);
    }
}

