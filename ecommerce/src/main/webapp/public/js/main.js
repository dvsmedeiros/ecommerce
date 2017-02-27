angular.module('ecommerce', ['ngRoute', 'ngResource'])
	.config(function($routeProvider, $locationProvider) {

		$locationProvider.html5Mode(true);

		$routeProvider.when('/products', {
			templateUrl: 'partials/products.html',
			controller: 'ProductsController'
		});

		$routeProvider.when('/products/new', {
			templateUrl: 'partials/product.html',
			controller: 'ProductController'
		});

		$routeProvider.when('/products/edit/:productId', {
			templateUrl: 'partials/product.html',
			controller: 'ProductController'
		});

		$routeProvider.otherwise({redirectTo: '/products'});
		
	});