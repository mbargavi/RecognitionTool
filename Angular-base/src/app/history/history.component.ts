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
    public HistoricalGiven;
    public HistoricalEarned;
    public employeeId = new URLSearchParams();
    public serverURL = localStorage.getItem('serverURL');

    constructor(private http: Http, private loginservice: LoginService) {}
    redeemCredits(e) {
      // do something here
    }
    public ngOnInit() {
      this.employeeId = this.loginservice.userDetails.employeeId;
      this.http.get(this.serverURL + 'RecognitionTool/HistoricalGiven/' + this.employeeId ).subscribe((resp) => {
      this.HistoricalGiven = resp.json();
      console.log(this.HistoricalGiven);
      });

      // this.http.get('http://localhost:8080/RecognitionTool/HistoricalEarned').subscribe((resp) => {
        // Read the result field from the JSON response.
      //  this.HistoricalGiven = resp.json();
      //});
    }
  }
