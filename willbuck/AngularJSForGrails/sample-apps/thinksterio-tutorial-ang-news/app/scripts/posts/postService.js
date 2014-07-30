'use strict';

angular.module('posts.api', ['auth.user.api'])
	.factory('PostService', function($firebase, FIREBASE_URL, UserService) {
		var ref = new Firebase(FIREBASE_URL + 'posts');

		var posts = $firebase(ref);

		var Post = {
			all: posts,
			create: function(post) {
				if(UserService.signedIn()) {
					var user = UserService.getCurrent();

					post.owner = user.username;

					return posts.$add(post).then(function (ref) {
						var postId = ref.name();

						user.$child('posts').$child(postId).$set(postId);

						return postId;
					});
				}				
			},
			find: function(postId) {
				return posts.$child(postId);
			},
			delete: function(postId) {
				if(UserService.signedIn()) {
					var post = Post.find(postId);

					post.$on('loaded', function() {
						var user = UserService.findByUsername(post.owner);

						posts.$remove(postId).then(function() {
							user.$child('posts').$remove(postId);
						});
					});
				}				
			},
			addComment: function (postId, comment) {
				if(UserService.signedIn()) {
					var user = UserService.getCurrent();

					comment.username = user.username;
					comment.postId = postId;

					posts.$child(postId).$child('comments').$add(comment).then(function(ref) {
						user.$child('comments').$child(ref.name()).$set({id: ref.name(), postId: postId});
					});
				}
			},
			deleteComment: function(post, comment, commentId) {
				if(UserService.signedIn()) {
					var user = UserService.findByUsername(comment.username);

					post.$child('comments').$remove(commentId).then(function() {
						user.$child('comments').$remove(commentId);
					});
				}
			},
			upVote: function (postId) {
			  	if (UserService.signedIn()) {
			    	var user = UserService.getCurrent();
			    	var post = posts.$child(postId);
			 
				    post.$child('upvotes').$child(user.username).$set(user.username).then(function () {
				        user.$child('upvotes').$child(postId).$set(postId);
				        post.$child('downvotes').$remove(user.username);
				        user.$child('downvotes').$remove(postId);
				 
				        post.$child('score').$transaction(function (score) {
				          if (!score) {
				            return 1;
				          }
				 
				          return score + 1;
			        	});
		      		});
			  	}
			},
			downVote: function (postId) {
			  if (UserService.signedIn()) {
			    var user = UserService.getCurrent();
			    var post = posts.$child(postId);
			 
			    post.$child('downvotes').$child(user.username).$set(user.username).then(function () {
			        user.$child('downvotes').$child(postId).$set(postId);
			        post.$child('upvotes').$remove(user.username);
			        user.$child('upvotes').$remove(postId);
			 
			        post.$child('score').$transaction(function (score) {
			          if (score === undefined || score === null) {
			            return -1;
			          }
			 
			          return score - 1;
			        });
			      });
			  }
			},
			clearVote: function (postId, upVoted) {
			  if (UserService.signedIn()) {
			    var user = UserService.getCurrent();
			    var username = user.username;
			    var post = posts.$child(postId);
			 
			    post.$child('upvotes').$remove(username);
			    post.$child('downvotes').$remove(username);
			    user.$child('upvotes').$remove(postId);
			    user.$child('downvotes').$remove(postId);
			    post.$child('score').$transaction(function (score) {
			      if (upVoted) {
			        return score - 1;
			      } else {
			        return score + 1;
			      }
			    });
			  }
			},
			upVoted: function (post) {
			  if (UserService.signedIn() && post.upvotes) {
			    return post.upvotes.hasOwnProperty(UserService.getCurrent().username);
			  }
			},
			downVoted: function (post) {
			  if (UserService.signedIn() && post.downvotes) {
			    return post.downvotes.hasOwnProperty(UserService.getCurrent().username);
			  }
			}
 		};

		return Post;
	});