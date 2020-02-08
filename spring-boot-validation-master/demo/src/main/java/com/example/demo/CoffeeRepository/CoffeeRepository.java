package com.example.demo.CoffeeRepository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.example.demo.CoffeeEntity.MakeCoffeeModel;
/**
 * Created by canerbakar on 27.04.2018.
 */


public interface CoffeeRepository extends CrudRepository<MakeCoffeeModel, Long> {
    
}
