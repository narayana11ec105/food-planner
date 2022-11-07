package com.food.planner.dao;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.food.planner.models.Recipe;

@Repository
public interface RecipeRepositry extends CrudRepository<Recipe, Long>{

}