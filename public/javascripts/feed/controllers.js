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
feedApp.controller('AdsController', function($scope, $timeout, HTTP, JSONs) {
    // Load Toolbar with new Title
    $scope.setStateTitle();

    JSONs.get('labels/AdsDefault.json').success(function(data){
        $scope.labels = data;
    }); // Card Labels

    $scope.adsList = [];

    HTTP.get('/api/ads').success(function(response){
        $scope.adsList = response.ads;

        $timeout(function() {
            Modal.init();
        }, 2000);
    });

    $scope.cardEdit = false;
});

// controller that works on My Ads
feedApp.controller('MyAdsController', function($scope, $timeout, HTTP, JSONs) {
    // Load Toolbar with new Title
    $scope.setStateTitle();

    JSONs.get('labels/AdsDefault.json').success(function(data){
        $scope.labels = data;
    }); // Edition Modal labels

    $scope.adsList = [];

    HTTP.get('/api/adsConfeiteiro').success(function(response){
        $scope.adsList = response.adsConfeiteiro;

        $scope.adsOperation = {
            "edit" : function (id, ad) {
                HTTP.get('/api/ad?id=' + id).success(function(response) {
                    $scope.formData = response.adData;
                });
            },
            "hide" : function (id, ad) {
                HTTP.post('/hide/ads?id=' + id).success(function(response) { });
            },
            "delete" : function (id, ad) {
                HTTP.post('/del/ads?id=' + id).success(function(response) { });
            }
        }

        $timeout(function() {
            Modal.init();
        }, 2000);
    });

    $scope.cardEdit = true;
});
