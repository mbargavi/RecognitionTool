import { Component, OnInit, Inject } from '@angular/core';
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
    public employeeId;
    public teamId;
    public historyType;
    public currentView = '';

    constructor(private http: Http, private loginservice: LoginService) {}
    redeemCredits(e) {
      // do something here
    }
    public ngOnInit() {
    this.currentView = localStorage.getItem('Histview');
    console.log('!!!! localstorage' + localStorage.getItem('Histview'));
     this.employeeId = this.loginservice.userDetails.employeeId;
     this.historicalView();
     this.teamId = localStorage.getItem('teamId');

    }

    public historicalView() {
      console.log('this.currentView is' + this.currentView );
      if (this.currentView === 'HistoricalGiven') {
        (<HTMLInputElement>document.getElementById('radioGiven')).checked = true;
      this.http.get(localStorage.getItem('serverURL') + 'HistoricalGiven/' + this.employeeId ).subscribe((resp) => {
        this.HistoricalGiven = resp.json();
       console.log(this.HistoricalGiven);
        });
    } else {
      (<HTMLInputElement>document.getElementById('radioEarned')).checked = true;
      this.teamId = localStorage.getItem('teamId');
      this.http.get(localStorage.getItem('serverURL') + 'HistoricalEarned'
       + '?empId=' + this.employeeId + '&teamId=' + this.teamId  ).subscribe((resp) => {
      this.HistoricalEarned = resp.json();
      console.log(this.HistoricalEarned);
      });
    }
    }

  public onSelectionChange(event) {
    this.HistoricalGiven = '';
    this.HistoricalEarned = '';
    this.currentView = event.srcElement.value;
    console.log('!!!' + event.srcElement.value);
    this.historicalView();
  }
  }
