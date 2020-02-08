package com.example.demo;

import com.fasterxml.jackson.annotation.JsonView;

import javax.persistence.*;
import javax.validation.constraints.*;

/**
 * Created by canerbakar on 27.04.2018.
 */

@Entity
@Table(name = "MakeCoffee")
public class MakeCoffeeModel {

    @Id @GeneratedValue
    private Long id;


    @NotNull
    @Column(name = "coffee_size")
    private double coffeeSize;
    
    
    @NotNull
    @Column(name = "water_size")
    private double waterSize;
    
    
    @NotNull
    @Column(name = "milk_size")
    private double milkSize;
       

    public MakeCoffeeModel(){}

    public double getMilkSize() {
        return milkSize;
    }
    
    public double getCoffeeSize() {
        return coffeeSize;
    }

    public double getWaterSize() {
        return waterSize;
    }

    public MakeCoffeeModel(double milkSize,double coffeeSize, double waterSize){
        this.milkSize = milkSize;
        this.coffeeSize = coffeeSize;
        this.waterSize = waterSize;
    }
    

	public void setCoffeeSize(double coffeeSize) {
		this.coffeeSize = coffeeSize;
	}

	public void setWaterSize(double waterSize) {
		this.waterSize = waterSize;
	}

	public void setMilkSize(double milkSize) {
		this.milkSize = milkSize;
	}


    @Override
    public String toString() {
        return String.format(
                "Person[id=%d,milkSize='%d%n',coffeeSize='%d%n',waterSize='%d%n']",
                id,milkSize,coffeeSize,waterSize);
    }





}
