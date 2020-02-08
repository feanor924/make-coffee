package com.example.demo.CoffeeUtils;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import com.example.demo.ResourceError;
import com.example.demo.CoffeeEntity.MakeCoffeeModel;
import com.example.demo.MakeCoffeeDTO.MakeCoffeeDTO;

public class CoffeeCalculator {

	public static MakeCoffeeModel insertCoffeeForDbOrRemove(final List<MakeCoffeeModel> allCoffees,final MakeCoffeeModel coffeeReq,final Boolean isInsertOrRemove){
		double insertToDbWaterSize =0;
		double insertToDbMilkSize =0;
		double insertToDbCoffeeSize =0;
    	
        for (MakeCoffeeModel coffeesFromDb: allCoffees){
        	insertToDbWaterSize = insertToDbWaterSize +coffeesFromDb.getWaterSize();
        	insertToDbMilkSize = insertToDbMilkSize + coffeesFromDb.getMilkSize();
        	insertToDbCoffeeSize = insertToDbCoffeeSize + coffeesFromDb.getCoffeeSize();
        }
        if (Boolean.TRUE.equals(isInsertOrRemove)){
	    	insertToDbWaterSize = insertToDbWaterSize +coffeeReq.getWaterSize();
	        insertToDbMilkSize = insertToDbMilkSize + coffeeReq.getMilkSize();
	        insertToDbCoffeeSize = insertToDbCoffeeSize + coffeeReq.getCoffeeSize(); 
        }
        else{
        	insertToDbWaterSize = insertToDbWaterSize - coffeeReq.getWaterSize();
	        insertToDbMilkSize = insertToDbMilkSize - coffeeReq.getMilkSize();
	        insertToDbCoffeeSize = insertToDbCoffeeSize - coffeeReq.getCoffeeSize(); 
        }
        return new MakeCoffeeModel(insertToDbMilkSize,insertToDbCoffeeSize,insertToDbWaterSize);
	}
	
	public static void checkSizeAndWarn(final List<MakeCoffeeModel> allCoffees){
		
    	StringBuilder sb = new StringBuilder();
    	
        for (MakeCoffeeModel coffeesFromDb: allCoffees){
        	if (coffeesFromDb.getWaterSize() <= CoffeeUtils.EMPTY_LONG){
            	sb.append(CoffeeUtils.WATER + ": " + coffeesFromDb.getWaterSize() + CoffeeUtils.EMPTY_STRING);
            }
            if (coffeesFromDb.getMilkSize() <= CoffeeUtils.EMPTY_LONG){
            	sb.append(CoffeeUtils.MILK + " " + coffeesFromDb.getMilkSize()+  CoffeeUtils.EMPTY_STRING);
            }
            if (coffeesFromDb.getCoffeeSize() <= CoffeeUtils.EMPTY_LONG){
            	sb.append(CoffeeUtils.COFFEE + ": " + coffeesFromDb.getCoffeeSize()+ CoffeeUtils.EMPTY_STRING);
            }
            if (sb.length() > 0){
            	sb.deleteCharAt(sb.length()-1);
            	throw new ResourceError(sb.toString());
            }
        }
		
	}
	
	public static double coffeePriceCalc(final MakeCoffeeModel coffee){
			return (coffee.getWaterSize() * CoffeeUtils.WATER_PRICE) + (coffee.getMilkSize() * CoffeeUtils.MILK_PRICE)
					+ (coffee.getCoffeeSize() * CoffeeUtils.COFFEE_PRICE);
	}
	
	public static MakeCoffeeModel determineHowManyCoffee(final MakeCoffeeDTO makeCoffeeReq){
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
			coffee.setMilkSize(CoffeeUtils.LARGE_MILK_OZ);
			coffee.setWaterSize(CoffeeUtils.LITTLE_WATER_OZ);
		}	
		return coffee;
	}
	
	public static void checkCoffeeReq(final MakeCoffeeDTO makeCoffeeReq){
		if ( makeCoffeeReq == null ){
			throw new ResourceError(CoffeeUtils.SIZE_CANNOT_BE_LOWER_THAN_ZERO_OR_EMPTY);
		}
		else if (makeCoffeeReq.getCoffeeSize() == null || makeCoffeeReq.getMilkSize() == null){
			throw new ResourceError(CoffeeUtils.SIZE_CANNOT_BE_LOWER_THAN_ZERO_OR_EMPTY);
		}
		else if (makeCoffeeReq.getCoffeeSize().equals(CoffeeUtils.EMPTY_STRING)
				|| makeCoffeeReq.getMilkSize().equals(CoffeeUtils.EMPTY_STRING)){
			throw new ResourceError(CoffeeUtils.SIZE_CANNOT_BE_LOWER_THAN_ZERO_OR_EMPTY);
		}

	}
	
}
