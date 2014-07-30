'use strict';

angular.module('auth.profile.view', ['auth.user.api', 'posts.api'])
	.controller('ProfileController', function($scope, $routeParams, PostService, UserService) {
		$scope.user = UserService.findByUsername($routeParams.username);

		$scope.commentedPosts = {};

		function populatePosts() {
			$scope.posts = {};

			angular.forEach($scope.user.posts, function(postId) {
				$scope.posts[postId] = PostService.find(postId);
			});
		}

		function populateComments() {
			$scope.comments = {};

			angular.forEach($scope.user.comments, function(comment) {
				var post = PostService.find(comment.postId);

				post.$on('loaded', function() {
					$scope.comments[comment.id] = post.$child('comments').$child(comment.id);

					$scope.commentedPosts[comment.postId] = post;
				});
			});
		}

		$scope.user.$on('loaded', function() {
			populatePosts();
			populateComments();
		});

	});