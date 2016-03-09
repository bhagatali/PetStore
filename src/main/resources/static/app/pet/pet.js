'use strict';

angular.module('myApp.pet',['ngRoute'])
.config(['$routeProvider',function($routeProvider){
    $routeProvider
        .when('/pet', {
            templateUrl:'pet/pet.html',
            controller:'PetController'
    }) 
        .when('/pet/:petId', {
            templateUrl:'pet/petDetails.html',
            controller:'PetDetailsController'
    })
    
    
}])
.controller('PetController',['$scope','$filter','$http','$location',function($scope,$filter,$http,$location){
    $http.get('http://localhost:8080/pet/').success(function(data){
        $scope.petStoreData = data;
    });    
    
    $scope.deletePet=function(petId){
    	var URL = "http://localhost:8080/pet/" + petId;
    	$http.delete(URL).success(function(){
    		$location.path('/pet/');
    	});
    }
}])
.controller('PetDetailsController',['$scope',
                                    '$http',
                                    '$routeParams',
                                    '$filter',
                                    function($scope,$http,$routeParams,$filter){
    var petId = $routeParams.petId;
    var URL = "http://localhost:8080/pet/" + petId;
    $http.get(URL).success(function(data){    	
        //$scope.pet = $filter('filter')(data,{id:petId})[0];
    	$scope.pet = data;
    	$scope.mainImage = $scope.pet.photoUrl[0];
        $scope.setImage = function(image){
            $scope.mainImage = image;
        }
    })
                                             
}])