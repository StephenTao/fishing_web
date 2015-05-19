define([ 'angular', 'angular-ui-router', 'controllers/index'], function(angular) {

  var indexModule = angular.module('indexModule', [ 'controller', 'service', 'directive', 'ui.router' ], angular.noop);

  indexModule.config([ '$stateProvider', '$urlRouterProvider', '$httpProvider', function($stateProvider, $urlRouterProvider, $httpProvider) {
    $httpProvider.defaults.headers.common['X-Requested-With'] = 'XMLHttpRequest';

    $httpProvider.interceptors.push(function() {
      return {
          'response' : function(response) {
          if (response.data == "NotLogin") {
            var hash = document.URL.split("#")[1];
            location.href = contextPath + "#" + hash;
          } else if (response.data == "NoAccess") {
            location.href = contextPath + "/static/index.html#/noaccess";
          }
          if (response.headers()['content-type'] === "application/json;charset=UTF-8") {
            if (response.data.status != "200") {
              var message = '';
              if (response.data.length > 1) {
                $.each(response.data.data, function(key, value) {
                  message += value;
                  message += "<br>";
                });
              } else {
                message = response.data.data;
              }
              showFlashMessage(message, 'DANGER');
              return null;
            }
          }
          return response;
        }
      };
    });

    $.extend($.ui.dialog.prototype.options, {
      close : function() {
        var dialogId = $(this).attr("id");
        closeDialog(dialogId);
      }
    });

    $urlRouterProvider.otherwise("dashboard");

    $stateProvider.state('dashboard', {
      url : "/dashboard",
      controller : 'dashboardCtrl',
      templateUrl : "page/dashboard.html"
    });
  }]);

  indexModule.run([ '$rootScope', '$state', '$stateParams', '$location', 'localize',
      function($rootScope, $state, $stateParams, $location, localize) {
        $rootScope.$state = $state;
        $rootScope.$stateParams = $stateParams;

        $rootScope.$on('$stateChangeStart', function(event, toState, toParams, fromState, fromParams) {
          clearTimeout(flashMessageTime);
          $(".flash-message").slideUp();
        });
      } ]);

  return indexModule;
});