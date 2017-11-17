import { Component, OnInit } from '@angular/core';
import { FormBuilder, RadioControlValueAccessor, Validators } from '@angular/forms';
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
  public earnedCredit;
  public awards;
  public submitted = false;
  public empId = localStorage.getItem('empId');
  public teamId = localStorage.getItem('teamId');
  public roleId = localStorage.getItem('roleId');
  public firstName = localStorage.getItem('Fname');
  public typeOfCreditsToRedeem = 'Personal';
  public messageOn = false;
  public giftURL = '';
  // public giftURL = 'assets/images/cup.jpg';

  constructor(private http: Http, private router: Router) {}
  public redeemCredits(e) {
    const empRedeemObj = {
      empRedeemerId: this.empId,
      creditsUsed: this.selectedAward.creditCost,
      creditTypeId: this.selectedCreditId,
      awardTypeId: this.selectedAward.awardId,
      teamRedemptionId: this.teamId,
      creditTypeToUse: this.typeOfCreditsToRedeem
    };
    this.http.post(localStorage.getItem('serverURL') + 'updateRedemptionRequest', empRedeemObj)
        .subscribe((resp) => {
          const updateEmpRedemption = resp.json();
          if ((resp.status === 200 && updateEmpRedemption === true)) {
             this.ngOnInit();
             this.creditId();
             //localStorage.setItem('messageOn', 'true');
             //console.log('just set localStorage messageOn = ' + localStorage.getItem('messageOn'));
             this.messageOn = true;
            // (<HTMLInputElement>document.getElementById('creditId')).value = undefined;
            // (<HTMLInputElement>document.getElementById('awardId')).value = undefined;
            // this.awardsList = null;
            //this.router.navigate(['main']);
        }
    });
  }
  public ngOnInit() {

    // this.messageOn = localStorage.getItem('messageOn');
    // console.log('ngOnInit setting value of messageOn = ' + this.messageOn);

    if (localStorage.getItem('env') === 'test') {
      this.giftURL = 'assets/images/genericgift.jpg';
    }else {
      this.giftURL = '././assets/images/genericgift.jpg';
    }


    this.http.get(localStorage.getItem('serverURL') + 'creditType').subscribe((resp) => {
      this.creditList = resp.json();
    });

    this.http.get(localStorage.getItem('serverURL') + 'personalCredits', {params : {empId: this.empId}})
    .subscribe((resp) => {
      this.empCreditsList = resp.json();
      // this.empCreditName();
      // console.log(this.empCreditsList);
    });

    this.http.get(localStorage.getItem('serverURL') + 'teamCredits', {params : {teamId: this.teamId}})
    .subscribe((resp) => {
      this.teamCreditsList = resp.json();
    });
  }

  public getGift(event) {

    if (this.selectedAward === undefined) {
      if (localStorage.getItem('env') === 'test') {
        this.giftURL = 'assets/images/' + event.srcElement.id + '.jpg';
      }else {
        this.giftURL = '././assets/images/' + event.srcElement.id + '.jpg';
      }
    }
  }

  public getCard(event) {

    if (this.selectedAward === undefined) {
      if (event.srcElement.id === '1') {
        if (localStorage.getItem('env') === 'test') {
          this.giftURL = 'assets/images/giftcard.png';
        }else {
          this.giftURL = '././assets/images/giftcard.png';
        }
      }else {
        if (localStorage.getItem('env') === 'test') {
          this.giftURL = 'assets/images/genericgift.jpg';
        }else {
          this.giftURL = '././assets/images/genericgift.jpg';
        }
      }
    }
  }

  public setGift(event) {
    if (localStorage.getItem('env') === 'test') {
      this.giftURL = 'assets/images/' + event.srcElement.id + '.jpg';
    }else {
      this.giftURL = '././assets/images/' + event.srcElement.id + '.jpg';
    }
  }

  public setCard(event) {
      if (event.srcElement.id === '1') {
        if (localStorage.getItem('env') === 'test') {
          this.giftURL = 'assets/images/giftcard.png';
        }else {
          this.giftURL = '././assets/images/giftcard.png';
        }
      }else {
        if (localStorage.getItem('env') === 'test') {
          this.giftURL = 'assets/images/genericgift.jpg';
        }else {
          this.giftURL = '././assets/images/genericgift.jpg';
        }
        this.selectedAward = undefined;
      }
  }

  public creditId() {
    this.selectedCreditId = this.selectedCredit.creditId;
    this.creditEarnedAmount(this.selectedCreditId);
    this.http.get(localStorage.getItem('serverURL') + 'awardsList', {params :
    {selectedCreditId: this.selectedCreditId}})
      .subscribe((resp) => {
        this.awards = resp.json();
        this.awardsList = this.awards.filter(
          (awards) => ((awards.creditCost) <= this.earnedCredit));
        });
    }

  public creditEarnedAmount(a: number) {
    if (this.typeOfCreditsToRedeem === 'Team') {
      this.teamCreditsList.forEach((element) => {
        if (element.creditId === a ) {
          this.earnedCredit = element.creditEarnedBalance;
        }
      });
    } else {
      this.empCreditsList.forEach((element) => {
        if (element.credit_id === a ) {
          this.earnedCredit = element.creditEarnedBalance;
        }
      });
    }
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
