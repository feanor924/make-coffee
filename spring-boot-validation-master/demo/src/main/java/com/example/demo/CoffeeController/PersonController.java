package com.example.demo.CoffeeController;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.*;

import com.example.demo.ResourceError;
import com.example.demo.CoffeeEntity.MakeCoffeeModel;
import com.example.demo.CoffeeService.CoffeeService;
import com.example.demo.CoffeeUtils.CoffeeCalculator;
import com.example.demo.CoffeeUtils.CoffeeUtils;
import com.example.demo.MakeCoffeeDTO.MakeCoffeeDTO;

import java.util.*;

/**
 * Created by canerbakar on 27.04.2018.
 */
@CrossOrigin
@RestController
public class PersonController {

    @Autowired
    CoffeeService coffeeService;

    @RequestMapping(value = "/findAll", method = RequestMethod.GET)
    public List<MakeCoffeeModel> findAll_(){

        Iterator<MakeCoffeeModel> coffees = coffeeService.findAll().iterator();

        List<MakeCoffeeModel> listCoffee = new ArrayList<>();
        coffees.forEachRemaining(listCoffee::add);

        if ( listCoffee.size() == 0)
            throw new ResourceError(CoffeeUtils.COFFEES_ARE_NOT_FOUND);

        return listCoffee;

    }
    @RequestMapping(value = "/findbyid/{id}", method = RequestMethod.GET)
    public Optional<MakeCoffeeModel> findById(@PathVariable(value = "id") Long id){

        Optional<MakeCoffeeModel> coffees = coffeeService.findById(id);

        if ( !coffees.isPresent())
            throw new ResourceError(CoffeeUtils.COFFEES_ARE_NOT_FOUND);

        return coffees;
    }
   
    @RequestMapping(value = "/insertSomeCoffeeMilkWater", method = RequestMethod.POST)
    public MakeCoffeeModel insertSomeCoffeeMilkWater(@RequestBody MakeCoffeeModel coffeeReq){
    	Iterator<MakeCoffeeModel> allCoffees = coffeeService.findAll().iterator();
    	List<MakeCoffeeModel> listCoffee = new ArrayList<>();
    	allCoffees.forEachRemaining(listCoffee::add);
    	MakeCoffeeModel howManyCoffeWillBeInserted = CoffeeCalculator.insertCoffeeForDbOrRemove(listCoffee, coffeeReq,Boolean.TRUE);
    	coffeeService.deleteAll();
    	return coffeeService.save(howManyCoffeWillBeInserted);    
    }
    

    @RequestMapping(value = "/makeCoffee", method = RequestMethod.POST)
    public double makeCoffee(@RequestBody MakeCoffeeDTO makeCoffeeReq){
    	
		if (coffeeService.count() != CoffeeUtils.EMPTY_INT){
	    	CoffeeCalculator.checkCoffeeReq(makeCoffeeReq);
	    	Iterator<MakeCoffeeModel> allCoffees = coffeeService.findAll().iterator();
	    	List<MakeCoffeeModel> listCoffee = new ArrayList<>();
	    	allCoffees.forEachRemaining(listCoffee::add);
	    	
			CoffeeCalculator.checkSizeAndWarn(listCoffee);
	    	
			MakeCoffeeModel coffee = CoffeeCalculator.determineHowManyCoffee(makeCoffeeReq);
	    	
			MakeCoffeeModel howManyCoffeRemain = CoffeeCalculator.insertCoffeeForDbOrRemove(listCoffee, coffee,Boolean.FALSE);
	    	
			List<MakeCoffeeModel> howManyCoffeRemainList = new ArrayList<>();
			howManyCoffeRemainList.add(howManyCoffeRemain);
			CoffeeCalculator.checkSizeAndWarn(howManyCoffeRemainList);
			
			coffeeService.deleteAll();
			coffeeService.save(howManyCoffeRemain);
	    	
	    	return CoffeeCalculator.coffeePriceCalc(coffee);
	        
	    }
		else{
			throw new ResourceError(CoffeeUtils.DB_IS_EMPTY);
		}
        

    }




}
