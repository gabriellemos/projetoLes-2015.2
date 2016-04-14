// Controller that works on all Homepage
homeApp.controller('HomeController', function($scope, jsonService) {
    jsonService.get('NavbarDefault.json').success(function(data){
        $scope.navbarText = data;
    }); // Buttons and logo
    jsonService.get('FootbarDefault.json').success(function(data){
        $scope.footbarText = data;
    }); // Buttons and credits
});

// Controller that works only on Initial section
homeApp.controller('InitController', function($scope, jsonService) {
    jsonService.get('HeaderDefault.json').success(function(data){
        $scope.header = data;
    }); // Poster attracting clients
});

// Controller that works only on About section
homeApp.controller('AboutController', function($scope, jsonService) {
    jsonService.get('banners/AboutDefault.json').success(function(data){
        $scope.bnIni = data;
    }); // Banner title
    jsonService.get('paragraphs/HowWorksDefault.json').success(function(data){
        $scope.parA = data;
    }); // How the site works
    jsonService.get('content/AreasDefault.json').success(function(data){
        $scope.conA = data;
    }); // Where the site works
    jsonService.get('content/MotivationDefault.json').success(function(data){
        $scope.conB = data;
    }); // History of the site
    jsonService.get('banners/RecallDefault.json').success(function(data){
        $scope.bnEnd = data;
    }); // Banner recalling client
});

// Controller that works only on Services section
homeApp.controller('ServicesController', function($scope, jsonService) {
    jsonService.get('banners/ServicesDefault.json').success(function(data){
        $scope.bnIni = data;
    }); // Banner title
    jsonService.get('content/AdsDefault.json').success(function(data){
        $scope.conA = data;
    }); // We have ads
    jsonService.get('content/ClientsDefault.json').success(function(data){
        $scope.conB = data;
    }); // We support clients comments
    jsonService.get('content/ChefsDefault.json').success(function(data){
        $scope.conC = data;
    }); // We support chefs assistance
    jsonService.get('banners/RecallDefault.json').success(function(data){
        $scope.bnEnd = data;
    }); // Banner recalling client
});

// Controller that works only on Contact section
homeApp.controller('ContactController', function($scope, jsonService) {
    jsonService.get('banners/ContactDefault.json').success(function(data){
        $scope.bnIni = data;
    }); // Banner title
    jsonService.get('paragraphs/FurtherHelpDefault.json').success(function(data){
        $scope.parA = data;
    }); // We'll add more info
    jsonService.get('contacts/ThreeOnesDefault.json').success(function(data){
        $scope.contactA = data;
    }); // 3 persons of team
    jsonService.get('contacts/TwoOnesDefault.json').success(function(data){
        $scope.contactB = data;
    }); // 2 persons of team
    jsonService.get('banners/RecallDefault.json').success(function(data){
        $scope.bnEnd = data;
    }); // Banner recalling client
});