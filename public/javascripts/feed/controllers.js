// Controller that works on all Homepage
feedApp.controller('FeedController', function($scope, jsonService) {
    jsonService.get('NavbarDefault.json').success(function(data){
        $scope.navbarText = data;
    }); // Buttons and logo
    jsonService.get('FootbarDefault.json').success(function(data){
        $scope.footbarText = data;
    }); // Buttons and credits
});

// Controller that works only on Initial section
feedApp.controller('AdsController', function($scope, $http, jsonService) {
    jsonService.get('title/AdsDefault.json').success(function(data){
        $scope.labels = data;
    }); // Title
    $http.get('/api/ads').success(function(response){
        $scope.ads = response.ads.data;
    });
});
