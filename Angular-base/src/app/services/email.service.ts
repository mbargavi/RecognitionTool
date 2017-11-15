import { Injectable, Inject } from '@angular/core';
import { Observable } from 'rxjs/Observable';
import { Http } from '@angular/http';

@Injectable()
export class EmailService {
  // public reusableField = 5;


  constructor( @Inject(Http) private http: Http) {

  }

  sendEmailObservable(emailParams: any): Observable<any> {
      const url = (localStorage.getItem('serverURL') + 'sendEmail');
    return this.http.post(url, emailParams);
  }

}
