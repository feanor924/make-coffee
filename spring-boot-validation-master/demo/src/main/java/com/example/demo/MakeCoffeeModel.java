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
    @JsonView(View.Summary.class)
    private Long id;


    @NotNull
    @NotEmpty
    @Column(name = "coffee_size")
    @JsonView(View.Summary.class)
    private Long coffeeSize;
    
    
    @NotNull
    @NotEmpty
    @Column(name = "water_size")
    @JsonView(View.Summary.class)
    private Long waterSize;
    
    
    @NotNull
    @NotEmpty
    @Column(name = "milk_size")
    @JsonView(View.Summary.class)
    private Long milkSize;
       

    public MakeCoffeeModel(){}

    public Long getMilkSize() {
        return milkSize;
    }
    
    public Long getCoffeeSize() {
        return coffeeSize;
    }

    public Long getWaterSize() {
        return waterSize;
    }

    public MakeCoffeeModel(Long milkSize,Long coffeeSize, Long waterSize){
        this.milkSize = milkSize;
        this.coffeeSize = coffeeSize;
        this.waterSize = waterSize;
    }
    

	public void setCoffeeSize(Long coffeeSize) {
		this.coffeeSize = coffeeSize;
	}

	public void setWaterSize(Long waterSize) {
		this.waterSize = waterSize;
	}

	public void setMilkSize(Long milkSize) {
		this.milkSize = milkSize;
	}


    @Override
    public String toString() {
        return String.format(
                "Person[id=%d,milkSize='%d%n',coffeeSize='%d%n',waterSize='%d%n']",
                id,milkSize,coffeeSize,waterSize);
    }





}
