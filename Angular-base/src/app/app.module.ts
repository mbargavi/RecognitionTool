import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { HttpModule } from '@angular/http';
import { UIRouterModule } from '@uirouter/angular';
import {NgbModule} from '@ng-bootstrap/ng-bootstrap';
import { FormsModule } from '@angular/forms';
import { ReactiveFormsModule } from '@angular/forms';
import { RouterModule, Routes } from '@angular/router';

import {AppComponent} from './app.component';
import {NavComponent} from './nav/nav.component';
import {LoginComponent} from './login/login.component';
import {HistoryComponent} from './history/history.component';
import {MainComponent} from './main/main.component';
import {RedemptionComponent} from './redemption/redemption.component';
import {SearchComponent} from './search/search.component';
import {SuccessComponent} from './success/success.component';

import {appRoutes} from './routes';
import {LoginService} from './services/login.service';
import {SearchListService} from './services/search_list.service';
import {CreditsService} from './services/credits.service';
import {HistMetricsService} from './services/histmetrics.service';
import {ImageService} from './services/image.service';
import {ChangeTitleService} from './services/changeTitle.service';
import {EmailService} from './services/email.service';



@NgModule({
  imports: [
    BrowserModule,
    HttpModule,
    ReactiveFormsModule,
    NgbModule.forRoot(),
    RouterModule.forRoot(appRoutes, {useHash: true}),
    FormsModule
  ],
  declarations: [
    AppComponent,
    NavComponent,
    LoginComponent,
    HistoryComponent,
    MainComponent,
    RedemptionComponent,
    SearchComponent,
    SuccessComponent,
   ],
  providers: [
    SearchListService,
    LoginService,
    LoginComponent,
    CreditsService,
    HistMetricsService,
    ImageService,
    ChangeTitleService,
    EmailService
   ],
  bootstrap: [AppComponent]
})
export class AppModule { }
