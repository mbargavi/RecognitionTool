import { Injectable, Inject } from '@angular/core';
import { Observable } from 'rxjs/Observable';
import { Http } from '@angular/http';

@Injectable()
export class SearchListService {
  // public reusableField = 5;

  constructor( @Inject(Http) private http: Http) {

  }

  getSearchListObservable(): Observable<any> {
    return this.http.get('http://localhost:8080/RecognitionTool/getSearchList');
  }
}
