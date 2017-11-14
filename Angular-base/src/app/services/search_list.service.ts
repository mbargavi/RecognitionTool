import { Injectable, Inject } from '@angular/core';
import { Observable } from 'rxjs/Observable';
import { Http } from '@angular/http';

@Injectable()
export class SearchListService {
  // public clearSelection = false;


  constructor( @Inject(Http) private http: Http) {

  }

  getSearchListObservable(): Observable<any> {
    return this.http.get(localStorage.getItem('serverURL') + 'getSearchList/' +
    localStorage.getItem('empId'));
  }
}
