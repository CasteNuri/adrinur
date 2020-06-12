package com.adrinur.springboot.backend.services;

import java.util.List;

import com.adrinur.springboot.backend.entities.Ingredient;

public interface IngredientServices {

	public List<Ingredient> getAllIngredients();
	
	public Ingredient createIngredient(Ingredient ingredient);
	
	public void deleteIngredient(Long id);
}
