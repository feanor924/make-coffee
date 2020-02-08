package com.example.demo;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class CoffeeCalculator {

	public static MakeCoffeeModel insertCoffeeForDb(Iterator<MakeCoffeeModel> allCoffees,MakeCoffeeModel model,boolean isForMakeOrInsert){
		Long insertToDbWaterSize =0L;
    	Long insertToDbMilkSize =0L;
    	Long insertToDbCoffeeSize =0L;
    	
    
    	List<MakeCoffeeModel> listCoffee = new ArrayList<>();
    	allCoffees.forEachRemaining(listCoffee::add);
    	
        for (MakeCoffeeModel coffeesFromDb: listCoffee){
        	insertToDbWaterSize+=coffeesFromDb.getWaterSize();
        	insertToDbMilkSize+=coffeesFromDb.getMilkSize();
        	insertToDbCoffeeSize+=coffeesFromDb.getCoffeeSize();
        }
        if (Boolean.TRUE.equals(isForMakeOrInsert)){
        	insertToDbWaterSize += model.getWaterSize();
	        insertToDbMilkSize += model.getMilkSize();
	        insertToDbCoffeeSize += model.getCoffeeSize();
        }
        else{
        	insertToDbWaterSize -= model.getWaterSize();
	        insertToDbMilkSize -= model.getMilkSize();
	        insertToDbCoffeeSize -= model.getCoffeeSize();
        }  
        
        return new MakeCoffeeModel(insertToDbMilkSize,insertToDbCoffeeSize,insertToDbWaterSize);
	}
	
	public static void checkSizeAndWarn(MakeCoffeeModel coffee){
		StringBuilder sb = new StringBuilder();
        if (CoffeeUtils.EMPTY_LONG.equals(coffee.getWaterSize())){
        	sb.append(CoffeeUtils.WATER + CoffeeUtils.EMPTY_STRING);
        }
        if (CoffeeUtils.EMPTY_LONG.equals(coffee.getMilkSize())){
        	sb.append(CoffeeUtils.MILK + CoffeeUtils.EMPTY_STRING);
        }
        if (CoffeeUtils.EMPTY_LONG.equals(coffee.getCoffeeSize())){
        	sb.append(CoffeeUtils.COFFEE + CoffeeUtils.EMPTY_STRING);
        }
        if (sb.length() > 0){
        	sb.deleteCharAt(sb.length()-1);
        	throw new ResourceError(sb.toString());
        }
	}
	
	public static double coffeePriceCalc(MakeCoffeeModel coffee){
		if (coffee.getCoffeeSize() < CoffeeUtils.EMPTY_LONG || 
				coffee.getMilkSize() < CoffeeUtils.EMPTY_LONG ||
				coffee.getWaterSize() < CoffeeUtils.EMPTY_LONG){
			throw new ResourceError(CoffeeUtils.SIZE_CANNOT_BE_LOWER_THAN_ZERO);
		}
		else{
			return (coffee.getWaterSize() * CoffeeUtils.WATER_PRICE) + (coffee.getMilkSize() * CoffeeUtils.MILK_PRICE)
					+ (coffee.getCoffeeSize() * CoffeeUtils.COFFEE_PRICE);
		}
	}
	
}
