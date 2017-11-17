"use strict";
Object.defineProperty(exports, "__esModule", { value: true });
var login_component_1 = require("./login/login.component");
var history_component_1 = require("./history/history.component");
var main_component_1 = require("./main/main.component");
var redemption_component_1 = require("./redemption/redemption.component");
var success_component_1 = require("./success/success.component");
exports.appRoutes = [
    {
        path: '',
        component: login_component_1.LoginComponent
    },
    {
        path: 'main',
        component: main_component_1.MainComponent,
        children: [
            {
                path: 'history',
                component: history_component_1.HistoryComponent
            },
            {
                path: 'redeem',
                component: redemption_component_1.RedemptionComponent
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
        component: login_component_1.LoginComponent
    },
    {
        path: 'redeem',
        component: redemption_component_1.RedemptionComponent
    },
    {
        path: 'history',
        component: history_component_1.HistoryComponent
    },
    {
        path: 'success',
        component: success_component_1.SuccessComponent
    }
];
//# sourceMappingURL=routes.js.map