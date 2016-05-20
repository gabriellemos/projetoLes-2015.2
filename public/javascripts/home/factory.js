/* Service responsible to load JSON files, and retrieving data as a JSON object */
homeApp.factory('jsonService', function($rootScope, $http){
    var jsonService = {};

    // Get a JSON data
    jsonService.get = function (local) {
        var url = "/assets/json/home/" + local;

        return $http.get(url);
    };

    return jsonService;
});