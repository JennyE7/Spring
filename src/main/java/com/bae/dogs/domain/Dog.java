package com.bae.dogs.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Dog {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@Column
	private String breed;
	
	@Column
	private double height;
	
	@Column
	private String colour;
	
	public String getBreed() {
		return breed;
	}
	
	public void setBreed(String breed) {
		this.breed = breed;
	}
	public double getHeight() {
		return height;
	}
	public void setHeight(double height) {
		this.height = height;
	}
	public String getColour() {
		return colour;
	}
	public void setColour(String colour) {
		this.colour = colour;
	}
	
	public void setId(Integer id) {
		this.id = id;
	}
	
	public Integer getId() {
		return id;
	}

	@Override
	public String toString() {
		return "Dog [id=" + id + ", breed=" + breed + ", height=" + height + ", colour=" + colour + "]";
	}

	public Dog(Integer id, String breed, double height, String colour) {
		super();
		this.id = id;
		this.breed = breed;
		this.height = height;
		this.colour = colour;
	}

	public Dog() {
		super();
	}
	
	
	
	
}
