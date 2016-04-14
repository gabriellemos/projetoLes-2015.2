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
            templateUrl: '/assets/html/feed/sections/ads.html',
            controller: 'AdsController'
        });
}]);