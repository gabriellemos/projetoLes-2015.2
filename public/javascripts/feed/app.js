// Declare FeedApp (used on html tag of index.html)
var feedApp = angular.module('FeedApp', ['ui.router', 'ngSanitize']);

// Make sections on the screen, to work when called via buttons
feedApp.config(["$stateProvider", "$urlRouterProvider", function($stateProvider, $urlRouterProvider) {
    // For any unmatched url, redirect to /
    $urlRouterProvider.otherwise('/');

    // States
    $stateProvider
        .state('ads', {
            url: '/',
            data: {
                title: 'Anúncios'
            },
            views: {
                'main' : {
                    templateUrl: '/assets/html/feed/sections/ads-main.html',
                    controller: 'AdsController'
                },
                "modals" : {
                    templateUrl: '/assets/html/feed/sections/ads-modals.html',
                    controller: 'AdsController'
                }
            }
        });

    // States
    $stateProvider
        .state('adsConfeiteiro', {
            url: '/',
            data: {
                title: 'Meus Anúncios'
            },
            views: {
                'main': {
                    templateUrl: '/assets/html/feed/sections/ads-main.html',
                    controller: 'AdsController'
                },
                "modals": {
                    templateUrl: '/assets/html/feed/sections/ads-modals.html',
                    controller: 'AdsController'
                }
            }
        });
}]);

// Initialize Modals -- This must be done only after all HTML are loaded.
feedApp.run(function($rootScope, $timeout) {
    $timeout(function() {
        Modal.init();
    }, 5000);
});