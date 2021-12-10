package com.bae.dogs.web;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.http.MediaType;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.Sql.ExecutionPhase;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.ResultMatcher;

import com.bae.dogs.domain.Dog;
import com.fasterxml.jackson.databind.ObjectMapper;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
@Sql(scripts = { "classpath:dog-schema.sql",
				"classpath:dog-data.sql" }, executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
public class DogControllerIntegrationTest {
	
	@Autowired
	private MockMvc mvc;
	
	@Autowired
	private ObjectMapper mapper;
	
	@Test
	void testCreate() throws Exception {
		
		Dog dog = new Dog(null, "Poodle", 40, "Brown");
		String dogAsJson = this.mapper.writeValueAsString(dog);
		
		RequestBuilder req = post("/create").contentType(MediaType.APPLICATION_JSON).content(dogAsJson);
		
		Dog dogRes = new Dog(2, "Poodle", 40, "Brown");
		String dogResJson = this.mapper.writeValueAsString(dogRes);
		
		ResultMatcher checkStatus = status().isCreated();
		ResultMatcher checkBody = content().json(dogResJson);
		
		this.mvc.perform(req).andExpect(checkStatus).andExpect(checkBody);
	}
	
	@Test
	void testGetAll() throws Exception {
		RequestBuilder req = get("/getAll");
		
		Dog dogRes = new Dog(1, "Beagle", 40, "Brown");
		List<Dog> dogList = new ArrayList<Dog>();
		dogList.add(dogRes);
		String dogResJson = this.mapper.writeValueAsString(dogList);
	
		
		ResultMatcher checkStatus = status().isOk();
		ResultMatcher checkBody = content().json(dogResJson);
		
		this.mvc.perform(req).andExpect(checkStatus).andExpect(checkBody);
	}
	
	@Test
	void testGetById() throws Exception {
		RequestBuilder req = get("/get/1");
		
		Dog dogRes = new Dog(1, "Beagle", 40, "Brown");
		String dogResJson = this.mapper.writeValueAsString(dogRes);
		
		ResultMatcher checkStatus = status().isOk();
		ResultMatcher checkBody = content().json(dogResJson);
		
		this.mvc.perform(req).andExpect(checkStatus).andExpect(checkBody);
	}
	
	@Test
	void testUpdate() throws Exception {
		
		Dog dog = new Dog(null, "Poodle", 40, "Brown");
		String dogAsJson = this.mapper.writeValueAsString(dog);
		
		RequestBuilder req = put("/update/1").contentType(MediaType.APPLICATION_JSON).content(dogAsJson);
		
		Dog dogRes = new Dog(1, "Poodle", 40, "Brown");
		String dogResJson = this.mapper.writeValueAsString(dogRes);
		
		ResultMatcher checkStatus = status().isAccepted();
		ResultMatcher checkBody = content().json(dogResJson);
		
		this.mvc.perform(req).andExpect(checkStatus).andExpect(checkBody);
	}
	
	@Test
	void testDelete() throws Exception {
		RequestBuilder req = delete("/delete/1");
		
		ResultMatcher checkStatus = status().isNoContent();
		
		this.mvc.perform(req).andExpect(checkStatus);
	}
}
