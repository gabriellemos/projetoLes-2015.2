/* Directive declaring a navbar, linking to other sections of page, brand */
feedApp.directive('navBar', function() {
    return {
        restrict: 'A',
        scope: {
            item: '=item'
        },
        link: function(scope, element, attr){
            element.addClass('site-drawer mdl-layout__drawer mdl-color--brown-900 mdl-color-text-brown-50');
            componentHandler.upgradeDom();
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
        link: function(scope, element, attr){
            element.addClass('site-header mdl-layout__header mdl-color--brown-700 mdl-color-text--brown-50');
            componentHandler.upgradeDom();
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
        link: function(scope, element, attr){
            element.addClass('mdl-mini-footer');
            componentHandler.upgradeDom();
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
        link: function(scope, element, attr){
            componentHandler.upgradeDom();
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
        link: function(scope, element, attr){
            element.addClass('modal modal__bg');
            componentHandler.upgradeDom();
        },
        templateUrl: '/assets/html/feed/directives/ad-modal.html'
    };
});