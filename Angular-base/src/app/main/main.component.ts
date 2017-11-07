import { Component, OnInit } from '@angular/core';
import {LoginComponent} from '../login/login.component';
import {LoginService} from '../services/login.service';

@Component({
  selector: 'app-main',
  templateUrl: './main.component.html',
  styleUrls: ['./main.component.css']
})
export class MainComponent implements OnInit {
 
  public userDetails;

  constructor(private loginService: LoginService) { }

  public ngOnInit() {
    this.userDetails = this.loginService.userDetails;
  }

}
