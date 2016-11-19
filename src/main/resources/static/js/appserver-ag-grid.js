var app = angular.module('appserverApp', ['smart-table']);

app.controller('appserverController', ['$scope', 'service', function($scope, service) {
  service.success(function(data) {
    $scope.appservers = data;
  });
}]);


app.factory('service', ['$http', function($http) {
  return $http.get('/v1/appserver')
            .success(function(data) {
              return data;
            })
            .error(function(err) {
              alert('error:' + err);
              return err;
            });
}]);

