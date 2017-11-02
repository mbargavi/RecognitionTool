import { Component } from '@angular/core';
import { FormBuilder, Validators } from '@angular/forms';
import { Router } from '@angular/router';

@Component({
    selector: 'app-login',
    templateUrl: './login.component.html',
    styleUrls: ['./login.component.css']
  })
  export class LoginComponent {

    constructor(private router: Router) { }

   
   loginUser(e) {
            e.preventDefault();
            console.log(e);
            var username = e.target.elements[0].value;
            var password = e.target.elements[1].value;
            if(username == 'admin' && password == 'admin') {
             // this.router.navigateByUrl('/main');}
            this.router.navigate(['main']);}
  }
}