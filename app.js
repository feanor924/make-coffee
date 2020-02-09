'use strict'

var demoApp = angular.module('demo', ['ui.bootstrap', 'demo.controllers','demo.services']);

demoApp.constant("CONSTANTS", {

    makeCoffee: "http://localhost:8080/makeCoffee",
    insertCoffee: "http://localhost:8080/insertSomeCoffeeMilkWater",
    getAllCoffees:"http://localhost:8080/findAll"

});