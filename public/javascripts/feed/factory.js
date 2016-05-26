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
       },
       post: function(url) {
           if (exec[url])
               return exec[url];
           else
               exec[url] = $http.post(url);

           return exec[url];
       }
   }
});

feedApp.factory('WAIT', function($rootScope, $http){
   var status = {};

   return {
       busy: function(){
            for (var key in status) {
                if (status[key] > 0)
                    return true;
            }
            return false;
       },
       release: function(){
            status = {};
       },
       get: function(url) {
           var promise = $http.get(url);
           if(status[url]){
              status[url] = 1 + status[url];
           } else {
              status[url] = 1;
           }
           promise.finally(function(response){
              status[url] = status[url] - 1;
           });
           return promise;
       },
       post: function(url) {
           var promise = $http.post(url);
           if(status[url]){
              status[url] = 1 + status[url];
           } else {
              status[url] = 1;
           }
           promise.finally(function(response){
              status[url] = status[url] - 1;
           });
           return promise;
       }
   }
});


feedApp.factory('AdUpdateManager', function($rootScope){
   var adsConf = [];
   var index = {};

   return {
       setAds: function(ads) {
           index = {};
           adsConf = ads;
           for(i = 0; i < adsConf.length; i++){
              index[adsConf[i].id] = i;
           }
       },
       updateAds: function(ads) {
            if(adsConf){
                for(i = 0; i < ads.length; i++){
                    if(ads[i].id in index){
                        ads[i].title = adsConf[index[ads[i].id]].title;
                        ads[i].chef = adsConf[index[ads[i].id]].chef;
                        ads[i].imglink = adsConf[index[ads[i].id]].imglink;
                        ads[i].price = adsConf[index[ads[i].id]].price;
                        ads[i].contacts = adsConf[index[ads[i].id]].contacts;
                        ads[i].address = adsConf[index[ads[i].id]].address;
                        ads[i].isDeleted = adsConf[index[ads[i].id]].isDeleted;
                        ads[i].isHided = adsConf[index[ads[i].id]].isHided;
                    }
                }
            }
       },
       isEmpty: function(){
          return adsConf.length > 0;
       },
       hideAd: function(id, value){
            if(id in index){
                adsConf[index[id]].isHided = value;
            }
       },
       deleteAd: function(id){
            if(id in index){
                adsConf[index[id]].isDeleted = true;
            }
       },
       editAd: function(id, ad){
            if(id in index){
                adsConf[index[id]].title = ad.title;
                adsConf[index[id]].price = ad.price;
                adsConf[index[id]].isDeleted = ad.isDeleted;
                adsConf[index[id]].isHided = ad.isHided;
            }
       }
   }
});

feedApp.factory('FormService', function($rootScope, $http){
    return {
        modify : function(form){
            return $http({
                method  : 'POST',
                url     : '/mod/' + form.type,
                data    : form.data,
                headers : {'Content-Type': 'application/json'}
            });
        },
        create : function(form){
            return $http({
                method  : 'POST',
                url     : '/reg/' + form.type,
                data    : form.data,
                headers : {'Content-Type': 'application/json'}
            });
        }
    }
});
