angular.module('AmbienteSeguro').controller('AmbienteseguroController', function($scope, $http) {

	$scope.token = {};
	
	$scope.authenticate = function(token) {		
		$http.get('http://localhost:8888/ambiente-seguro/security/authenticate/' + token.value)
		.success(function(retorno) {
			console.log(retorno);
			$scope.token = retorno;
		})
		.error(function(erro) {
			console.log(erro);
		});
	};
	
	$scope.genarateToken = function() {		
		$http.get('http://localhost:8888/ambiente-seguro/security/token')
		.success(function(retorno) {
			console.log(retorno);
			$scope.token = retorno;
		})
		.error(function(erro) {
			console.log(erro);
		});
	};
	
	
});