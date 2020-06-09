package com.adrinur.springboot.backend.services;

import java.util.List;

import com.adrinur.springboot.backend.entities.Recipes;

public interface RecipesServices {
	
	public List<Recipes> getAllRecipes();
	
	public Recipes getRecipeById(Long id);
	
	public Recipes createRecipe(Recipes recipe);
	
	public void deleteRecipe(Long id);
	

}
