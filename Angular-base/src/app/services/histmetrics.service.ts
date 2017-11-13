import { Injectable, Inject } from '@angular/core';
import { Observable } from 'rxjs/Observable';
import { Http } from '@angular/http';

@Injectable()
export class HistMetricsService {
  // public reusableField = 5;


  constructor( @Inject(Http) private http: Http) {

  }

  getHistMetricsObservable(): Observable<any> {
    return this.http.get(localStorage.getItem('serverURL') + 'getHistMetrics/' +
    localStorage.getItem('empId'));
  }
}
