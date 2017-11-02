import { Routes } from '@angular/router';
import {LoginComponent} from './login/login.component';
import {HistoryComponent} from './history/history.component';
import {MainComponent} from './main/main.component';
import {RedemptionComponent} from './redemption/redemption.component';
import {SearchComponent} from './search/search.component';

export const appRoutes: Routes = [
    {
      path: '',
      component: LoginComponent
    },
    {
      path: 'history',
      component: HistoryComponent
    },
    {
      path: 'main',
      component: MainComponent
    },
    {
      path: 'redeem',
      component: RedemptionComponent
    },
    {
      path: 'search',
      component: SearchComponent
    },
    // {
    //   path: '',
    //   redirectTo: '/history',
    //   pathMatch: 'full'
    // },
    // { path: '**', component: HistoryComponent}
];
