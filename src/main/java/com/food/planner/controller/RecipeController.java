package com.food.planner.controller;

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
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.food.planner.models.CookBook;
import com.food.planner.models.Recipe;
import com.food.planner.services.RecipeService;

@RequestMapping(value = "/recipes")
@RestController
public class RecipeController {

	@Autowired RecipeService recipeService;
	
	@GetMapping()
	public CookBook getRecipes() {
		return recipeService.getRecipes();
	}
	
	@GetMapping("/{recipeId}")
	public Recipe getRecipeById(@PathVariable Long recipeId) {
		return recipeService.getRecipeById(recipeId);
	}
	
	@PostMapping(
				consumes = MediaType.APPLICATION_JSON_VALUE, 
				produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseStatus(value = HttpStatus.OK)
	public void postRecipes(@RequestBody CookBook cookBook) {
		recipeService.postRecipes(cookBook);
	}
	
	@PutMapping(value = "/{recipeId}",
			consumes = MediaType.APPLICATION_JSON_VALUE, 
			produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseStatus(value = HttpStatus.OK)
	public void updateRecipe(@RequestBody Recipe recipe) {
		recipeService.updateRecipe(recipe);
	}	
	
	@DeleteMapping("/{recipeId}")
	@ResponseStatus(value = HttpStatus.OK)
	public void deleteRecipe(Long recipeId) {
		recipeService.deleteRecipe(recipeId);
	}
	
}
