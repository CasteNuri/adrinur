package com.adrinur.springboot.backend.services;

import java.util.List;

import com.adrinur.springboot.backend.entities.Recipe;

public interface RecipeServices {

	public List<Recipe> getAllRecipes();

	public Recipe getRecipeById(Long id);

	public List<Recipe> getRecipesByType(String type);

	public Recipe createRecipe(Recipe recipe, Long idUser);

	public Recipe ingredientAssociation(Long idRecipe, Long idIngredient);

	public Recipe deleteIngredient(Long idRecipe, Long idIngredient);
	
	public void deleteRecipe(Long id);
	
	public List<Recipe> findAllFavorites();
}
