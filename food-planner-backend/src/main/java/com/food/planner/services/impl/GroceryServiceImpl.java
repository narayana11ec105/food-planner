package com.food.planner.services.impl;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.food.planner.models.Groceries;
import com.food.planner.models.GroceryQuery;
import com.food.planner.models.Item;import com.food.planner.models.ItemType;
import com.food.planner.models.Meal;
import com.food.planner.services.GroceryService;
import com.food.planner.services.MealPlanService;
import com.food.planner.services.RecipeService;

@Service
public class GroceryServiceImpl implements GroceryService{

	@Autowired MealPlanService mealPlanService;
	@Autowired RecipeService recipeService;
	
	@Override
	public Groceries getGroceries(GroceryQuery groceryQuery) throws ParseException {
		
		Date startDate = new SimpleDateFormat("yyyy-MM-dd").parse(groceryQuery.getStartDate());  
		Date endDate = new SimpleDateFormat("yyyy-MM-dd").parse(groceryQuery.getEndDate());  

		
		List<Meal> mealsForDaysSelected = mealPlanService.getAllMealPlans()
														 .stream()
														 .filter(mealPlan -> {
															try {
																return !startDate.after(new SimpleDateFormat("yyyy-MM-dd").parse(mealPlan.getPlannedForDay())) 
																		 			&& !endDate.before(new SimpleDateFormat("yyyy-MM-dd").parse(mealPlan.getPlannedForDay()));
															} catch (ParseException e) {
																// TODO Auto-generated catch block
																e.printStackTrace();
															}
															return false;
														})
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
		
		int totalCost = items.stream()
				 .map(Item::getCost)
				 .reduce(0, (a,b) -> a + b);
		
		return Item.builder()
				   .name(items.get(0).getName())
				   .quantity(totalQuantity)
				   .cost(totalCost)
				   .purchaseFrequency(items.get(0).getPurchaseFrequency())
				   .type(items.get(0).getType())
				   .build();
	}
}
