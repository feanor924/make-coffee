package com.example.demo.CoffeeService;

import java.util.Iterator;
import java.util.Optional;

import com.example.demo.CoffeeEntity.MakeCoffeeModel;

public interface CoffeeServiceImpl {

	Iterable<MakeCoffeeModel> findAll();
	Optional<MakeCoffeeModel> findById(Long id);
	void deleteAll();
	MakeCoffeeModel save(MakeCoffeeModel model);
	long count();
	
}
