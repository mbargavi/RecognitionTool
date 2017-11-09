import { Routes } from '@angular/router';
import {LoginComponent} from './login/login.component';
import {HistoryComponent} from './history/history.component';
import {MainComponent} from './main/main.component';
import {RedemptionComponent} from './redemption/redemption.component';

export const appRoutes: Routes = [
    {
      path: '',
      component: LoginComponent
    },
    {
      path: 'main',
      component: MainComponent,
      children: [
        {
          path: 'history',
          component: HistoryComponent
        },
        {
          path: 'redeem',
          component: RedemptionComponent
        },
      ]
    },
    // {
    //   path: '',
    //   redirectTo: '/history',
    //   pathMatch: 'full'
    // },
    // { path: '**', component: HistoryComponent}
    {
      path: 'login',
      component: LoginComponent
  },
  {
    path: 'redeem',
    component: RedemptionComponent
  },
  {
    path: 'history',
    component: HistoryComponent
  }

];
