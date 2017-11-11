import { Injectable, Inject } from '@angular/core';
import { Observable } from 'rxjs/Observable';
import { Http } from '@angular/http';

@Injectable()
export class ImageService {
  // public reusableField = 5;

  constructor( @Inject(Http) private http: Http) {

  }

  uploadImageObservable(formdata: any): Observable<any> {
      const url = 'http://localhost:8080/RecognitionTool/uploadImage';
    return this.http.post('http://localhost:8080/RecognitionTool/uploadImage', formdata);
  }


  retrieveObservable(): Observable<any> {
    return this.http.get('http://localhost:8080/RecognitionTool/retrieveImage/' +
    localStorage.getItem('empId'));
  }
}
