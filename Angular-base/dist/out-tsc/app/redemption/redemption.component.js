"use strict";
var __decorate = (this && this.__decorate) || function (decorators, target, key, desc) {
    var c = arguments.length, r = c < 3 ? target : desc === null ? desc = Object.getOwnPropertyDescriptor(target, key) : desc, d;
    if (typeof Reflect === "object" && typeof Reflect.decorate === "function") r = Reflect.decorate(decorators, target, key, desc);
    else for (var i = decorators.length - 1; i >= 0; i--) if (d = decorators[i]) r = (c < 3 ? d(r) : c > 3 ? d(target, key, r) : d(target, key)) || r;
    return c > 3 && r && Object.defineProperty(target, key, r), r;
};
var __metadata = (this && this.__metadata) || function (k, v) {
    if (typeof Reflect === "object" && typeof Reflect.metadata === "function") return Reflect.metadata(k, v);
};
Object.defineProperty(exports, "__esModule", { value: true });
var core_1 = require("@angular/core");
var http_1 = require("@angular/http");
var router_1 = require("@angular/router");
require("rxjs/add/operator/map");
var RedemptionComponent = (function () {
    function RedemptionComponent(http, router) {
        this.http = http;
        this.router = router;
        this.selectedCreditId = 0;
        this.submitted = false;
        this.empId = localStorage.getItem('empId');
        this.teamId = localStorage.getItem('teamId');
        this.roleId = localStorage.getItem('roleId');
        this.firstName = localStorage.getItem('Fname');
        this.typeOfCreditsToRedeem = 'Personal';
        this.messageOn = false;
        this.giftURL = '';
    }
    RedemptionComponent.prototype.redeemCredits = function (e) {
        var _this = this;
        var empRedeemObj = {
            empRedeemerId: this.empId,
            creditsUsed: this.selectedAward.creditCost,
            creditTypeId: this.selectedCreditId,
            awardTypeId: this.selectedAward.awardId,
            teamRedemptionId: this.teamId,
            creditTypeToUse: this.typeOfCreditsToRedeem
        };
        this.http.post(localStorage.getItem('serverURL') + 'updateRedemptionRequest', empRedeemObj)
            .subscribe(function (resp) {
            var updateEmpRedemption = resp.json();
            if ((resp.status === 200 && updateEmpRedemption === true)) {
                _this.clearValues();
                localStorage.setItem('redemptionMade', 'true');
                _this.checkValues();
                // this.router.navigate(['main']);
            }
        });
    };
    RedemptionComponent.prototype.ngOnInit = function () {
        var _this = this;
        if (localStorage.getItem('env') === 'test') {
            this.giftURL = 'assets/images/genericgift.jpg';
        }
        else {
            this.giftURL = '././assets/images/genericgift.jpg';
        }
        this.redButtonClasses = document.getElementById('redeemSubmit').classList;
        this.http.get(localStorage.getItem('serverURL') + 'creditType').subscribe(function (resp) {
            _this.creditList = resp.json();
        });
        this.http.get(localStorage.getItem('serverURL') + 'personalCredits', { params: { empId: this.empId } })
            .subscribe(function (resp) {
            _this.empCreditsList = resp.json();
            // this.empCreditName();
            // console.log(this.empCreditsList);
        });
        this.http.get(localStorage.getItem('serverURL') + 'teamCredits', { params: { teamId: this.teamId } })
            .subscribe(function (resp) {
            _this.teamCreditsList = resp.json();
        });
    };
    RedemptionComponent.prototype.getGift = function (event) {
        if (this.selectedAward === undefined) {
            if (localStorage.getItem('env') === 'test') {
                this.giftURL = 'assets/images/' + event.srcElement.id + '.jpg';
            }
            else {
                this.giftURL = '././assets/images/' + event.srcElement.id + '.jpg';
            }
        }
    };
    RedemptionComponent.prototype.getCard = function (event) {
        if (this.selectedAward === undefined) {
            if (event.srcElement.id === '1') {
                if (localStorage.getItem('env') === 'test') {
                    this.giftURL = 'assets/images/giftcard.png';
                }
                else {
                    this.giftURL = '././assets/images/giftcard.png';
                }
            }
            else {
                if (localStorage.getItem('env') === 'test') {
                    this.giftURL = 'assets/images/genericgift.jpg';
                }
                else {
                    this.giftURL = '././assets/images/genericgift.jpg';
                }
            }
        }
    };
    RedemptionComponent.prototype.setGift = function (event) {
        if (localStorage.getItem('env') === 'test') {
            this.giftURL = 'assets/images/' + event.srcElement.id + '.jpg';
        }
        else {
            this.giftURL = '././assets/images/' + event.srcElement.id + '.jpg';
        }
    };
    RedemptionComponent.prototype.setCard = function (event) {
        if (event.srcElement.id === '1') {
            if (localStorage.getItem('env') === 'test') {
                this.giftURL = 'assets/images/giftcard.png';
            }
            else {
                this.giftURL = '././assets/images/giftcard.png';
            }
        }
        else {
            if (localStorage.getItem('env') === 'test') {
                this.giftURL = 'assets/images/genericgift.jpg';
            }
            else {
                this.giftURL = '././assets/images/genericgift.jpg';
            }
            this.selectedAward = undefined;
        }
    };
    RedemptionComponent.prototype.creditId = function () {
        var _this = this;
        this.selectedCreditId = this.selectedCredit.creditId;
        this.creditEarnedAmount(this.selectedCreditId);
        this.http.get(localStorage.getItem('serverURL') + 'awardsList', { params: { selectedCreditId: this.selectedCreditId } })
            .subscribe(function (resp) {
            _this.awards = resp.json();
            _this.awardsList = _this.awards.filter(function (awards) { return ((awards.creditCost) <= _this.earnedCredit); });
        });
    };
    RedemptionComponent.prototype.creditEarnedAmount = function (a) {
        var _this = this;
        if (this.typeOfCreditsToRedeem === 'Team') {
            this.teamCreditsList.forEach(function (element) {
                if (element.creditId === a) {
                    _this.earnedCredit = element.creditEarnedBalance;
                }
            });
        }
        else {
            this.empCreditsList.forEach(function (element) {
                if (element.credit_id === a) {
                    _this.earnedCredit = element.creditEarnedBalance;
                }
            });
        }
    };
    RedemptionComponent.prototype.empCreditName = function () {
        var _this = this;
        this.empCreditsList.forEach(function (element) {
            _this.creditList.forEach(function (b) {
                if (element.credit_id === b.creditId) {
                    element.creditName = b.creditName;
                }
            });
        });
    };
    RedemptionComponent.prototype.change = function () {
        this.checkValues();
        if (localStorage.getItem('redReadiness') === 'true') {
            if (this.redButtonClasses.contains('disabled')) {
                this.redButtonClasses.remove('disabled');
            }
        }
        else {
            if (!(this.redButtonClasses.contains('disabled'))) {
                this.redButtonClasses.add('disabled');
            }
        }
    };
    RedemptionComponent.prototype.checkValues = function () {
        // check values here
        // if credit selected and have 1 credit , set redReadiness to true
        // if 
    };
    RedemptionComponent.prototype.clearValues = function () {
        // clear values here
        this.ngOnInit();
        this.messageOn = true;
        document.getElementById('creditId').value = undefined;
        document.getElementById('awardId').value = undefined;
        this.awardsList = null;
        this.creditId();
    };
    return RedemptionComponent;
}());
RedemptionComponent = __decorate([
    core_1.Component({
        selector: 'app-redeem',
        templateUrl: './redemption.component.html',
        styleUrls: ['./redemption.component.css']
    }),
    __metadata("design:paramtypes", [http_1.Http, router_1.Router])
], RedemptionComponent);
exports.RedemptionComponent = RedemptionComponent;
//# sourceMappingURL=redemption.component.js.map