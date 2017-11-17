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
var __param = (this && this.__param) || function (paramIndex, decorator) {
    return function (target, key) { decorator(target, key, paramIndex); }
};
Object.defineProperty(exports, "__esModule", { value: true });
var core_1 = require("@angular/core");
var http_1 = require("@angular/http");
var router_1 = require("@angular/router");
var LoginService = (function () {
    function LoginService(http, router) {
        this.http = http;
        this.router = router;
        this.myParams = new URLSearchParams();
        this.message = '';
    }
    LoginService.prototype.getConnection = function () {
        console.log('serverURL is ' + localStorage.getItem('serverURL'));
        return this.http.get(localStorage.getItem('serverURL') + 'login', { search: this.myParams });
    };
    LoginService.prototype.fetch = function (Params) {
        var _this = this;
        this.myParams = Params;
        this.getConnection().subscribe(function (resp) {
            if ((resp.status === 200)) {
                _this.userDetails = resp.json();
                localStorage.setItem('Fname', _this.userDetails.firstName);
                localStorage.setItem('Lname', _this.userDetails.lastName);
                localStorage.setItem('titleId', _this.userDetails.title.titleId);
                localStorage.setItem('user', _this.userDetails);
                localStorage.setItem('empId', _this.userDetails.employeeId);
                localStorage.setItem('teamId', _this.userDetails.teamId);
                localStorage.setItem('Title', _this.userDetails.title.titleName);
                localStorage.setItem('empId', _this.userDetails.employeeId);
                localStorage.setItem('roleId', _this.userDetails.roleId);
                _this.message = '';
                console.log(_this.userDetails);
                _this.router.navigate(['main']);
            }
        }, function (error) {
            if (error.status === 400) {
                _this.message = 'Those credentials were invalid!';
            }
        });
    };
    return LoginService;
}());
LoginService = __decorate([
    core_1.Injectable(),
    __param(0, core_1.Inject(http_1.Http)),
    __metadata("design:paramtypes", [http_1.Http, router_1.Router])
], LoginService);
exports.LoginService = LoginService;
//# sourceMappingURL=login.service.js.map