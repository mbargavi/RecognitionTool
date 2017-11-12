
import { Injectable, Inject } from '@angular/core';
import { Observable } from 'rxjs/Observable';
import { Http } from '@angular/http';
import { Component, OnInit} from '@angular/core';
import {LoginService} from '../services/login.service';

@Injectable()
export class CreditsService {

    public serverURL = localStorage.getItem('serverURL');

    constructor( @Inject(Http) private http: Http, private loginService: LoginService) {}

    getCreditsTogive():  Observable<any> {
         return this.http.get(this.serverURL + 'RecognitionTool/creditsToGive/' +
        this.loginService.userDetails.employeeId);
   }
   getCreditsEarned():  Observable<any> {
    return this.http.get(this.serverURL + 'RecognitionTool/creditsEarned/' +
   this.loginService.userDetails.employeeId);
}


}

