
import { Injectable, Inject } from '@angular/core';
import { Observable } from 'rxjs/Observable';
import { Http } from '@angular/http';
import { Component, OnInit} from '@angular/core';

@Injectable()
export class ChangeTitleService {



    constructor( @Inject(Http) private http) {}

    getUpdatedTitleId():  Observable<any> {
         return this.http.get(localStorage.getItem('serverURL') + 'updateTitle/' +
        localStorage.getItem('empId'));
        // returns Title with titleId = -1 on error, 0 if no update, or >0 if a new titleId
        // and a new Title.titleName or null  Need to set the title in Local Storage

   }
}
