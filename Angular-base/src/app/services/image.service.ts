import { Injectable, Inject } from '@angular/core';
import { Observable } from 'rxjs/Observable';
import { Http } from '@angular/http';

@Injectable()
export class ImageService {
  // public reusableField = 5;


  constructor( @Inject(Http) private http: Http) {

  }

  uploadImageObservable(formdata: any): Observable<any> {
      const url = (localStorage.getItem('serverURL') + 'uploadImage');
    return this.http.post(url, formdata);
  }


  retrieveObservable(): Observable<any> {
    return this.http.get(localStorage.getItem('serverURL') + 'retrieveImage/' +
    localStorage.getItem('empId'));
  }
}
