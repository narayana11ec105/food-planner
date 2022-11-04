package com.food.planner.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.food.planner.models.MealPlan;
import com.food.planner.services.MealPlanService;

@RequestMapping(value = "/meal-plans")
@RestController
public class MealPlanController {

	private @Autowired MealPlanService mealPlanService;

	@GetMapping()
	public MealPlan getMealPlanByDate(@RequestParam(required=true) Integer day, 
										@RequestParam(required=false) Integer month, 
										@RequestParam(required=false) Integer year) {
		return mealPlanService.getMealPlanByDate(day, month, year);
	}

	@GetMapping("/{mealPlanId}")
	public MealPlan getMealPlanById(@PathVariable Long mealPlanId) {
		return mealPlanService.getMealById(mealPlanId);
	}

	@PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseStatus(value = HttpStatus.OK)
	public void postMealPlans(@RequestBody List<MealPlan> mealPlans) {
		mealPlanService.postMealPlans(mealPlans);
	}

	@PutMapping(value = "/{mealPlanId}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseStatus(value = HttpStatus.OK)
	public void updateMealPlan(@RequestBody MealPlan mealPlan) {
		mealPlanService.updateMealPlan(mealPlan);
	}

	@DeleteMapping("/{mealPlanId}")
	@ResponseStatus(value = HttpStatus.OK)
	public void deleteMealPlan(Long mealPlanId) {
		mealPlanService.deleteMealPlan(mealPlanId);
	}

}
