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
var search_list_service_1 = require("../services/search_list.service");
var SearchComponent = (function () {
    function SearchComponent(sls) {
        this.sls = sls;
        this.combinedList = [];
        // public fieldFromService;
        this.searchValue = '';
        this.selected = false;
        this.yourSelection = '[Your Selection]';
        this.selectionEntityType = ''; // this will be a 'Team' or an 'Employee' so we know which type of selectionID we have
        // nothing to do here I think, but a SearchComponent
        // will have a SearchListService available to it
    }
    SearchComponent.prototype.makeSelection = function (event) {
        console.log(event.currentTarget.cells[1].childNodes[0].innerText);
        console.log(event.currentTarget.cells[0].childNodes[0].innerText);
        this.yourSelection = event.currentTarget.cells[2].childNodes[0].innerText;
        this.selectionId = event.currentTarget.cells[1].childNodes[0].innerText;
        this.selectionEntityType = event.currentTarget.cells[0].childNodes[0].innerText;
        localStorage.setItem('nomineeId', this.selectionId);
        localStorage.setItem('nominee', this.selectionEntityType);
        this.searchValue = '';
        this.selected = true;
        localStorage.setItem('nomineeSelected', 'true');
        document.getElementById('search').removeAttribute('class');
        document.getElementById('selection').setAttribute('class', 'optional-border');
    };
    SearchComponent.prototype.ngOnInit = function () {
        localStorage.setItem('nomineeSelected', 'false');
        this.fetch();
        // this.fieldFromService = this.fcs.reusableField;
    };
    SearchComponent.prototype.fetch = function () {
        var _this = this;
        this.sls.getSearchListObservable().subscribe(function (resp) {
            _this.combinedList = resp.json();
        });
    };
    return SearchComponent;
}());
SearchComponent = __decorate([
    core_1.Component({
        selector: 'app-search',
        templateUrl: './search.component.html',
        styleUrls: ['./search.component.css']
    }),
    __metadata("design:paramtypes", [search_list_service_1.SearchListService])
], SearchComponent);
exports.SearchComponent = SearchComponent;
//# sourceMappingURL=search.component.js.map