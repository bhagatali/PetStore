'use strict';

// Declare app level module which depends on views, and components
angular.module('myApp', [
  'ngRoute',
  'myApp.about',
  'myApp.contact',
  'myApp.pet'
]).
config(['$routeProvider', function($routeProvider) {
  $routeProvider
	  .when('/',{
	      templateUrl:'pet/pet.html',
	      controller:'PetController'
	  })
	  .otherwise({redirectTo: '/pet'});
}]);
