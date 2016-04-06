// Declare HomepageApp (used on html tag of index.html)
var homeApp = angular.module('HomepageApp', ['ui.router', 'ngSanitize']);

// Make sections on the screen, to work when called via buttons
homeApp.config(["$stateProvider", "$urlRouterProvider", function($stateProvider, $urlRouterProvider){
    // For any unmatched url, redirect to /
    $urlRouterProvider.otherwise("/");

    // States
    $stateProvider
        .state('initial', {
            url: "/",
            templateUrl: '/assets/html/home/sections/initial.html',
            controller: 'InitController'
        })
        .state('about', {
            url: "/",
            templateUrl: '/assets/html/home/sections/about.html',
            controller: 'AboutController'
        })
        .state('services', {
            url: "/",
            templateUrl: '/assets/html/home/sections/services.html',
            controller: 'ServicesController'
        })
        .state('contact', {
            url: "/",
            templateUrl: '/assets/html/home/sections/contact.html',
            controller: 'ContactController'
        })
}]);