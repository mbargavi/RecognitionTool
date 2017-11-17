"use strict";
Object.defineProperty(exports, "__esModule", { value: true });
/** UIRouter Config  */
function uiRouterConfigFn(router) {
    // If no URL matches, go to the `Login` state by default
    router.urlService.rules.otherwise({ state: 'login' });
    // Use ui-router-visualizer to show the states as a tree
    // and transitions as a timeline
    // visualizer(router);
}
exports.uiRouterConfigFn = uiRouterConfigFn;
//# sourceMappingURL=router.config.js.map