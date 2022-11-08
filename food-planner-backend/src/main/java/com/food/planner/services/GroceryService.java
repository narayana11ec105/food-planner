package com.food.planner.services;

import java.text.ParseException;

import com.food.planner.models.Groceries;
import com.food.planner.models.GroceryQuery;

public interface GroceryService {
	
	public Groceries getGroceries(GroceryQuery groceryQuery) throws ParseException;
}
