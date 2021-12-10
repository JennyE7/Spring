package com.bae.dogs.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bae.dogs.domain.Dog;
import com.bae.dogs.repo.DogRepo;

@Service
public class DogService {
	
	private DogRepo repo;
	
	
	@Autowired
	public DogService(DogRepo repo) {
		super();
		this.repo = repo;
	}

	public List<Dog> getAll() {
		return this.repo.findAll();
	}
	
	public Dog getById(Integer id) {
		return this.repo.findById(id).get();
	}
	
	public Dog create(Dog dog) {
		return this.repo.save(dog);
	}
	
	public Dog update(Integer id, Dog dog) {
		Dog existing = this.repo.findById(id).get();
		
		existing.setBreed(dog.getBreed());
		existing.setColour(dog.getColour());
		existing.setHeight(dog.getHeight());
		
		Dog update = this.repo.save(existing);
		
		return update;
	}

	public void delete(Integer id) {
		this.repo.deleteById(id);
	}

}