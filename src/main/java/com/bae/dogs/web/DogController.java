package com.bae.dogs.web;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.bae.dogs.domain.Dog;
import com.bae.dogs.service.DogService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController

public class DogController {

	private DogService service;
	
	@Autowired
	public DogController(DogService service) {
		super();
		this.service = service;
	}
	
	@CrossOrigin
	@GetMapping("/getAll")
	public ResponseEntity<List<Dog>> getAll() {
		ResponseEntity<List<Dog>> response = new ResponseEntity<>(this.service.getAll(),HttpStatus.OK);	
		return response;
	}

	@GetMapping("/get/{id}")
	public Dog getById(@PathVariable Integer id) {
		return service.getById(id);
	}
	
	@PostMapping("/create")
	public ResponseEntity<Dog> create(@RequestBody Dog dog) {
		ResponseEntity<Dog> response = new ResponseEntity<>(service.create(dog), HttpStatus.CREATED);
		return response;
	}
	
	@PutMapping("/update/{id}")
	public ResponseEntity<Dog> update(@PathVariable Integer id, @RequestBody Dog dog) {
		ResponseEntity<Dog> response = new ResponseEntity<>(this.service.update(id, dog), HttpStatus.ACCEPTED);
		return response;
	}

	@DeleteMapping("/delete/{id}")
	public ResponseEntity<Dog> delete(@PathVariable Integer id) {
		this.service.delete(id);
		ResponseEntity<Dog> response = new ResponseEntity<>(HttpStatus.NO_CONTENT);
		return response;
	}
}