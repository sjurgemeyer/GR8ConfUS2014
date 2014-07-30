'use strict';

angular.module('common.nav.view', ['posts.api', 'auth.api'])
	.controller('NavController', function($scope, $location, PostService, AuthService) {
		$scope.post = {url: 'http://', title: ''};
 
	    $scope.submitPost = function () {
	      PostService.create($scope.post).then(function (postId) {
	        $scope.post = {url: 'http://', title: ''};
	        $location.path('/posts/' + postId);    
	      });
	    };

	    $scope.logout = function() {
	    	AuthService.logout();
	    };
	});