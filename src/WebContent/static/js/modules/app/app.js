define(['angular', 'angular-ui-router', 'controllers/app'], function(angular) {
  var appModule = angular.module('appModule', ['controller', 'ui.router'], angular.noop);

  appModule.config(['$stateProvider', function($stateProvider) {
    $stateProvider.state('app', {
      url: "/app",
      controller: 'appCtrl',
      template: ""
    });
  }]);

  return appModule;
});