package com.food.planner.services.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.StreamSupport;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;

import com.food.planner.dao.MealPlanRepository;
import com.food.planner.models.MealPlan;
import com.food.planner.services.MealPlanService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class MealPlanServiceImpl implements MealPlanService{

	@Autowired MealPlanRepository mealPlanRepository;
	
	@Override
	public MealPlan getMealPlanByDate(Integer day, Integer month, Integer year) {
		
		@SuppressWarnings("deprecation")
		Optional<MealPlan> mealPlanOptional = StreamSupport.stream(mealPlanRepository.findAll().spliterator(), false)
					 .filter(mealPlan -> 	mealPlan.getMealPlanDay().getDay() == day 
					 				  	 && mealPlan.getMealPlanDay().getMonth() == month 
					 				  	 && mealPlan.getMealPlanDay().getYear() == year)
					 .findFirst();
		
		if(mealPlanOptional.isPresent()) {
			log.debug("Found the meal plan {} for the day {}:{}:{}", mealPlanOptional.get(), day, month, year);
			return mealPlanOptional.get();
		}else {
			String errorMsg = String.format("Could not find the meal for the day %s:%s:%s", day, month, year);
			log.error(errorMsg);
			throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, errorMsg);
		}
	}

	@Override
	public MealPlan getMealById(Long mealPlanId) {
		Optional<MealPlan> mealPlanOptional = mealPlanRepository.findById(mealPlanId);
		
		if(mealPlanOptional.isPresent()) {
			log.debug("Found the meal plan {} with ID {}", mealPlanOptional.get(), mealPlanId);
			return mealPlanOptional.get();
		}else {
			String errorMsg = String.format("Could not find the meal with the ID %s", mealPlanId);
			log.error(errorMsg);
			throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, errorMsg);
		}
	}

	@Override
	public List<MealPlan> getAllMealPlans(){
		List<MealPlan> mealPlans = new ArrayList<>();
		mealPlanRepository.findAll().forEach(mealPlans::add);
		return mealPlans;
	}
	
	@Override
	public void postMealPlans(List<MealPlan> mealPlans) {
		mealPlanRepository.saveAll(mealPlans);
		log.debug("Saved the meal plans");
	}

	@Override
	public void updateMealPlan(MealPlan mealPlan) {
		mealPlanRepository.save(mealPlan);
		log.debug("Updated the meal plan");
	}

	@Override
	public void deleteMealPlan(Long mealPlanId) {
		mealPlanRepository.delete(getMealById(mealPlanId));
		log.debug("Deleted the meal plan");
		
	}

}
