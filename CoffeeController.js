'use strict'

var module = angular.module('demo.controllers', []);

module.controller("CoffeeController", ["$scope", "CoffeeService","$http",

    function($scope, CoffeeService,$http) {

        $scope.makeCoffeeDTO = {

            coffeeSize: null,

            milkSize: null,

            waterSize: null

        };

        $scope.makeCoffeeModel = {

            coffeeSize: null,

            milkSize: null

        };


        CoffeeService.getAllCoffees().then(function(value) {

            $scope.allCoffees = value.data;

        }, function(reason) {

            window.alert("There is no coffee's, please some insert coffes");

        }, function(value) {

            console.log("Server is down");

        });


        $scope.makeCoffee = function() {

            CoffeeService.makeCoffee($scope.makeCoffeeModel).then(function(value_) {



                CoffeeService.getAllCoffees().then(function(value) {

                    $scope.allCoffees = value.data;

                }, function(reason) {

                    console.log("error occured");

                }, function(value) {

                    console.log("no callback");

                });


                $scope.makeCoffeeModel = {

                    coffeeSize: null,

                    milkSize: null,

                };

                window.alert("Coffe Price: " + value_.data);

            }, function(reason) {
                window.alert(reason.data.message);

            }, function(value) {

                console.log("no callback");

            });

        }

        $scope.insertCoffee = function() {


                CoffeeService.insertCoffee($scope.makeCoffeeDTO).then(function() {


                CoffeeService.getAllCoffees().then(function(value) {

                    $scope.allCoffees = value.data;

                }, function(reason) {

                    console.log("error occured");

                }, function(value) {

                    console.log("no callback");

                });


                $scope.makeCoffeeDTO = {

                    coffeeSize: null,

                    milkSize: null,

                };

            }, function(reason) {

                console.log("error occured");

            }, function(value) {

                console.log("no callback");

            });

        }



    }

]);