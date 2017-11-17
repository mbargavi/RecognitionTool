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
var login_service_1 = require("../services/login.service");
var credits_service_1 = require("../services/credits.service");
var histmetrics_service_1 = require("../services/histmetrics.service");
var image_service_1 = require("../services/image.service");
var email_service_1 = require("../services/email.service");
var search_list_service_1 = require("../services/search_list.service");
var platform_browser_1 = require("@angular/platform-browser");
var http_1 = require("@angular/http");
var http_2 = require("@angular/http");
var router_1 = require("@angular/router");
var MainComponent = (function () {
    function MainComponent(loginService, creditsService, hms, is, es, sls, http, router, sanitizer) {
        this.loginService = loginService;
        this.creditsService = creditsService;
        this.hms = hms;
        this.is = is;
        this.es = es;
        this.sls = sls;
        this.http = http;
        this.router = router;
        this.sanitizer = sanitizer;
        this.Fname = localStorage.getItem('Fname');
        this.Lname = localStorage.getItem('Lname');
        this.Title = localStorage.getItem('Title');
        this.creditsToGive = localStorage.getItem('creditsToGive');
        this.creditsEarned = localStorage.getItem('creditsEarned');
        this.picShowInput = false;
        this.currTooltip = '';
        this.messageOn = false;
        this.addrecog = false;
        this.baseURL = 'http://heartlandpreciousmetals.com/wp-content/uploads/2014/06/person-placeholder.jpg';
        this.message = '';
        this.fileSelected = false;
        this.addRecognitionStatus = false;
    }
    MainComponent.prototype.ngOnInit = function () {
        this.getValues();
        this.recButtonClasses = document.getElementById('recButton').classList;
    };
    MainComponent.prototype.change = function () {
        this.checkRecognitionReadiness();
        if (localStorage.getItem('recReadiness') === 'true') {
            if (this.recButtonClasses.contains('disabled')) {
                this.recButtonClasses.remove('disabled');
            }
        }
        else {
            if (!(this.recButtonClasses.contains('disabled'))) {
                this.recButtonClasses.add('disabled');
            }
        }
    };
    MainComponent.prototype.inputPic = function () {
        this.picShowInput = true;
    };
    MainComponent.prototype.uploadImage = function () {
        var _this = this;
        // event.preventDefault();
        if (this.fileSelected) {
            this.picShowInput = false;
            var files = document.getElementById('selectFile').files;
            var formData = new FormData();
            var file = files[0];
            console.log(files[0]);
            formData.append('file', file, file.name);
            formData.append('empId', this.loginService.userDetails.employeeId);
            this.is.uploadImageObservable(formData).subscribe(function (resp) {
                _this.getImage();
            }, function (error) {
                _this.message = 'Upload failed! Revert to last pic';
                console.log(_this.message);
                _this.getImage();
            });
        }
    };
    MainComponent.prototype.dataLoaded = function (data) {
        // this.elem.querySelector('#spinner').setAttribute('style', 'visibility:hidden;'); // need to add spinner element first
    };
    MainComponent.prototype.onSelectionChange = function (event) {
        localStorage.setItem('creditsTypeId', event.srcElement.value);
        console.log(localStorage.getItem('creditsTypeId'));
        this.change();
    };
    MainComponent.prototype.addRecognition = function () {
        this.checkRecognitionReadiness();
        if (localStorage.getItem('recReadiness') === 'true') {
            this.getAddRecognitionResponse();
        }
        else {
            // They haven't input all the values needed to make a recognition
            // Could give message to that effect or do nothing.
        }
    };
    MainComponent.prototype.addRecognitionObservable = function () {
        var headers = new http_2.Headers();
        headers.append('Content-Type', 'application/json');
        var body = { 'empNominatorId': this.loginService.userDetails.employeeId,
            'nomineeId': localStorage.getItem('nomineeId'),
            'creditTypeId': localStorage.getItem('creditsTypeId'),
            'nominee': localStorage.getItem('nominee') };
        return this.http.post(localStorage.getItem('serverURL') + 'addRecognition', body, { headers: headers });
    };
    MainComponent.prototype.getAddRecognitionResponse = function () {
        var _this = this;
        this.addRecognitionObservable().subscribe(function (resp) {
            if ((resp.status === 200)) {
                console.log('here in success');
                _this.message = 'Successfull submission!';
                _this.messageOn = true;
                _this.ngOnInit(); // calling this refreshes page numbers but more work needed to clear selected values;
                console.log('here in success');
                _this.sls.getSearchListObservable().subscribe(function (response) {
                    _this.fullList = response.json;
                    console.log(_this.fullList);
                });
                var emailForm = new FormData();
                if (document.getElementById('copyManager').checked === true) {
                    _this.recipientCount = '4';
                }
                else {
                    _this.recipientCount = '3';
                }
                // if 1 radio button is checked, set creditText to "1 Credit", else set creditText to "5 Capital One Bucks"
                if (document.getElementById('creditRadio').childNodes[1].childNodes[1].childNodes[0].checked === true) {
                    _this.creditText = document.getElementById('creditRadio').childNodes[1].childNodes[1].innerText;
                }
                else {
                    _this.creditText = document.getElementById('creditRadio').childNodes[3].childNodes[1].innerText;
                }
                console.log('creditText = ' + _this.creditText);
                emailForm.append('recipientCount', _this.recipientCount);
                emailForm.append('subject', 'Recognition Feedback!');
                emailForm.append('message', document.getElementById('primaryFeedback').value);
                emailForm.append('manMessage', document.getElementById('addFeedback').value);
                emailForm.append('competencies', localStorage.getItem('competencyString'));
                emailForm.append('nomineeId', localStorage.getItem('nomineeId'));
                emailForm.append('nominatorId', localStorage.getItem('empId'));
                emailForm.append('credits', _this.creditText);
                emailForm.append('entityType', localStorage.getItem('nominee'));
                _this.clearValues();
                if (!(_this.recButtonClasses.contains('disabled'))) {
                    _this.recButtonClasses.add('disabled');
                }
                console.log(emailForm);
                _this.es.sendEmailObservable(emailForm).subscribe(function (response) {
                    console.log(response.json);
                });
                // this.router.navigate(['success']);
            }
        }, function (error) {
            if (error.status === 417) {
                _this.addRecognitionStatus = true;
                _this.errorMessage = 'No credits left, add recognition failed';
            }
            else {
                _this.addRecognitionStatus = true;
                _this.errorMessage = 'Server error, add recognition failed';
            }
        });
    };
    MainComponent.prototype.getValues = function () {
        var _this = this;
        this.creditsService.getCreditsTogive().subscribe(function (resp) {
            _this.creditsToGive = resp.json();
            localStorage.setItem('creditsToGive', _this.creditsToGive);
        });
        this.creditsService.getCreditsEarned().subscribe(function (resp) {
            _this.creditsEarned = resp.json();
            localStorage.setItem('creditsEarned', _this.creditsEarned);
        });
        this.hms.getHistMetricsObservable().subscribe(function (resp) {
            _this.metrics = resp.json();
            console.log(_this.metrics);
            console.log(_this.metrics[0]);
            console.log(_this.metrics[1]);
            localStorage.setItem('totalGiven', _this.metrics[0]);
            localStorage.setItem('totalEarned', _this.metrics[1]);
            _this.histGiven = _this.metrics[0];
            _this.histEarned = _this.metrics[1];
        });
        this.creditsService.getCreditsToGiveByType().subscribe(function (resp) {
            _this.creditsByType = resp.json();
            console.log('first' + _this.creditsByType[0]);
            console.log('first' + _this.creditsByType[1]);
            localStorage.setItem('currentCredits', _this.creditsByType[0]);
            localStorage.setItem('currentCap1Credits', _this.creditsByType[1]);
            _this.credits = _this.creditsByType[0];
            _this.capOneCredits = _this.creditsByType[1];
            _this.currTooltip = 'Credits: ' + _this.credits + ', ' + 'CapOne Bucks: ' + _this.capOneCredits;
        });
        this.getImage();
    };
    MainComponent.prototype.clearValues = function () {
        // clear the values here
        document.getElementById('commBox').checked = false;
        document.getElementById('custBox').checked = false;
        document.getElementById('judgeBox').checked = false;
        document.getElementById('jobBox').checked = false;
        document.getElementById('valueBox').checked = false;
        document.getElementById('teamBox').checked = false;
        // localStorage.setItem('clearSelection', 'true');
        // Still need some logic to clear the search component selection
        localStorage.setItem('competencySelected', 'false');
        localStorage.setItem('nomineeSelected', 'false');
        localStorage.setItem('creditsTypeId', null);
        // clearing the two radio buttons
        document.getElementById('creditRadio').childNodes[1].childNodes[1].childNodes[0].checked = false;
        document.getElementById('creditRadio').childNodes[3].childNodes[1].childNodes[0].checked = false;
        document.getElementById('primaryFeedback').value = '';
        localStorage.setItem('competencySelected', 'false');
    };
    MainComponent.prototype.getImage = function () {
        var _this = this;
        this.is.retrieveObservable().subscribe(function (resp) {
            _this.profileURL = _this.sanitizer.bypassSecurityTrustUrl(resp.url);
            console.log(resp.url);
        }, function (error) {
            _this.message = 'File retrieval failed! Probably no profile pic saved';
            console.log(_this.message);
            _this.profileURL = _this.sanitizer.bypassSecurityTrustUrl(_this.baseURL);
            // this.profileURL = this.baseURL;
        });
        console.log('Retrieval done...this is what we got: ' + this.profileURL);
        // if (this.profileURL === undefined) {
        //   console.log('Setting profileURL since it was undefined');
        //   this.profileURL = this.sanitizer.bypassSecurityTrustUrl(this.baseURL);
        //   // this.profileURL = 'http://heartlandpreciousmetals.com/wp-content/uploads/2014/06/person-placeholder.jpg';
        //   console.log(this.profileURL);
        // }
    };
    MainComponent.prototype.setHistoricalGiven = function () {
        localStorage.setItem('Histview', 'HistoricalGiven');
        this.router.navigate(['history']);
    };
    MainComponent.prototype.setHistoricalEarned = function () {
        localStorage.setItem('Histview', 'HistoricalEarned');
        this.router.navigate(['history']);
    };
    MainComponent.prototype.sendEmail = function (email) {
        this.es.sendEmailObservable(email).subscribe(function (resp) {
            console.log(resp.url);
        }, function (error) {
            console.log(error);
        });
    };
    MainComponent.prototype.buildCompetencyString = function () {
        var competencyString = '<br><ul>';
        if (document.getElementById('commBox').checked === true) {
            competencyString += '<li><strong>Communication</strong></li>';
        }
        if (document.getElementById('custBox').checked === true) {
            competencyString += '<li><strong>Customer Focus</strong></li>';
        }
        if (document.getElementById('judgeBox').checked === true) {
            competencyString += '<li><strong>Judgement</strong></li>';
        }
        if (document.getElementById('jobBox').checked === true) {
            competencyString += '<li><strong>Job Specific Skills</strong></li>';
        }
        if (document.getElementById('valueBox').checked === true) {
            competencyString += '<li><strong>Live the Values</strong></li>';
        }
        if (document.getElementById('teamBox').checked === true) {
            competencyString += '<li><strong>Teamwork</strong></li>';
        }
        competencyString += '</ul><br>';
        localStorage.setItem('competencyString', competencyString);
    };
    MainComponent.prototype.checkRecognitionReadiness = function () {
        if (document.getElementById('commBox').checked === true ||
            (document.getElementById('custBox').checked === true) ||
            (document.getElementById('judgeBox').checked === true) ||
            (document.getElementById('jobBox').checked === true) ||
            (document.getElementById('valueBox').checked === true) ||
            (document.getElementById('teamBox').checked === true)) {
            localStorage.setItem('competencySelected', 'true');
            this.buildCompetencyString();
        }
        else {
            localStorage.setItem('competencySelected', 'false');
        }
        if (document.getElementById('creditRadio').childNodes[1].childNodes[1].childNodes[0].checked === true) {
            if (this.credits > 0) {
                localStorage.setItem('hasRightCredits', 'true');
            }
            else {
                localStorage.setItem('hasRightCredits', 'false');
            }
        }
        else if (document.getElementById('creditRadio').childNodes[3].childNodes[1].childNodes[0].checked === true) {
            if (this.capOneCredits > 4) {
                localStorage.setItem('hasRightCredits', 'true');
            }
            else {
                localStorage.setItem('hasRightCredits', 'false');
            }
        }
        if ((localStorage.getItem('nomineeSelected') === 'true') &&
            (!(localStorage.getItem('creditsTypeId') === null)) &&
            (!(document.getElementById('primaryFeedback').value === '')) &&
            (localStorage.getItem('hasRightCredits') === 'true') &&
            (localStorage.getItem('competencySelected') === 'true')) {
            localStorage.setItem('recReadiness', 'true');
        }
        else {
            localStorage.setItem('recReadiness', 'false');
        }
    };
    return MainComponent;
}());
MainComponent = __decorate([
    core_1.Component({
        selector: 'app-main',
        templateUrl: './main.component.html',
        styleUrls: ['./main.component.css']
    }),
    __param(6, core_1.Inject(http_1.Http)),
    __metadata("design:paramtypes", [login_service_1.LoginService,
        credits_service_1.CreditsService,
        histmetrics_service_1.HistMetricsService,
        image_service_1.ImageService,
        email_service_1.EmailService,
        search_list_service_1.SearchListService,
        http_1.Http,
        router_1.Router,
        platform_browser_1.DomSanitizer])
], MainComponent);
exports.MainComponent = MainComponent;
//# sourceMappingURL=main.component.js.map