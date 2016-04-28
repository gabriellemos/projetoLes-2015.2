/* Directive declaring a form-title, showing title of registers forms */
regApp.directive('formTitle', function() {
    return {
        restrict: 'A',
        scope: {
            item: '=item'
        },
        link: function(scope, element, attr){
            element.addClass('mdl-card__title');
            componentHandler.upgradeDom();
        },
        templateUrl: '/assets/html/register/directives/form-title.html'
    };
});

/* Directive declaring a form-tabs, with tabs buttons to change page look */
regApp.directive('formTabs', ['$timeout', function($timeout) {
    return {
        restrict: 'A',
        transclude: true,
        scope: {
            item: '=item'
        },
        link: function(scope, element, attr){
            element.addClass('site-tabs mdl-tabs is-upgraded mdl-js-tabs mdl-js-ripple-effect');
            $("#tab1").delay(500).addClass("is-active");
            componentHandler.upgradeDom();
        },
        templateUrl: '/assets/html/register/directives/form-tabs.html'
    };
}]);

/* Directive declaring a layout showing only a card */
regApp.directive('layoutOneCard', function() {
    return {
        restrict: 'A',
        transclude: true,
        scope: {
            item: '=item'
        },
        link: function(scope, element, attr){
            element.addClass('site-layout mdl-layout mdl-layout--fixed-header mdl-js-layout mdl-color--grey-100');
            componentHandler.upgradeDom();
        },
        templateUrl: '/assets/html/register/directives/layout-one-card.html'
    };
});

/* Directive declaring a panel to choose form type */
regApp.directive('panelType', function() {
    return {
        restrict: 'A',
        transclude: true,
        scope: {
            item: '=item'
        },
        link: function(scope, element, attr){
            element.addClass('mdl-tabs__panel is-active');

            scope.next = function() {
                $("#tab1").removeClass("is-active");
                $("#choose-panel").removeClass("is-active");
                $("#tab2").delay(500).addClass("is-active");
                $("#terms-panel").delay(500).addClass("is-active");
            };
            componentHandler.upgradeDom();
        },
        templateUrl: '/assets/html/register/directives/panel-type.html'
    };
});

regApp.directive('panelTerms', function() {
    return {
        restrict: 'A',
        transclude: true,
        scope: {
            item: '=item'
        },
        link: function(scope, element, attr){
            element.addClass('mdl-tabs__panel');

            scope.terms_accepted = false;
            scope.terms_next_pressed = false;

            scope.next = function() {
                if(scope.terms_accepted){
                    scope.terms_next_pressed = false;
                    $("#tab2").removeClass("is-active");
                    $("#terms-panel").removeClass("is-active");
                    $("#tab3").delay(500).addClass("is-active");
                    $("#user-data-panel").delay(500).addClass("is-active");
                } else {
                    scope.terms_next_pressed = true;
                }
            };

            scope.previous = function() {
                $("#tab2").removeClass("is-active");
                $("#terms-panel").removeClass("is-active");
                $("#tab1").delay(500).addClass("is-active");
                $("#choose-panel").delay(500).addClass("is-active");
            };

            scope.change = function(){
                componentHandler.upgradeDom();
            };
            componentHandler.upgradeDom();
        },
        templateUrl: '/assets/html/register/directives/panel-terms.html'
    };
});

regApp.directive('panelUser', function() {
    return {
        restrict: 'A',
        transclude: true,
        scope: {
            item: '=item'
        },
        link: function(scope, element, attr){
            element.addClass('mdl-tabs__panel');

            scope.previous = function() {
                $("#tab3").removeClass("is-active");
                $("#user-data-panel").removeClass("is-active");
                $("#tab2").delay(500).addClass("is-active");
                $("#terms-panel").delay(500).addClass("is-active");
            };
            componentHandler.upgradeDom();
        },
        templateUrl: '/assets/html/register/directives/panel-user.html'
    };
});