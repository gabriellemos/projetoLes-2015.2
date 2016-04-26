// Controller that works on all Feed
feedApp.controller('FeedController', ["$scope", "$state", "$http", "jsonService", function($scope, $state, $http, jsonService) {
    // Set the title for Toolbar
    $scope.setStateTitle = function(){
        try{
            $scope.title = $state.current.data.title;
        } catch (err) {
            $scope.title = '';
        }
    };
    $scope.toolbarText = {};
    $http.get('api/toolbar-user').success(function(response){
        if(response.user)
            $scope.toolbarText.user = response.user.data;
    });
    jsonService.get('ToolbarDefault.json').success(function(data){
        $scope.toolbarText.btns = data;
    }); // Title and User btns

    jsonService.get('NavbarDefault.json').success(function(data){
        $scope.navbarText = data;
    }); // Buttons and logo

    jsonService.get('FootbarDefault.json').success(function(data){
        $scope.footbarText = data;
    }); // Buttons and credits
}]);

// Controller that works only on All Ads section
feedApp.controller('AdsController', function($scope, $http, jsonService) {
    // Load Toolbar with new Title
    $scope.setStateTitle();
    jsonService.get('labels/AdsDefault.json').success(function(data){
        $scope.labels = data;
    }); // Title
    $http.get('/api/ads').success(function(response){
        $scope.adsList = response.ads.data;
    });
});
