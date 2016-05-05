// Controller that works on all Feed
feedApp.controller('FeedController', function($scope, $state, HTTP, JSONs) {
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
        if(response.user)
            $scope.toolbarText.user = response.user;
        else
            $scope.toolbarText.login = response.login;
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
    }); // Title

    $scope.adsList = [];

    HTTP.get('/api/ads').success(function(response){
        $scope.adsList = response.ads;

        $timeout(function() {
            Modal.init();
        }, 2000);
    });
});
