regApp.controller('RegisterController', function($scope, jsonService) {
    jsonService.get('MainDefault.json').success(function(data){
        $scope.mainText = data;
    }); // Logo of the site

    jsonService.get('TitleDefault.json').success(function(data){
        $scope.titleText = data;
    }); // Title of page

    jsonService.get('TabbarDefault.json').success(function(data){
        $scope.tabbarText = data;
    }); // Bar with buttons to all sections

    jsonService.get('panels/TypeDefault.json').success(function(data){
        $scope.paneltypeText = data;
    }); // Panel choosing type of Form

    jsonService.get('panels/TermsDefault.json').success(function(data){
        $scope.paneltermsText = data;
    }); // Panel showing Terms to be signed

    jsonService.get('panels/UserDefault.json').success(function(data){
        $scope.paneluserText = data;
    }); // Panel with data to fill
});
