package com.example.demo;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
/**
 * Created by canerbakar on 27.04.2018.
 */


public interface PersonRepository extends CrudRepository<MakeCoffeeModel, Long> {
    
}
