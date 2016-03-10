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
        console.log('Before 1'+ $scope.addFormShowFlag);
        $scope.addFormShowFlag = true;      
        console.log('Before 2' + $scope.addFormShowFlag);  
      };

    $scope.cancelAdd = function(){
        $scope.addFormShowFlag = false; 
    };      
      
    $scope.submitAddPetForm = function(){
    	//Convert String to Array
    	var tags=$scope.tags.split(',');
    	//New Array to hold JSON objects
    	var tagArrayJSON = new Array();

    	//Looping through the length of the array 
    	for(var i=0;i<tags.length;i++){
    		//Create a new JSON object
    		var tagJson = new Object();
    		//Add a tagname:tagValue 
        	tagJson.tagName = tags[i];
        	//Push the JSON object into JSON array
        	tagArrayJSON.push(tagJson);
    	}

    	var categoryArrayJSON = new Array();

    	for(var j=0;j<$scope.categories.length;j++){
    		var categoryJson = new Object();
    		categoryJson.categoryName = $scope.categories[j]; 
    		categoryArrayJSON.push(categoryJson);
    	}
    	
    	var petObject={
    			petName:$scope.name || null,
    			status:$scope.status || null,
    			photoUrl:$scope.image || null,
    			price:$scope.price || null,
    			tags:tagArrayJSON || null,
    			categories:categoryArrayJSON || null
    	}
    	console.log(petObject);
        var res = $http.post('http://localhost:8080/pet/',petObject);
    	
    	res.success(function(data, status, headers, config){
    		$scope.message = data;
    		console.log($scope.message);
    		$location.path('/pet/');
    		
		});
    	
		res.error(function(data, status, headers, config) {
			alert( "failure message: " + JSON.stringify({data: data}));
		});
        clearFields();
        $scope.addFormShowFlag = false;        
    };
    
    var clearFields = function(){
        $scope.name = "";
        $scope.status = "";
        $scope.image = "";
        $scope.price = "";
        $scope.categories = "";
        $scope.tags = "";
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