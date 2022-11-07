package com.food.planner.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.food.planner.models.Groceries;
import com.food.planner.models.GroceryQuery;
import com.food.planner.services.GroceryService;

@RequestMapping(value = "/groceries")
@RestController
public class GroceryController {
	
	@Autowired GroceryService groceryService;
	
	@PostMapping(
			consumes = MediaType.APPLICATION_JSON_VALUE, 
			produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseStatus(value = HttpStatus.OK)
	public Groceries getGroceries(@RequestBody GroceryQuery groceryQuery) {
		return groceryService.getGroceries(groceryQuery);
	}
	
}
