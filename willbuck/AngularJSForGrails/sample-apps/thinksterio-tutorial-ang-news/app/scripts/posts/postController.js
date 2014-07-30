'use strict';

angular.module('posts.view', ['posts.api'])
	.controller('PostController', function($scope, $location, PostService) {
		if ($location.path() === '/') {
			$scope.posts = PostService.all;
		}
		$scope.post = {url: 'http://gr8conf.us', title: 'Grrrr8!'};
		
	    $scope.deletePost = function (postId) {
		  	PostService.delete(postId);
		};

		$scope.upVotePost = function (postId, upVoted) {
		  if (upVoted) {
		    PostService.clearVote(postId, upVoted);
		  } else {
		    PostService.upVote(postId);
		  }
		};
		 
		$scope.downVotePost = function (postId, downVoted) {
		  if (downVoted) {
		    PostService.clearVote(postId, !downVoted);
		  } else {
		    PostService.downVote(postId);
		  }
		};
		 
		$scope.upVoted = function (post) {
		  return PostService.upVoted(post);
		};
		 
		$scope.downVoted = function (post) {
		  return PostService.downVoted(post);
		};
	});