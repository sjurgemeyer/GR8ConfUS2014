/* global app:true */ 
'use strict';

/**
 * @ngdoc overview
 * @name angNewsApp
 * @description
 * # angNewsApp
 *
 * Main module of the application.
 */
var app = angular.module('angNewsApp', [
  'auth',
  'posts',
  'common',
  'ngAnimate',
  'ngCookies',
  'ngResource',
  'ngRoute',
  'ngSanitize',
  'ngTouch',
  'firebase'
]);
app.config(function ($routeProvider) {
    $routeProvider
      .when('/', {
        templateUrl: 'scripts/posts/postView.html',
        controller: 'PostController'
      })
      .when('/post/:postId', {
        templateUrl: 'scripts/posts/showPost.html',
        controller: 'PostDetailController'
      })
      .when('/users/:username', {
        templateUrl: 'scripts/auth/profileView.html',
        controller: 'ProfileController'
      })
      .when('/register', {
        templateUrl: 'scripts/auth/register.html',
        controller: 'AuthController'
      })
      .when('/login', {
        templateUrl: 'scripts/auth/login.html',
        controller: 'AuthController'
      })
      .otherwise({
        redirectTo: '/'
      });
  });
app.constant('FIREBASE_URL', 'https://torid-fire-2780.firebaseio.com/');