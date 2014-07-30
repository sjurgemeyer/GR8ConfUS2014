'use strict';

angular.module('auth.view', ['auth.api', 'auth.user.api'])
	.controller('AuthController', function($scope, $location, AuthService, UserService) {
		if(AuthService.signedIn()) {
			$location.path('/');
		}

		$scope.$on('$firebaseSimpleLogin:login', function () {
	    	$location.path('/');
	    });
	 
	    $scope.login = function () {
	      AuthService.login($scope.user).then(function () {
	        $location.path('/');
	      }, function (error) {
	        $scope.error = error.toString();
	      });
	    };

		$scope.register = function() {
			AuthService.register($scope.user).then(function(authUser) {
				UserService.create(authUser, $scope.user.username);
				console.log(authUser);
				$location.path('/');
			}, function (error) {
		    	$scope.error = error.toString();
		    });
		};
	});