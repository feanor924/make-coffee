package com.example.demo;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class CoffeeCalculator {

	public static MakeCoffeeModel insertCoffeeForDbOrRemove(Iterator<MakeCoffeeModel> allCoffees,MakeCoffeeModel coffeeReq,Boolean isInsertOrRemove){
		double insertToDbWaterSize =0;
		double insertToDbMilkSize =0;
		double insertToDbCoffeeSize =0;
    	
    	List<MakeCoffeeModel> listCoffee = new ArrayList<>();
    	allCoffees.forEachRemaining(listCoffee::add);
    	
        for (MakeCoffeeModel coffeesFromDb: listCoffee){
        	insertToDbWaterSize+=coffeesFromDb.getWaterSize();
        	insertToDbMilkSize+=coffeesFromDb.getMilkSize();
        	insertToDbCoffeeSize+=coffeesFromDb.getCoffeeSize();
        }
        if (Boolean.TRUE.equals(isInsertOrRemove)){
	    	insertToDbWaterSize += coffeeReq.getWaterSize();
	        insertToDbMilkSize += coffeeReq.getMilkSize();
	        insertToDbCoffeeSize += coffeeReq.getCoffeeSize(); 
        }
        else{
        	insertToDbWaterSize -= coffeeReq.getWaterSize();
	        insertToDbMilkSize -= coffeeReq.getMilkSize();
	        insertToDbCoffeeSize -= coffeeReq.getCoffeeSize(); 
        }
       
        return new MakeCoffeeModel(insertToDbMilkSize,insertToDbCoffeeSize,insertToDbWaterSize);
	}
	
	public static void checkSizeAndWarn(MakeCoffeeModel coffee){
		StringBuilder sb = new StringBuilder();
        if (coffee.getWaterSize() <= CoffeeUtils.EMPTY_LONG){
        	sb.append(CoffeeUtils.WATER + CoffeeUtils.EMPTY_STRING);
        }
        if (coffee.getMilkSize() <= CoffeeUtils.EMPTY_LONG){
        	sb.append(CoffeeUtils.MILK + CoffeeUtils.EMPTY_STRING);
        }
        if (coffee.getCoffeeSize() <= CoffeeUtils.EMPTY_LONG){
        	sb.append(CoffeeUtils.COFFEE + CoffeeUtils.EMPTY_STRING);
        }
        if (sb.length() > 0){
        	sb.deleteCharAt(sb.length()-1);
        	throw new ResourceError(sb.toString());
        }
	}
	
	
	public static MakeCoffeeModel determineHowManyCoffee(MakeCoffee makeCoffeeReq){
		MakeCoffeeModel coffee = new MakeCoffeeModel();
		if (makeCoffeeReq.getCoffeeSize().toLowerCase().equals(CoffeeUtils.LITTLE_LOWER_CASE)){
			coffee.setCoffeeSize(CoffeeUtils.LITTLE_COFFEE_OZ);
		}
		else if (makeCoffeeReq.getCoffeeSize().toLowerCase().equals(CoffeeUtils.MID_LOWER_CASE)){
			coffee.setCoffeeSize(CoffeeUtils.MID_COFFEE_OZ);
		}
		else{
			coffee.setCoffeeSize(CoffeeUtils.LARGE_COFFEE_OZ);
		}
		if (makeCoffeeReq.getMilkSize().toLowerCase().equals(CoffeeUtils.LITTLE_LOWER_CASE)){
			coffee.setCoffeeSize(CoffeeUtils.LITTLE_MILK_OZ);
			coffee.setWaterSize(CoffeeUtils.LARGE_WATER_OZ);
		}
		else if (makeCoffeeReq.getMilkSize().toLowerCase().equals(CoffeeUtils.MID_LOWER_CASE)){
			coffee.setCoffeeSize(CoffeeUtils.MID_MILK_OZ);
			coffee.setWaterSize(CoffeeUtils.MID_WATER_OZ);
		}
		else{
			coffee.setCoffeeSize(CoffeeUtils.LARGE_MILK_OZ);
			coffee.setWaterSize(CoffeeUtils.LITTLE_WATER_OZ);
		}	
		return coffee;
	}
	
	public static void checkCoffeeReq(MakeCoffee makeCoffeeReq){
		if ( makeCoffeeReq == null ){
			throw new ResourceError(CoffeeUtils.SIZE_CANNOT_BE_LOWER_THAN_ZERO_OR_EMPTY);
		}
		else if (makeCoffeeReq.getCoffeeSize() == null || makeCoffeeReq.getWaterSize() == null || makeCoffeeReq.getMilkSize() == null){
			throw new ResourceError(CoffeeUtils.SIZE_CANNOT_BE_LOWER_THAN_ZERO_OR_EMPTY);
		}
		else if (makeCoffeeReq.getCoffeeSize().equals(CoffeeUtils.EMPTY_STRING)
				|| makeCoffeeReq.getMilkSize().equals(CoffeeUtils.EMPTY_STRING)
				|| makeCoffeeReq.getWaterSize().equals(CoffeeUtils.EMPTY_STRING)){
			throw new ResourceError(CoffeeUtils.SIZE_CANNOT_BE_LOWER_THAN_ZERO_OR_EMPTY);
		}

	}
	
}
