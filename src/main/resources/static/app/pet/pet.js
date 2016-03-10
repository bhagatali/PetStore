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
.controller('PetController',['$scope','$http','$location',function($scope,$http,$location){
    $http.get('http://localhost:8080/pet/').success(function(data){
        $scope.petStoreData = data;
    });    
    
    $scope.deletePet=function(petId){
    	var URL = "http://localhost:8080/pet/" + petId;
    	$http.delete(URL).success(function(){
    		$location.path('/pet/');
    	});
    };

    $scope.showAddPetForm = function(){
        clearFields();    
        $scope.addFormShowFlag = true;        
      };
      
    $scope.submitAddPetForm = function(){
    	var petObject={
    			petName:$scope.name || null,
    			status:$scope.status || null,
    			photoUrl:$scope.image || null,
    			price:$scope.price || null
    	}
    	console.log('petObject');
        var res = $http.post('http://localhost:8080/pet/',petObject);
    	
    	res.success(function(data, status, headers, config){
    		$scope.message = data;
		});
    	
		res.error(function(data, status, headers, config) {
			alert( "failure message: " + JSON.stringify({data: data}));
		});
//            var id = ref.key();
//            console.log('Added record with id: ' + id);
            
        clearFields();
            
        $scope.addFormShowFlag = false;        
    };
    
    var clearFields = function(){
        $scope.name = "";
        $scope.status = "";
        $scope.image = "";
        $scope.price = "";
    };      
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
}]);