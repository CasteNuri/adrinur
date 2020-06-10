package com.adrinur.springboot.backend.services;

import java.util.List;

import com.adrinur.springboot.backend.entities.Ingredients;

public interface IngredientsServices {
	
	
	public List<Ingredients> getAllIngredients(); 
	public Ingredients createIngredient(Ingredients ingredient);
	public void deleteIngredient(Long id);

}
