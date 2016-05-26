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
  HTTP, JSONs, AdUpdateManager, FormService, WAIT) {
    // Load Toolbar with new Title
    $scope.setStateTitle();
    $scope.adsList = [];
    $scope.cardEdit = true;
    $scope.labels = {};
    $scope.adEdit = true;

    JSONs.get('labels/AdsDefault.json').success(function(data){
        $scope.labels = data;
    }); // Edition Modal labels

    HTTP.get('/api/adsConfeiteiro').success(function(response){
        $scope.adsList = response.adsConfeiteiro;

        AdUpdateManager.setAds($scope.adsList);

        $scope.adsOperation = {
            "edit" : function (id, ad) {
                WAIT.get('/api/ad?id=' + id).success(function(response) {
                    $scope.adEdit = true;
                    $('#adId').val(id);
                    $('#edit-modal-content').text($scope.labels.edit.textEdit);
                    $('.inp-text').addClass('is-dirty').removeClass('is-invalid');
                    $('#title').val(response.adData.title)
                    .removeClass('ng-pristine ng-empty ng-invalid ng-invalid-required')
                    .addClass('ng-not-empty ng-dirty ng-valid-parse ng-valid ng-valid-required');
                    $('.inp-price').addClass('is-dirty').removeClass('is-invalid');
                    $('#price').val(response.adData.price)
                    .removeClass('ng-pristine ng-empty ng-invalid ng-invalid-required')
                    .addClass('ng-not-empty ng-dirty ng-valid-parse ng-valid ng-valid-required');
                    $('.inp-description').addClass('is-dirty').removeClass('is-invalid');
                    $('#description').val(response.adData.description)
                    .removeClass('ng-pristine ng-empty ng-invalid ng-invalid-required')
                    .addClass('ng-not-empty ng-dirty ng-valid-parse ng-valid ng-valid-required');
                });
            },
            "create" : function () {
                $('#adId').val("-1");
                $('#edit-modal-content').text($scope.labels.edit.textCreate);
                $('#title').val("");
                $('.inp-price').addClass('is-dirty').removeClass('is-invalid');
                $('#price').val("R$0,00")
                .removeClass('ng-pristine ng-empty ng-invalid ng-invalid-required')
                .addClass('ng-not-empty ng-dirty ng-valid-parse ng-valid ng-valid-required');
                $('#description').val("");
            },
            "hide" : function (id, ad) {
                WAIT.post('/hide/ads?id=' + id).success(function(response) {
                    ad.isHided = !ad.isHided;
                    AdUpdateManager.hideAd(id, ad.isHided);
                });
            },
            "delete" : function (id, ad) {
                WAIT.post('/del/ads?id=' + id).success(function(response) {
                    ad.isDeleted = true;
                    AdUpdateManager.deleteAd(id);
                });
            }
        };

        $timeout(function() {
            Modal.init();
        }, 2000);
    });
});
