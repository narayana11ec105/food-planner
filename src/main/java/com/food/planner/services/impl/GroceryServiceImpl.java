package com.food.planner.services.impl;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.food.planner.models.Groceries;
import com.food.planner.models.GroceryQuery;
import com.food.planner.models.Item;
import com.food.planner.models.Meal;
import com.food.planner.services.GroceryService;
import com.food.planner.services.MealPlanService;
import com.food.planner.services.RecipeService;

@Service
public class GroceryServiceImpl implements GroceryService{

	@Autowired MealPlanService mealPlanService;
	@Autowired RecipeService recipeService;
	
	@Override
	public Groceries getGroceries(GroceryQuery groceryQuery) {
		List<Meal> mealsForDaysSelected = mealPlanService.getAllMealPlans()
														 .stream()
														 .filter(mealPlan -> !groceryQuery.getStartDate().after(mealPlan.getMealPlanDay()) 
																 			&& !groceryQuery.getEndDate().before(mealPlan.getMealPlanDay()))
														 .flatMap(mealPlan -> mealPlan.getMeals().stream())
														 .collect(Collectors.toList());
		//Calculate groceries
		Map<String, List<Item>> items = mealsForDaysSelected.stream()
														    .flatMap(meal -> recipeService.getRecipeById(meal.getRecipeId()).getItems().stream())
														    .collect(Collectors.groupingBy(Item::getName));
		
		List<Item> itemsCurated = items.entrySet().stream()
												  .map(entry -> aggregateQuantity(entry.getValue()))
												  .collect(Collectors.toList());
		
		return Groceries.builder().items(itemsCurated).build();
	}
	
	private Item aggregateQuantity(List<Item> items) {
		int totalQuantity = items.stream()
								 .map(Item::getQuantity)
								 .reduce(0, (a,b) -> a + b);
		return Item.builder()
				   .name(items.get(0).getName())
				   .quantity(totalQuantity)
				   .build();
	}
}
