import { Injectable, Inject } from '@angular/core';
import { Observable } from 'rxjs/Observable';
import { Http } from '@angular/http';

@Injectable()
export class ImageService {
  // public reusableField = 5;
  public serverURL = localStorage.getItem('serverURL');

  constructor( @Inject(Http) private http: Http) {

  }

  uploadImageObservable(formdata: any): Observable<any> {
      const url = (this.serverURL + 'RecognitionTool/uploadImage');
    return this.http.post(url, formdata);
  }


  retrieveObservable(): Observable<any> {
    return this.http.get(this.serverURL + 'RecognitionTool/retrieveImage/' +
    localStorage.getItem('empId'));
  }
}
