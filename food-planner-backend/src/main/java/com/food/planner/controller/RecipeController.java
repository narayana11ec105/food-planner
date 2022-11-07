package com.food.planner.controller;

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
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.food.planner.models.CookBook;
import com.food.planner.models.Recipe;
import com.food.planner.services.RecipeService;

@RequestMapping(value = "/recipes")
@RestController
@CrossOrigin
public class RecipeController {

	@Autowired RecipeService recipeService;
	
	@GetMapping()
	public ResponseEntity<CookBook> getRecipes() {
	    return new ResponseEntity<CookBook>(recipeService.getRecipes(), buildHttpHeaders(), HttpStatus.OK);
	}
	
	@GetMapping("/{recipeId}")
	public ResponseEntity<Recipe> getRecipeById(@PathVariable Long recipeId) {
		return new ResponseEntity<Recipe>(recipeService.getRecipeById(recipeId), buildHttpHeaders(), HttpStatus.OK);
	}
	
	@PostMapping(
				consumes = MediaType.APPLICATION_JSON_VALUE, 
				produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseStatus(value = HttpStatus.OK)
	public ResponseEntity<Void> postRecipes(@RequestBody CookBook cookBook) {
		recipeService.postRecipes(cookBook);
		return new ResponseEntity<Void>(buildHttpHeaders(), HttpStatus.CREATED);
	}
	
	@PutMapping(value = "/{recipeId}",
			consumes = MediaType.APPLICATION_JSON_VALUE, 
			produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseStatus(value = HttpStatus.OK)
	public ResponseEntity<Void> updateRecipe(@RequestBody Recipe recipe) {
		recipeService.updateRecipe(recipe);
		return new ResponseEntity<Void>(buildHttpHeaders(), HttpStatus.ACCEPTED);
	}	
	
	@DeleteMapping("/{recipeId}")
	@ResponseStatus(value = HttpStatus.OK)
	public ResponseEntity<Void> deleteRecipe(Long recipeId) {
		recipeService.deleteRecipe(recipeId);
		return new ResponseEntity<Void>(buildHttpHeaders(), HttpStatus.ACCEPTED);
	}
	
	private HttpHeaders buildHttpHeaders() {
		HttpHeaders responseHeaders = new HttpHeaders();
	    //responseHeaders.set("Access-Control-Allow-Origin", "*");
	    //responseHeaders.set("Access-Control-Allow-Methods", "GET,POST,OPTIONS,DELETE,PUT");

		return responseHeaders;
	}
}
