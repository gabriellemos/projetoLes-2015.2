// Controller that works on all Feed
feedApp.controller('FeedController', function($scope, $state, $http, $parse, $window, HTTP, JSONs) {
    // Set the title for Toolbar
    $scope.setStateTitle = function(){
        try{
            $scope.title = $state.current.data.title;
        } catch (err) {
            $scope.title = '';
        }
    };
    $scope.toolbarText = {};
    HTTP.get('api/toolbar-user').success(function(response){
        if(response.user) {
            $scope.toolbarText.user = response.user;
            $scope.categorySession = ['all', 'chef'];
        } else {
            $scope.toolbarText.login = response.login;
            $scope.categorySession = ['all'];
        }
    });
    JSONs.get('ToolbarDefault.json').success(function(data){
        $scope.toolbarText.btns = data;
    }); // Title and User btns

    JSONs.get('NavbarDefault.json').success(function(data){
        $scope.navbarText = data;
    }); // Buttons and logo

    JSONs.get('FootbarDefault.json').success(function(data){
        $scope.footbarText = data;
    }); // Buttons and credits
});

// Controller that works only on All Ads section
feedApp.controller('AdsController', function($scope, $rootScope, $timeout,
  $http, HTTP, JSONs, AdUpdateManager) {
    // Load Toolbar with new Title
    $scope.setStateTitle();
    $scope.cardEdit = false;
    $scope.adsList = [];

    JSONs.get('labels/AdsDefault.json').success(function(data){
        $scope.labels = data;
    }); // Card Labels

    HTTP.get('/api/ads').success(function(response){
        $scope.adsList = response.ads;

        AdUpdateManager.updateAds($scope.adsList);

        $timeout(function() {
            Modal.init();
        }, 2000);
    });
});

// controller that works on My Ads
feedApp.controller('MyAdsController', function($scope, $timeout, $http,
  HTTP, JSONs, AdUpdateManager, WAIT) {
    // Load Toolbar with new Title
    $scope.setStateTitle();
    $scope.adsList = [];
    $scope.cardEdit = true;

    JSONs.get('labels/AdsDefault.json').success(function(data){
        $scope.labels = data;
    }); // Edition Modal labels

    HTTP.get('/api/adsConfeiteiro').success(function(response){
        $scope.adsList = response.adsConfeiteiro;

        AdUpdateManager.setAds($scope.adsList);

        $scope.adsOperation = {
            "edit" : function (id, ad) {
                WAIT.get('/api/ad?id=' + id).success(function(response) {
                    $scope.formData = response.adData;
                });
            },
            "hide" : function (id, ad) {
                WAIT.post('/hide/ads?id=' + id).success(function(response) {
                    AdUpdateManager.hideAd(id, ad.isHided);
                });
            },
            "delete" : function (id, ad) {
                WAIT.post('/del/ads?id=' + id).success(function(response) {
                    AdUpdateManager.deleteAd(id);
                });
            }
        }

        $timeout(function() {
            Modal.init();
        }, 2000);
    });
});
