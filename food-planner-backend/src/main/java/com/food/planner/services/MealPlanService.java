package com.food.planner.services;

import java.util.List;

import com.food.planner.models.MealPlan;

public interface MealPlanService {

	public MealPlan getMealPlanByDate(Integer day, Integer month, Integer year);
	public MealPlan getMealById(Long mealPlanId);
	public List<MealPlan> getAllMealPlans();
	public void postMealPlans(List<MealPlan> mealPlan);
	public void updateMealPlan(MealPlan mealPlan);
	public void deleteMealPlan(Long mealPlanId);
	
}
