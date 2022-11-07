package com.food.planner.dao;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.food.planner.models.MealPlan;

@Repository
public interface MealPlanRepository extends CrudRepository<MealPlan, Long>{

}
