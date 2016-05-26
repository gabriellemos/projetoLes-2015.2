regApp.controller('RegisterController', function($scope, $http, jsonService, formService, redirectService) {
    $scope.formInfo = {};

    $scope.submit = function () {
        formService.post({
            type: 'user',
            data: $scope.formInfo
        }).success(function(data) {
            redirectService.goto('/feed');
        });
    };

    jsonService.get('MainDefault.json').success(function(data){
        $scope.mainText = data;
    }); // Logo of the site

    jsonService.get('TitleDefault.json').success(function(data){
        $scope.titleText = data;
    }); // Title of page

    jsonService.get('TabbarDefault.json').success(function(data){
        $scope.tabbarText = data;
        $scope.tabbarText.submit = $scope.submit;
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

    $http.get('/api/facebook-info').success(function(response){
        $scope.formInfo = response.formInfo;
    });
});
