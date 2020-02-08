package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.*;

import java.util.*;

/**
 * Created by canerbakar on 27.04.2018.
 */
@RestController
public class PersonController {

    @Autowired
    PersonRepository personRepository;

    @RequestMapping(value = "/findAll", method = RequestMethod.GET)
    public List<MakeCoffeeModel> findAll_(){

        Iterator<MakeCoffeeModel> coffees = personRepository.findAll().iterator();

        List<MakeCoffeeModel> listCoffee = new ArrayList<>();
        coffees.forEachRemaining(listCoffee::add);

        if ( listCoffee.size() == 0)
            throw new ResourceError(CoffeeUtils.COFFEES_ARE_NOT_FOUND);

        return listCoffee;

    }
    @RequestMapping(value = "/findbyid/{id}", method = RequestMethod.GET)
    public Optional<MakeCoffeeModel> findById(@PathVariable(value = "id") Long id){

        Optional<MakeCoffeeModel> coffees = personRepository.findById(id);

        if ( !coffees.isPresent())
            throw new ResourceError(CoffeeUtils.COFFEES_ARE_NOT_FOUND);

        return coffees;
    }
   
    
    @RequestMapping(value = "/insertSomeCoffeeMilkWater", method = RequestMethod.POST)
    public MakeCoffeeModel insertSomeCoffeeMilkWater(@RequestBody MakeCoffeeModel coffeeReq){
    	Iterator<MakeCoffeeModel> allCoffees = personRepository.findAll().iterator();
        return personRepository.save(CoffeeCalculator.insertCoffeeForDbOrRemove(allCoffees, coffeeReq,Boolean.TRUE));    
    }
    

    @RequestMapping(value = "/makeCoffee", method = RequestMethod.POST)
    public double makeCoffee(@RequestBody MakeCoffee makeCoffeeReq){
    	
    	CoffeeCalculator.checkCoffeeReq(makeCoffeeReq);
		if (personRepository.count() != CoffeeUtils.EMPTY_INT){
	    	Iterator<MakeCoffeeModel> allCoffees = personRepository.findAll().iterator();   
	    	MakeCoffeeModel coffee = CoffeeCalculator.determineHowManyCoffee(makeCoffeeReq);
	    	CoffeeCalculator.insertCoffeeForDbOrRemove(allCoffees, coffee,Boolean.FALSE);
	    	
	        CoffeeCalculator.checkSizeAndWarn(coffee);
	        
	    }
		else{
			throw new ResourceError(CoffeeUtils.DB_IS_EMPTY);
		}
        

    }




}
