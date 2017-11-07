import { Injectable, Inject } from '@angular/core';
import { Observable } from 'rxjs/Observable';
import { Http } from '@angular/http';
import { Router } from '@angular/router';
import {LoginComponent} from '../login/login.component';


@Injectable()
export class LoginService {
  public userDetails;
  public myParams = new URLSearchParams();
  public show = false;
  public message = '';
  constructor( @Inject(Http) private http: Http, private router: Router) {

  }

  getConnection(): Observable<any> {
    return this.http.get('http://localhost:8080/RecognitionTool/login', {search: this.myParams});
  }

  fetch(Params): void {
    this.myParams = Params;
    this.getConnection().subscribe((resp) => {
      if ((resp.status === 200 && this.userDetails === undefined)) {
        this.userDetails = resp.json();
        this.router.navigate(['main']);
        console.log(this.userDetails);
      }
       else {
        // Could run a modal here to say invalid
        console.log('turning "show" to true');
        this.message = 'Those credentials were invalid!';
        this.show = true;
      }
    }, (error) => {
           if (error.status === 400) {
              // Could run a modal here to say invalid
              console.log('turning "show" to true');
              this.message = 'Those credentials were invalid!';
              this.show = true;
           }
    });

}

}
