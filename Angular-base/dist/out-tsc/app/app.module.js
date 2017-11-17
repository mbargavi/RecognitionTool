"use strict";
var __decorate = (this && this.__decorate) || function (decorators, target, key, desc) {
    var c = arguments.length, r = c < 3 ? target : desc === null ? desc = Object.getOwnPropertyDescriptor(target, key) : desc, d;
    if (typeof Reflect === "object" && typeof Reflect.decorate === "function") r = Reflect.decorate(decorators, target, key, desc);
    else for (var i = decorators.length - 1; i >= 0; i--) if (d = decorators[i]) r = (c < 3 ? d(r) : c > 3 ? d(target, key, r) : d(target, key)) || r;
    return c > 3 && r && Object.defineProperty(target, key, r), r;
};
Object.defineProperty(exports, "__esModule", { value: true });
var platform_browser_1 = require("@angular/platform-browser");
var core_1 = require("@angular/core");
var http_1 = require("@angular/http");
var ng_bootstrap_1 = require("@ng-bootstrap/ng-bootstrap");
var forms_1 = require("@angular/forms");
var forms_2 = require("@angular/forms");
var router_1 = require("@angular/router");
var app_component_1 = require("./app.component");
var nav_component_1 = require("./nav/nav.component");
var login_component_1 = require("./login/login.component");
var history_component_1 = require("./history/history.component");
var main_component_1 = require("./main/main.component");
var redemption_component_1 = require("./redemption/redemption.component");
var search_component_1 = require("./search/search.component");
var success_component_1 = require("./success/success.component");
var routes_1 = require("./routes");
var login_service_1 = require("./services/login.service");
var search_list_service_1 = require("./services/search_list.service");
var credits_service_1 = require("./services/credits.service");
var histmetrics_service_1 = require("./services/histmetrics.service");
var image_service_1 = require("./services/image.service");
var changeTitle_service_1 = require("./services/changeTitle.service");
var email_service_1 = require("./services/email.service");
var AppModule = (function () {
    function AppModule() {
    }
    return AppModule;
}());
AppModule = __decorate([
    core_1.NgModule({
        imports: [
            platform_browser_1.BrowserModule,
            http_1.HttpModule,
            forms_2.ReactiveFormsModule,
            ng_bootstrap_1.NgbModule.forRoot(),
            router_1.RouterModule.forRoot(routes_1.appRoutes, { useHash: true }),
            forms_1.FormsModule
        ],
        declarations: [
            app_component_1.AppComponent,
            nav_component_1.NavComponent,
            login_component_1.LoginComponent,
            history_component_1.HistoryComponent,
            main_component_1.MainComponent,
            redemption_component_1.RedemptionComponent,
            search_component_1.SearchComponent,
            success_component_1.SuccessComponent,
        ],
        providers: [
            search_list_service_1.SearchListService,
            login_service_1.LoginService,
            login_component_1.LoginComponent,
            credits_service_1.CreditsService,
            histmetrics_service_1.HistMetricsService,
            image_service_1.ImageService,
            changeTitle_service_1.ChangeTitleService,
            email_service_1.EmailService
        ],
        bootstrap: [app_component_1.AppComponent]
    })
], AppModule);
exports.AppModule = AppModule;
//# sourceMappingURL=app.module.js.map