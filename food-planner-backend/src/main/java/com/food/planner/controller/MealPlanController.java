package com.food.planner.controller;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
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

import lombok.extern.slf4j.Slf4j;

@RequestMapping(value = "/meal-plans")
@RestController
@CrossOrigin
@Slf4j
public class MealPlanController {

	private @Autowired MealPlanService mealPlanService;

	
	@GetMapping()
	public ResponseEntity<List<MealPlan>> getMealPlanByDate(@RequestParam(required=false) Integer day, 
										@RequestParam(required=false) Integer month, 
										@RequestParam(required=false) Integer year) {
		
		if(day == null || month == null || year == null) {
			return new ResponseEntity<List<MealPlan>>(mealPlanService.getAllMealPlans(), buildHttpHeaders(), HttpStatus.OK);
		}
		return new ResponseEntity<List<MealPlan>>(Arrays.asList(mealPlanService.getMealPlanByDate(day, month, year)), buildHttpHeaders(), HttpStatus.OK);
	}

	@GetMapping("/{mealPlanId}")
	public ResponseEntity<MealPlan> getMealPlanById(@PathVariable Long mealPlanId) {
		return new ResponseEntity<MealPlan>(mealPlanService.getMealById(mealPlanId), buildHttpHeaders(), HttpStatus.OK);

	}

	@PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseStatus(value = HttpStatus.OK)
	public ResponseEntity<Void> postMealPlans(@RequestBody List<MealPlan> mealPlans) {
		log.debug("meal plans {}", mealPlans);
		mealPlanService.postMealPlans(mealPlans);
		return new ResponseEntity<Void>(buildHttpHeaders(), HttpStatus.OK);
	}

	@PutMapping(value = "/{mealPlanId}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseStatus(value = HttpStatus.OK)
	public ResponseEntity<Void> updateMealPlan(@RequestBody MealPlan mealPlan) {
		mealPlanService.updateMealPlan(mealPlan);
		return new ResponseEntity<Void>(buildHttpHeaders(), HttpStatus.OK);
	}

	@DeleteMapping("/{mealPlanId}")
	@ResponseStatus(value = HttpStatus.OK)
	public ResponseEntity<Void> deleteMealPlan(Long mealPlanId) {
		mealPlanService.deleteMealPlan(mealPlanId);
		return new ResponseEntity<Void>(buildHttpHeaders(), HttpStatus.OK);
	}

	private HttpHeaders buildHttpHeaders() {
		HttpHeaders responseHeaders = new HttpHeaders();
	    //responseHeaders.set("Access-Control-Allow-Origin", "*");
	    //responseHeaders.set("Access-Control-Allow-Methods", "GET,POST,OPTIONS,DELETE,PUT");

		return responseHeaders;
	}
}
