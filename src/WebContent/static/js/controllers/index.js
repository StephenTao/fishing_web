define(['modules/controller', 'jQuery', "services/user"], function(controller, $) {
  controller.controller('indexCtrl', ['$scope', '$state', '$stateParams', 'userService', function($scope, $state, $stateParams, userService) {
    
    userService.logout().success(function(data) {
      var result = data.data;
    });
    var user = {};
    user.userName = "Stephen";
    $scope.user = user;
    $scope.role = "Huang Tao";
  }]);
});