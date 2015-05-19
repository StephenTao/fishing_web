define(['modules/controller', 'jQuery', "services/user"], function(controller, $) {
  controller.controller('indexCtrl', ['$scope', '$state', '$stateParams', function($scope, $state, $stateParams) {
    var user = {};
    user.userName = "Stephen";
    $scope.user = user;
    $scope.role = "Huang Tao";
  }]);
});