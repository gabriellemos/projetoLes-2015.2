/* Directive declaring a navbar, linking to other sections of page, brand */
feedApp.directive('navBar', function() {
    return {
        restrict: 'A',
        scope: {
            item: '=item',
            category: '=category'
        },
        link: function(scope, element, attr){
            element.addClass('site-drawer mdl-layout__drawer mdl-color--brown-900 mdl-color-text-brown-50');

            scope.shouldAppear = function(name) {
                var answer = false;
                angular.forEach(scope.category, function(value, key){
                    if (value == name){
                        answer = true;
                    }
                });

                return answer;
            };

            componentHandler.upgradeDom();
        },
        templateUrl: '/assets/html/feed/directives/nav-bar.html'
    };
});

/* Directive informing section, and with button indicating login */
feedApp.directive('toolBar', function($http, $parse, $window) {
    return {
        restrict: 'A',
        scope: {
            item: '=item',
            title: '=title'
        },
        link: function(scope, element, attr){
            element.addClass('site-header mdl-layout__header mdl-color--brown-700 mdl-color-text--brown-50');

            scope.logout = function() {
                // Execute logout route, and refresh page
                $http.get('/logout').success(function() {
                    $window.location.reload();
                }).error(function() {});
            };

            scope.callFunc = function(exp) {
                if (exp == 'logout') scope.logout();
            };

            componentHandler.upgradeDom();
        },
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
            index: '=index',
            mutable: '=mutable',
            operations: '=operations'
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

/* Directive declaring a Ad Modal, showing more information of a cake announced */
feedApp.directive('editModal', function() {
    return {
        restrict: 'A',
        scope: {
            labels: '=labels',
            data: '=data'
        },
        link: function(scope, element, attr){
            element.addClass('modal modal__bg');
            componentHandler.upgradeDom();
        },
        templateUrl: '/assets/html/feed/directives/edit-modal.html'
    };
});
