// Controller that works on all Homepage
feedApp.controller('FeedController', function($scope, jsonService) {
    jsonService.get('NavbarDefault.json').success(function(data){
        $scope.navbarText = data;
    }); // Buttons and logo
    jsonService.get('FootbarDefault.json').success(function(data){
        $scope.footbarText = data;
    }); // Buttons and credits
});

// Controller that works only on Initial section
feedApp.controller('AdsController', function($scope, $http, jsonService) {
    jsonService.get('title/AdsDefault.json').success(function(data){
        $scope.labels = data;
    }); // Title
    $http.get('/api/ads').success(function(response){
        $scope.ads = response.ads.data;
    });
    /*$scope.ads = [
        {
            title: 'Red Velvet Cake',
            chef: 'Panificadora Severo',
            imglink: 'http://foodnetwork.sndimg.com/content/dam/images/food/fullset/2004/1/23/1/ss1d26_red_velvet_cake.jpg.rend.sniipadlarge.jpeg',
            price: '60,00'
        },
        {
            title: 'Banana Split Cheesecake',
            chef: 'Doces Maria Amor',
            imglink: 'http://www.shugarysweets.com/wp-content/uploads/2014/04/banana-split-cheesecake-1.jpg',
            price: '40,00'
        },
        {
            title: 'Caramel Apple Cheesecake',
            chef: 'Panificadora Severo',
            imglink: 'http://foodnetwork.sndimg.com/content/dam/images/food/fullset/2007/4/12/0/bt0206_applecheesecake.jpg.rend.sniipadlarge.jpeg',
            price: '20,00'
        },
        {
            title: 'Torta Alemã',
            chef: 'Panificadora Severo',
            imglink: 'http://receitatodahora.com.br/wp-content/uploads/2014/09/Torta-alem%C3%A3.jpg',
            price: '50,00'
        },
        {
            title: 'Red Velvet Cake',
            chef: 'Bolos Doce Paixão',
            imglink: 'https://s3.amazonaws.com/twduncan/recipe/1778/hero-cherry-red-velvet-cake.jpg',
            price: '30,00'
        },
        {
            title: 'Bolo de Rapadura',
            chef: 'Bolos Doce Paixão',
            imglink: 'http://mdemulher.abril.com.br/sites/mdemulher/files/styles/retangular_horizontal_2/public/migracao/receita-bolo-rapadura.jpg?itok=6C_EiXVJ',
            price: '40,00'
        }

    ];*/
});
