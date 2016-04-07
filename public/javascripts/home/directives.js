/* Directive declaring a navigation bar, linking to other sections of page, and containing logo */
homeApp.directive('navBar', function(){
    return {
        restrict: 'E',
        scope: {
            item: '=item'
        },
        templateUrl: '/assets/html/home/directives/navbar.html'
    };
});
/* Directive declaring a footer bar, linking to other sections of page, and credits */
homeApp.directive('footBar', function(){
    return {
        restrict: 'E',
        scope: {
            item: '=item'
        },
        templateUrl: '/assets/html/home/directives/footbar.html'
    };
});
/* Directive declaring header, containing title, text, main buttons, and some impact images */
homeApp.directive('header', function(){
    return {
        restrict: 'E',
        scope: {
            item: '=item'
        },
        templateUrl: '/assets/html/home/directives/header.html'
    };
});
/* Directive declaring banner, containing text, main buttons, and a impact image */
homeApp.directive('banner', function(){
    return {
        restrict: 'E',
        scope: {
            item: '=item'
        },
        templateUrl: '/assets/html/home/directives/banner.html'
    };
});
/* Directive declaring a type of Paragraph, divided in a Title and 2 columns */
homeApp.directive('parA', function(){
    return {
        restrict: 'E',
        scope: {
            item: '=item'
        },
        templateUrl: '/assets/html/home/directives/parA.html'
    };
});
/* Directive declaring a type of Content Display, showing title, text and image */
homeApp.directive('contentA', function(){
    return {
        restrict: 'E',
        scope: {
            item: '=item'
        },
        templateUrl: '/assets/html/home/directives/contenta.html'
    };
});
/* Directive declaring a type of Content Display, showing title, text and image */
homeApp.directive('contentB', function(){
    return {
        restrict: 'E',
        scope: {
            item: '=item'
        },
        templateUrl: '/assets/html/home/directives/contentb.html'
    };
});
/* Directive declaring a type of Contact Display, showing profile photos, links and text of contacts */
homeApp.directive('contactA', function(){
    return {
        restrict: 'E',
        scope: {
            item: '=item'
        },
        templateUrl: '/assets/html/home/directives/contacta.html'
    };
});
/* Directive declaring a type of Contact Display, showing profile photos, links and text of contacts */
homeApp.directive('contactB', function(){
    return {
        restrict: 'E',
        scope: {
            item: '=item'
        },
        templateUrl: '/assets/html/home/directives/contactb.html'
    };
});