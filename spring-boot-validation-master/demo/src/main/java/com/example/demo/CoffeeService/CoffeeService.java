package com.example.demo.CoffeeService;

import java.util.Iterator;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.CoffeeEntity.MakeCoffeeModel;
import com.example.demo.CoffeeRepository.CoffeeRepository;

@Service
public class CoffeeService implements CoffeeServiceImpl{
	 @Autowired
	 CoffeeRepository coffeeRepository;

	@Override
	public Iterable<MakeCoffeeModel> findAll() {
		return coffeeRepository.findAll();
	}

	@Override
	public Optional<MakeCoffeeModel> findById(Long id) {
		return coffeeRepository.findById(id);
	}

	@Override
	public void deleteAll() {
		coffeeRepository.deleteAll();
	}

	@Override
	public MakeCoffeeModel save(MakeCoffeeModel model) {
		return coffeeRepository.save(model);
	}

	@Override
	public long count() {
		return coffeeRepository.count();
	}
	
}
