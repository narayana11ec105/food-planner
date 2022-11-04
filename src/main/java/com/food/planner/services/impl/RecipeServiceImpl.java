package com.food.planner.services.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;

import com.food.planner.dao.RecipeRepositry;
import com.food.planner.models.CookBook;
import com.food.planner.models.Recipe;
import com.food.planner.services.RecipeService;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class RecipeServiceImpl implements RecipeService {

	@Autowired 
	private RecipeRepositry recipeRepositry;
	
	@Override
	public CookBook getRecipes() {
	    List<Recipe> recipes = new ArrayList<>();
		recipeRepositry.findAll().forEach(recipes::add);	
		return CookBook.builder().recipes(recipes).build();
		
	}

	@Override
	public Recipe getRecipeById(Long recipeId) {
		Optional<Recipe> optional = recipeRepositry.findById(recipeId);
		
		if(optional.isPresent()) {
			return optional.get();
		}else {
			log.error("The recipe with ID {} is not found", recipeId);
			throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, String.format("The recipe with ID %s is not found", recipeId));
		}
	}

	@Override
	public CookBook postRecipes(CookBook cookBook) {
		recipeRepositry.saveAll(cookBook.getRecipes());
		log.debug("Saved the recipes successfully in the database!!");
		return getRecipes();
	}

	@Override
	public Recipe updateRecipe(Recipe recipe) {
		log.debug("Updated the recipe in the database!!");
		return recipeRepositry.save(recipe);
	}

	@Override
	public void deleteRecipe(Long recipeId) {
		recipeRepositry.delete(getRecipeById(recipeId));
		log.debug("Deleted the recipe successfully from the database!!");
	}

}
