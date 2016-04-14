/* Directive declaring a navbar, linking to other sections of page, brand  */
feedApp.directive('navBar', function() {
    return {
        restrict: 'E',
        scope: {
            item: '=item'
        },
        controller: ['$scope', '$parse', "$http", "$window", function($scope, $parse, $http, $window) {
            $scope.callFunc = function(exp) {
                $parse(exp)($scope); //Parse the function name to get the expression and invoke it on the scope
            };
            $scope.logout = function() {
                $http.get('/logout').success(function() {
                    $window.location.reload();
                }).error(function() {});
            };
        }],
        templateUrl: '/assets/html/feed/directives/navbar.html'
    };
});

/* Directive declaring a footer bar, linking to other sections of page, and credits */
feedApp.directive('footBar', function() {
    return {
        restrict: 'E',
        scope: {
            item: '=item'
        },
        templateUrl: '/assets/html/feed/directives/footbar.html'
    };
});

/* Directive declaring a Ad card, showing informations of a cake announced */
feedApp.directive('titleJumbo', function() {
    return {
        restrict: 'E',
        scope: {
            item: '=item'
        },
        templateUrl: '/assets/html/feed/directives/titlejumbo.html'
    };
});

/* Directive declaring a Ad card, showing informations of a cake announced */
feedApp.directive('adCard', function() {
    return {
        restrict: 'E',
        scope: {
            item: '=item'
        },
        templateUrl: '/assets/html/feed/directives/adcard.html'
    };
});
