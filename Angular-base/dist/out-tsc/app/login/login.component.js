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
var core_2 = require("@angular/core");
var router_1 = require("@angular/router");
var http_1 = require("@angular/http");
var login_service_1 = require("../services/login.service");
var LoginComponent = (function () {
    function LoginComponent(router, http, loginservice) {
        this.router = router;
        this.http = http;
        this.loginservice = loginservice;
        this.myParams = new http_1.URLSearchParams();
        this.show = false;
        this.message = this.loginservice.message;
        // public ENVIRONMENT = 'TEST'; // CHANGE TO 'PROD' before "npm run build" to build production "dist" folder
        this.ENVIRONMENT = 'PROD'; // CHANGE TO 'TEST' before testing on 4200 or localhost
    }
    LoginComponent.prototype.loginUser = function (e) {
        e.preventDefault();
        localStorage.clear();
        if (this.ENVIRONMENT === 'TEST') {
            localStorage.setItem('env', 'test');
            localStorage.setItem('serverURL', 'http://localhost:8080/RecognitionTool/');
        }
        else {
            localStorage.setItem('env', 'prod');
            localStorage.setItem('serverURL', 'http://ec2-54-159-198-200.compute-1.amazonaws.com:8080/RecognitionTool/');
        }
        console.log('SERVER URL WE ARE USING IS : ' + localStorage.getItem('serverURL'));
        var username = e.target.elements[0].value;
        var password = e.target.elements[1].value;
        this.myParams.set('UserId', username);
        this.myParams.set('Password', password);
        this.loginservice.fetch(this.myParams);
    };
    return LoginComponent;
}());
LoginComponent = __decorate([
    core_1.Component({
        selector: 'app-login',
        templateUrl: './login.component.html',
        styleUrls: ['./login.component.css']
    }),
    core_2.Injectable(),
    __param(1, core_2.Inject(http_1.Http)),
    __metadata("design:paramtypes", [router_1.Router, http_1.Http, login_service_1.LoginService])
], LoginComponent);
exports.LoginComponent = LoginComponent;
//# sourceMappingURL=login.component.js.map