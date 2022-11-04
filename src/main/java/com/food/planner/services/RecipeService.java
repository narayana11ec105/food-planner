package com.food.planner.services;

import com.food.planner.models.CookBook;
import com.food.planner.models.Recipe;

public interface RecipeService {
	
	public CookBook getRecipes();
	public Recipe getRecipeById(Long recipeId);
	public CookBook postRecipes(CookBook cookBook);
	public Recipe updateRecipe(Recipe recipe);
	public void deleteRecipe(Long recipeId);
}
