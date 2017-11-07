import { Component, OnInit } from '@angular/core';
import { FormBuilder, Validators } from '@angular/forms';
import { Http, RequestOptions, Headers } from '@angular/http';
import { Observable } from 'rxjs/Observable';
import 'rxjs/add/operator/map';
import {LoginService} from '../services/login.service';

@Component({
    selector: 'app-history',
    templateUrl: './history.component.html',
    styleUrls: ['./history.component.css']
  })
  export class HistoryComponent implements OnInit {

    public userDetails;
    public creditsEarned;
    public creditsGiven;
    public employeeId = new URLSearchParams();

    constructor(private http: Http, private loginservice: LoginService) {}
    redeemCredits(e) {
      // do something here
    }
    public ngOnInit() {
      this.userDetails = this.loginservice.userDetails;
      this.employeeId = this.userDetails.employeeId;
      console.log(this.employeeId);
      this.http.get('http://localhost:8080/RecognitionTool/creditsEarned', {search: this.employeeId}).subscribe((resp) => {
      this.creditsEarned = resp.json();
      });

      this.http.get('http://localhost:8080/RecognitionTool/creditsGiven').subscribe((resp) => {
        // Read the result field from the JSON response.
        this.creditsGiven = resp.json();
      });
    }
  }