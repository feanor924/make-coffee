package com.example.demo;


import com.fasterxml.jackson.annotation.JsonView;
import org.aspectj.weaver.Iterators;
import org.hibernate.exception.ConstraintViolationException;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.boot.autoconfigure.security.oauth2.client.OAuth2ClientProperties;
import org.springframework.validation.BeanPropertyBindingResult;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;


import javax.validation.Valid;
import java.util.*;
import java.util.logging.Logger;

/**
 * Created by canerbakar on 27.04.2018.
 */
@RestController
public class PersonController {

    @Autowired
    PersonRepository personRepository;

    @JsonView(View.Summary.class)
    @RequestMapping(value = "/findAll", method = RequestMethod.GET)
    public List<MakeCoffeeModel> findAll_(){

        Iterator<MakeCoffeeModel> coffees = personRepository.findAll().iterator();

        List<MakeCoffeeModel> listCoffee = new ArrayList<>();
        coffees.forEachRemaining(listCoffee::add);

        if ( listCoffee.size() == 0)
            throw new ResourceError("Coffees are not found");

        return listCoffee;

    }

    @JsonView(View.Summary.class)
    @RequestMapping(value = "/findbyid/{id}", method = RequestMethod.GET)
    public Optional<MakeCoffeeModel> findById(@PathVariable(value = "id") Long id){

        Optional<MakeCoffeeModel> coffees = personRepository.findById(id);

        if ( !coffees.isPresent())
            throw new ResourceError("Coffees are not found");

        return coffees;
    }

    @JsonView(View.Summary.class)
    @RequestMapping(value = "/findbyname/{coffee_name}",method = RequestMethod.GET)
    public List<MakeCoffeeModel> fetchDataByName(@PathVariable(value = "coffee_name") String coffeeName){

        List<MakeCoffeeModel> coffees = personRepository.findByCoffeeName(coffeeName);

        if ( coffees.size() == 0 ){
            throw new ResourceError("Coffees are not found");
        }

        return coffees;

    }

    @JsonView(View.Summary.class)
    @RequestMapping(value = "/findbyemail/{coffee_size}",method = RequestMethod.GET)
    public List<MakeCoffeeModel> fetchDataByCoffeeSize(@PathVariable(value = "coffee_size") Long coffeeSize){

        List<MakeCoffeeModel> coffeeSizeList = personRepository.findByCoffeeSize(coffeeSize);

        if ( coffeeSizeList.size() == 0 ){
            throw new ResourceError("Coffees are not found");
        }

        return coffeeSizeList;
    }
    
    
    @JsonView(View.Summary.class)
    @RequestMapping(value = "/findbyemail/{water_size}",method = RequestMethod.GET)
    public List<MakeCoffeeModel> fetchDataByWaterSize(@PathVariable(value = "water_size") Long waterSize){

        List<MakeCoffeeModel> waterSizeList = personRepository.findByCoffeeSize(waterSize);

        if ( waterSizeList.size() == 0 ){
            throw new ResourceError("Coffees are not found");
        }

        return waterSizeList;
    }
    
    
    @JsonView(View.Summary.class)
    @RequestMapping(value = "/findbyemail/{milk_size}",method = RequestMethod.GET)
    public List<MakeCoffeeModel> fetchDataByMilkSize(@PathVariable(value = "milk_size") Long milkSize){

        List<MakeCoffeeModel> coffeeSizeList = personRepository.findByCoffeeSize(milkSize);

        if ( coffeeSizeList.size() == 0 ){
            throw new ResourceError("Coffees are not found");
        }

        return coffeeSizeList;
    }
    
    @RequestMapping(value = "/insertCoffeeWaterMilk", method = RequestMethod.POST)
    public MakeCoffeeModel process(@Valid @RequestBody MakeCoffeeModel model, BindingResult result){
    
    	Long insertToDbWaterSize =0L;
    	Long insertToDbMilkSize =0L;
    	Long insertToDbCoffeeSize =0L;
    	
    	Iterator<MakeCoffeeModel> allCoffees = personRepository.findAll().iterator();
    	List<MakeCoffeeModel> listCoffee = new ArrayList<>();
    	allCoffees.forEachRemaining(listCoffee::add);
    	
        for (MakeCoffeeModel coffeesFromDb: listCoffee){
        	insertToDbWaterSize+=coffeesFromDb.getWaterSize();
        	insertToDbMilkSize+=coffeesFromDb.getMilkSize();
        	insertToDbCoffeeSize+=coffeesFromDb.getCoffeeSize();
        }
        insertToDbWaterSize += model.getWaterSize();
        insertToDbMilkSize += model.getMilkSize();
        insertToDbCoffeeSize += model.getCoffeeSize();
        
        MakeCoffeeModel newCoffee = new MakeCoffeeModel(insertToDbMilkSize,insertToDbCoffeeSize,insertToDbWaterSize);
        
        
    }
    

    @RequestMapping(value = "/makeCoffee", method = RequestMethod.POST)
    public MakeCoffeeModel process(@Valid @RequestBody MakeCoffeeModel model, BindingResult result){


        List<FieldError> lists = result.getFieldErrors();

        StringBuilder sb = new StringBuilder(5);

        if ( lists.size() != 0) {
            for (FieldError f1 : lists) {
                sb.append(f1.getDefaultMessage());
            }
            throw new ResourceError(sb.toString());
        }
        else{
        	
        	
        }

    }




}
