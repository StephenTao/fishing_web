define(['modules/controller', 'jQuery', 'jQuery-ui', 'services/user'], function(controller, $, $ui) {
  controller.controller('userCtrl', ['$scope', '$timeout', 'userService', function($scope, $timeout, userService) {
    $scope.logout = function () {
      userService.logout().success(function(data){
        alert(data);
      });
    };
  }]);
});