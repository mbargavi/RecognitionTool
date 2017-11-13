import { Component, OnInit } from '@angular/core';
import { FormBuilder, Validators } from '@angular/forms';
import { Headers, Http, RequestOptions } from '@angular/http';
import {LoginService} from '../services/login.service';
import { Observable } from 'rxjs/Observable';
import { Router } from '@angular/router';
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
  public selectedCredit;
  public selectedCreditId = 0;
  public selectedAward;
  public empEarnedCredit;
  public awards;
  public submitted = false;
  public empId = localStorage.getItem('empId');
  public teamId = localStorage.getItem('teamId');

  constructor(private http: Http, private router: Router) {}
  public redeemCredits(e) {
    const empRedeemObj = {
      empRedeemerId: this.empId,
      creditsUsed: this.selectedAward.creditCost,
      creditTypeId: this.selectedCreditId,
      awardTypeId: this.selectedAward.awardId
    };
    this.http.post('http://localhost:8080/RecognitionTool/updateRedemptionRequest', empRedeemObj)
        .subscribe((resp) => {
          const updateEmpRedemption = resp.json();
          if ((resp.status === 200 && updateEmpRedemption === true)) {
            this.ngOnInit();
            this.router.navigate(['main']);
        }
    });
  }
  public ngOnInit() {

    this.http.get('http://localhost:8080/RecognitionTool/creditType').subscribe((resp) => {
      this.creditList = resp.json();
    });

    this.http.get('http://localhost:8080/RecognitionTool/personalCredits', {params : {empId: this.empId}})
    .subscribe((resp) => {
      this.empCreditsList = resp.json();
      //this.empCreditName();
      //console.log(this.empCreditsList);
    });

    this.http.get('http://localhost:8080/RecognitionTool/teamCredits', {params : {teamId: this.teamId}})
    .subscribe((resp) => {
      this.teamCreditsList = resp.json();
    });
  }

  public creditId() {
    this.selectedCreditId = this.selectedCredit.creditId;
    this.creditEarnedAmount(this.selectedCreditId);
    this.http.get('http://localhost:8080/RecognitionTool/awardsList', {params :
    {selectedCreditId: this.selectedCreditId}})
      .subscribe((resp) => {
        this.awards = resp.json();
        this.awardsList = this.awards.filter(
          (awards) => ((awards.creditCost) <= this.empEarnedCredit));
        });
    }

  public creditEarnedAmount(a: number) {
    this.empCreditsList.forEach((element) => {
      if (element.credit_id === a ) {
        this.empEarnedCredit = element.creditEarnedBalance;
      }
    });
  }

  public empCreditName() {
    this.empCreditsList.forEach((element) => {
      this.creditList.forEach((b) => {
      if (element.credit_id === b.creditId) {
        element.creditName = b.creditName;
      }
    });
    });
  }
}
