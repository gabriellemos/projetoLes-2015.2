/* Service responsible to load JSON files, and retrieving data as a JSON object */
feedApp.factory('jsonService', function($rootScope, $http){
    var jsonService = {};

    // Get a JSON data
    jsonService.get = function (local) {
        var url = "/assets/json/feed/" + local;

        return $http.get(url);
    };

    return jsonService;
});