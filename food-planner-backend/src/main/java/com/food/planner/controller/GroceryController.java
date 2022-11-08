package com.food.planner.controller;

import java.text.ParseException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.food.planner.models.Groceries;
import com.food.planner.models.GroceryQuery;
import com.food.planner.services.GroceryService;

@RequestMapping(value = "/groceries")
@RestController
@CrossOrigin
public class GroceryController {
	
	@Autowired GroceryService groceryService;
	
	@GetMapping(
			produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseStatus(value = HttpStatus.OK)
	public Groceries getGroceries(@RequestParam(required=true) String startDate, 
			@RequestParam(required=true) String endDate, 
			@RequestParam(required=true) Integer numberOfPersons) throws ParseException {
		GroceryQuery gq = GroceryQuery.builder().startDate(startDate).endDate(endDate).numberOfPersons(numberOfPersons).build();
		return groceryService.getGroceries(gq);
	}
	
}
