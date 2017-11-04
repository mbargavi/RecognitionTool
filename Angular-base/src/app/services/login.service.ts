import { Injectable, Inject } from '@angular/core';
import { Observable } from 'rxjs/Observable';
import { Http } from '@angular/http';

@Injectable()
export class LoginService {
  public reusableField = 5;

  constructor( @Inject(Http) private http: Http) {

  }

  getConnection(): Observable<any> {
    return this.http.get('http://localhost:8080/RecognitionTool/login/sachin/test1234');
  }
}