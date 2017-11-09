import { Component, OnInit} from '@angular/core';
import {LoginComponent} from '../login/login.component';
import {LoginService} from '../services/login.service';

@Component({
  selector: 'app-main',
  templateUrl: './main.component.html',
  styleUrls: ['./main.component.css']
})
export class MainComponent {

  public userDetails;
  public Fname = localStorage.getItem('Fname');
  public Lname = localStorage.getItem('Lname');
  public Title = localStorage.getItem('Title');

  // constructor(private loginService: LoginService) { }

  // public ngOnInit() {
  //   this.userDetails = this.loginService.userDetails;
  // }

}
