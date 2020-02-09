'use strict'

angular.module('demo.services', []).factory('CoffeeService', ["$http", "CONSTANTS", function($http, CONSTANTS) {

    var service = {};


    service.getAllCoffees = function() {
        
        return $http.get(CONSTANTS.getAllCoffees);

    }

    service.makeCoffee = function(coffeeDto) {

        return $http.post(CONSTANTS.makeCoffee, coffeeDto);

    }

    service.insertCoffee = function(coffeeDto) {

        return $http.post(CONSTANTS.insertCoffee, coffeeDto);

    }

    return service;

}]);