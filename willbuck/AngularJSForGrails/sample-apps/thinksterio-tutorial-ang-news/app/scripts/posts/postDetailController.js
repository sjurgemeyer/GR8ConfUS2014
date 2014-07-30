'use strict';

angular.module('posts.detail.view', [])
	.controller('PostDetailController', function($scope, $routeParams, PostService) {
		$scope.post = PostService.find($routeParams.postId);

		$scope.addComment = function() {
			PostService.addComment($routeParams.postId, $scope.comment);
			$scope.comment = '';
		};

		$scope.removeComment = function(comment, commentId) {
			PostService.deleteComment($scope.post, comment, commentId);
		};

		$scope.upVotePost = function (upVoted) {
		  if (upVoted) {
		    PostService.clearVote($routeParams.postId, true);
		  } else {
		    PostService.upVote($routeParams.postId);
		  }
		};
		 
		$scope.downVotePost = function (downVoted) {
		  if (downVoted) {
		    PostService.clearVote($routeParams.postId, false);
		  } else {
		    PostService.downVote($routeParams.postId);
		  }
		};
		 
		$scope.upVoted = function () {
		  return PostService.upVoted($scope.post);
		};
		 
		$scope.downVoted = function () {
		  return PostService.downVoted($scope.post);
		};
	});