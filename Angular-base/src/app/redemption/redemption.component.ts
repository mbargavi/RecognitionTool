import { Component, OnInit } from '@angular/core';
import { FormBuilder, Validators } from '@angular/forms';
import { Headers, Http, RequestOptions } from '@angular/http';
import { Observable } from 'rxjs/Observable';
import {LoginService} from '../services/login.service';
import 'rxjs/add/operator/map';

@Component({
    selector: 'app-redeem',
    templateUrl: './redemption.component.html',
    styleUrls: ['./redemption.component.css']
  })
export class RedemptionComponent implements OnInit {
  public creditList;
  public awardsListAll;
  public awardsList;
  public empCreditsList;
  public teamCreditsList;
  public selectedCreditId;
  public empEarnedCredit;
  public awards;
  public empId = localStorage.getItem('empId');
  public teamId = localStorage.getItem('teamId');

  constructor(private http: Http) {}
  public redeemCredits(e) {
    // do something here
  }

  public ngOnInit() {

    this.http.get('http://localhost:8080/RecognitionTool/creditType').subscribe((resp) => {
      this.creditList = resp.json();
    });

    // this.http.get('http://localhost:8080/RecognitionTool/awardsList').subscribe((resp) => {
    //   // Read the result field from the JSON response.
    //   this.awardsList = resp.json();
    // });

    this.http.get('http://localhost:8080/RecognitionTool/personalCredits', {params : {empId: this.empId}})
    .subscribe((resp) => {
      this.empCreditsList = resp.json();
    });

    this.http.get('http://localhost:8080/RecognitionTool/teamCredits', {params : {teamId: this.teamId}})
    .subscribe((resp) => {
      this.teamCreditsList = resp.json();
    });
  }


public creditId() {
  this.selectedCreditId = this.selectedCreditId.creditId;
  this.creditEarnedAmount();
  this.http.get('http://localhost:8080/RecognitionTool/awardsList', {params :
  {selectedCreditId: this.selectedCreditId}})
  .subscribe((resp) => {
    this.awards = resp.json();
    //this.awardsList = resp.json();
    this.awardsList = this.awards.filter(
      (awards) => awards.creditCost <= this.empEarnedCredit);
  });
}

public creditEarnedAmount() {
  this.empCreditsList.forEach((element) => {
    if (element.creditId === this.selectedCreditId ) {
      this.empEarnedCredit = element.creditEarnedBalance;
  }
});
}
}
