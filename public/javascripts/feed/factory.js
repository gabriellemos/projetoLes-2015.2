/* Service responsible to load JSON files, and retrieving data as a JSON object */
feedApp.factory('JSONs', function($rootScope, $http){
    var JSONs = {};

    // Get a JSON data
    JSONs.get = function (local) {
        var url = "/assets/json/feed/" + local;

        return $http.get(url);
    };

    return JSONs;
});

feedApp.factory('HTTP', function($rootScope, $http){
   var exec = {};

   return {
       get: function(url) {
           if (exec[url])
               return exec[url];
           else
               exec[url] = $http.get(url);

           return exec[url];
       }
   }
});