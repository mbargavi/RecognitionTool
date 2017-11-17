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
require("rxjs/add/operator/map");
var login_service_1 = require("../services/login.service");
var HistoryComponent = (function () {
    function HistoryComponent(http, loginservice) {
        this.http = http;
        this.loginservice = loginservice;
        this.currentView = '';
    }
    HistoryComponent.prototype.redeemCredits = function (e) {
        // do something here
    };
    HistoryComponent.prototype.ngOnInit = function () {
        this.currentView = localStorage.getItem('Histview');
        console.log('!!!! localstorage' + localStorage.getItem('Histview'));
        this.employeeId = this.loginservice.userDetails.employeeId;
        this.historicalView();
        this.teamId = localStorage.getItem('teamId');
    };
    HistoryComponent.prototype.historicalView = function () {
        var _this = this;
        console.log('this.currentView is' + this.currentView);
        if (this.currentView === 'HistoricalGiven') {
            document.getElementById('radioGiven').checked = true;
            this.http.get(localStorage.getItem('serverURL') + 'HistoricalGiven/' + this.employeeId).subscribe(function (resp) {
                _this.HistoricalGiven = resp.json();
                console.log(_this.HistoricalGiven);
            });
        }
        else {
            document.getElementById('radioEarned').checked = true;
            this.teamId = localStorage.getItem('teamId');
            this.http.get(localStorage.getItem('serverURL') + 'HistoricalEarned'
                + '?empId=' + this.employeeId + '&teamId=' + this.teamId).subscribe(function (resp) {
                _this.HistoricalEarned = resp.json();
                console.log(_this.HistoricalEarned);
            });
        }
    };
    HistoryComponent.prototype.onSelectionChange = function (event) {
        this.HistoricalGiven = '';
        this.HistoricalEarned = '';
        this.currentView = event.srcElement.value;
        console.log('!!!' + event.srcElement.value);
        this.historicalView();
    };
    return HistoryComponent;
}());
HistoryComponent = __decorate([
    core_1.Component({
        selector: 'app-history',
        templateUrl: './history.component.html',
        styleUrls: ['./history.component.css']
    }),
    __metadata("design:paramtypes", [http_1.Http, login_service_1.LoginService])
], HistoryComponent);
exports.HistoryComponent = HistoryComponent;
//# sourceMappingURL=history.component.js.map