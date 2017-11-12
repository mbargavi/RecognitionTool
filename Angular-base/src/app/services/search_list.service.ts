import { Injectable, Inject } from '@angular/core';
import { Observable } from 'rxjs/Observable';
import { Http } from '@angular/http';

@Injectable()
export class SearchListService {
  // public reusableField = 5;
  public serverURL = localStorage.getItem('serverURL');

  constructor( @Inject(Http) private http: Http) {

  }

  getSearchListObservable(): Observable<any> {
    return this.http.get(this.serverURL + 'RecognitionTool/getSearchList');
  }
}
