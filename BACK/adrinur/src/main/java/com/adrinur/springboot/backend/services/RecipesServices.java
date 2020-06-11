package com.adrinur.springboot.backend.services;

import java.util.List;

import com.adrinur.springboot.backend.entities.Recipes;

public interface RecipesServices {
	
	public List<Recipes> getAllRecipes();
	
	public Recipes getRecipeById(Long id);
	
	public List<Recipes> getRecipesByType(String type);
	
	public Recipes createRecipe(Recipes recipe);
	
	public void deleteRecipe(Long id);
	
	public Recipes ingredientAssociation(Long idRecipe, Long idIngredient);
	
	public Recipes deleteIngredient(Long idRecipe, Long idIngredient);
}
