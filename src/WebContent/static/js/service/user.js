define(['modules/service'] , function (service) {
  service.factory("userService", ['$http', function($http) {
    return {
      logout: function() {
        return $http({
          url: baseURL + "/user/logout",
          method: "GET"
        });
      }
    };
  }]);
});