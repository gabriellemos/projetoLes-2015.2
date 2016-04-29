/* Service responsible to load JSON files, and retrieving data as a JSON object */
regApp.factory('jsonService', function($rootScope, $http){

    return {
        // Get a JSON data
        get : function (local) {
            var url = "/assets/json/register/" + local;
            return $http.get(url);
        }
    }
});

regApp.factory('formService', function($rootScope, $http){

    return {
        post : function(form){
            return $http({
                method  : 'POST',
                url     : '/reg/' + form.type,
                data    : form.data,
                headers : {'Content-Type': 'application/json'}
            });
        }
    }
});

regApp.factory('redirectService', function($rootScope, $window){
    return {
        goto : function(url){
            $window.location.href = url;
        }
    }
});