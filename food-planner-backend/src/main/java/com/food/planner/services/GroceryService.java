package com.food.planner.services;

import com.food.planner.models.Groceries;
import com.food.planner.models.GroceryQuery;

public interface GroceryService {
	
	public Groceries getGroceries(GroceryQuery groceryQuery);
}
