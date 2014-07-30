'use strict';
 
angular.module('auth.api', [])
	.factory('AuthService',
	  function ($firebaseSimpleLogin, FIREBASE_URL, $rootScope) {
	    var ref = new Firebase(FIREBASE_URL);
	 
	    var auth = $firebaseSimpleLogin(ref);
	 
	    var Auth = {
	      register: function (user) {
	        return auth.$createUser(user.email, user.password);
	      },
	      signedIn: function () {
	        return auth.user !== null;
	      },
	      login: function (user) {
		    return auth.$login('password', user);
		  },
	      logout: function () {
	        auth.$logout();
	      }
	    };
	 
	    $rootScope.signedIn = function () {
	      return Auth.signedIn();
	    };
	 
	    return Auth;
	  });