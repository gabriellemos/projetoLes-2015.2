/* Directive declaring a navbar, linking to other sections of page, brand */
feedApp.directive('navBar', function() {
    return {
        restrict: 'A',
        scope: {
            item: '=item'
        },
        templateUrl: '/assets/html/feed/directives/nav-bar.html'
    };
});

/* Directive informing section, and with button indicating login */
feedApp.directive('toolBar', function() {
    return {
        restrict: 'A',
        scope: {
            item: '=item',
            title: '=title'
        },
        controller: ['$parse', "$http", "$window", "$state", function($scope, $parse, $http, $window, $state) {
            $scope.callFunc = function(exp) {
                //Parse the function name to get the expression and invoke it on the scope
                $parse(exp)($scope);
            };
            $scope.logout = function() {
                // Execute logout route, and refresh page
                $http.get('/logout').success(function() {
                    $window.location.reload();
                }).error(function() {});
            };
            $scope.getStateName = function(){
                // Get the current state title
                try {
                    $scope.currentState = $state.get($state.current).data.title;
                }
                catch(err) {
                    $scope.currentState =  '';
                }
            };
        }],
        templateUrl: '/assets/html/feed/directives/tool-bar.html'
    };
});

/* Directive declaring a footer bar, linking to other sections of page, and credits */
feedApp.directive('footBar', function() {
    return {
        restrict: 'A',
        scope: {
            item: '=item'
        },
        templateUrl: '/assets/html/feed/directives/foot-bar.html'
    };
});

/* Directive declaring an Ad card, showing information of a cake announced */
feedApp.directive('adCard', function() {
    return {
        restrict: 'A',
        scope: {
            item: '=item',
            labels: '=labels',
            index: '=index'
        },
        templateUrl: '/assets/html/feed/directives/ad-card.html'
    };
});

/* Directive declaring a Ad Modal, showing more information of a cake announced */
feedApp.directive('adModal', function() {
    return {
        restrict: 'A',
        scope: {
            item: '=item',
            labels: '=labels',
            index: '=index'
        },
        templateUrl: '/assets/html/feed/directives/ad-modal.html'
    };
});