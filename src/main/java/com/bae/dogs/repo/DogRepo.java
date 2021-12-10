package com.bae.dogs.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bae.dogs.domain.Dog;

public interface DogRepo extends JpaRepository<Dog, Integer> {

}
