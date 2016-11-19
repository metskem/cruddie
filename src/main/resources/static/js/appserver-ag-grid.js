var appserverApp = angular.module('appserverApp', ['agGrid', 'infinite-scroll', 'ngStorage']);

appserverApp.service('appserverService', ['$http', '$q', function($http, $q) {
    var deferred = $q.defer();
    this.getData = function(appOrGroup, preSelectService) {
        return $http({
            method: 'GET',
            url: '/v1/appserver',
        });
    };
}]);

appserverApp.controller('appserverCtrl', ['$scope', '$interval', '$http', '$localStorage', '$location', 'appserverService', function($scope, $interval, $http, $localStorage, $location, appserverService ) {

    var self = this;
    self.appserverSearch = "";
    self.appserverInfo = "";

    $scope.$storage = $localStorage.$default({
        appserverSearch: self.appserverSearch
    });

    function getServiceInfo() {
        appserverDataService.getData(QueryStringToJSON().queryName, QueryStringToJSON().queryParmValue).then(function(result) {
                self.appserverInfo = result.data;
                self.gridOptions.api.setRowData(result.data);
                self.gridOptions.api.setQuickFilter($scope.$storage.appserverSearch);
                //console.info('timeout: ', self.Timeout)
        });
    }
    getServiceInfo();
//    console.info(this, self.appserverInfo);

    $scope.$watch(function() { return  $scope.$storage.appserverSearch}, function(){
        self.gridOptions.api.setQuickFilter($scope.$storage.appserverSearch);
    }, true);

// Grid definition below

    var columnDefs = [
        {headerName: "id", field: "id", suppressMenu: true, cellRenderer: idRenderer, width: 60 },
        {headerName: "hostname" },
        {headerName: "location"},
        {headerName: "created", valueGetter: 'data.metadata.created', suppressMenu: true, cellRenderer: dateRenderer, width: 100},
        {headerName: "lastchanged", valueGetter: "data.metadata.lastChanged", suppressMenu: true, cellRenderer: dateRenderer, width: 100},
        {headerName: "lastchangedby", valueGetter: "data.metadata.lastChangedBy", width: 120}
    ];

    self.gridOptions = {
        columnDefs: columnDefs,
        rowSelection: 'single',
        enableColResize: true,
        enableSorting: true,
        enableFilter: true,
        rowHeight: 22,
        suppressRowClickSelection: false
    };

// helper functions below

     function pad(s) { return (s < 10) ? '0' + s : s; }

     function QueryStringToJSON() {
         var pairs = location.search.slice(1).split('&');

         var result = {};
         pairs.forEach(function(pair) {
             pair = pair.split('=');
             result[pair[0]] = decodeURIComponent(pair[1] || '');
         });
         return JSON.parse(JSON.stringify(result));
     }

// Renderer functions below

     function dateRenderer(params) {
        var value = params.value;
        var flag;
	var d = new Date(value);
	flag =  "<div class=klein>" + d.getFullYear() + '/' + pad(d.getMonth()+1) + '/' + pad(d.getDate())  + ' ' + pad(d.getHours()) + ':' + pad(d.getMinutes()) + "</div>";
	return flag;
     }

    function idRenderer(params) {
        var id = params.value;
	    return '<a href="/v1/appserver/' + id +  '" title="edit">' + id + '</a>';
    }

    function escapedHtmlRenderer(params) {
        var id = params.value;
        return id.replace('>', "&gt;").replace('<', "&lt;");
    }

}]);
