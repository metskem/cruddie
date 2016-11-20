var app = angular.module('appserverApp', ['smart-table']);

app.controller('appserverController', ['$scope', 'service', function($scope, service) {
  service.success(function(data) {
    $scope.appservers = data;
    $scope.itemsByPage=25;
  });
}]);


app.factory('service', ['$http', function($http) {
  return $http.get('/v1/appserver?size=99999')
            .success(function(data) {
              return data;
            })
            .error(function(err) {
              alert('error:' + err);
              return err;
            });
}]);

app.directive('pageSelect', function() {
      return {
        restrict: 'E',
        template: '<input type="text" class="select-page" ng-model="inputPage" ng-change="selectPage(inputPage)">',
        link: function(scope, element, attrs) {
          scope.$watch('currentPage', function(c) {
            scope.inputPage = c;
          });
        }
      }
});