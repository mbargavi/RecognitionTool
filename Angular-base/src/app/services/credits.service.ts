
import { Injectable, Inject } from '@angular/core';
import { Observable } from 'rxjs/Observable';
import { Http } from '@angular/http';
import { Component, OnInit} from '@angular/core';
import {LoginService} from '../services/login.service';

@Injectable()
export class CreditsService {



    constructor( @Inject(Http) private http: Http, private loginService: LoginService) {}

    getCreditsTogive():  Observable<any> {
    return this.http.get(localStorage.getItem('serverURL') + 'creditsToGive/' +
    this.loginService.userDetails.employeeId);
   }
    getCreditsEarned():  Observable<any> {
    return this.http.get(localStorage.getItem('serverURL') + 'creditsEarned/' +
    this.loginService.userDetails.employeeId);
   }
    getCreditsToGiveByType():  Observable<any> {
    return this.http.get(localStorage.getItem('serverURL') + 'creditsToGiveByType/' +
    this.loginService.userDetails.employeeId);
   }


}

