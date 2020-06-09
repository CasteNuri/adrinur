package com.adrinur.springboot.backend.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.adrinur.springboot.backend.entities.Recipes;
import com.adrinur.springboot.backend.repositories.RecipesRepository;

public class RecipesServicesImpl implements RecipesServices{

	@Autowired
	private RecipesRepository recipesRepository;
	
	
	@Override
	public List<Recipes> getAllRecipes() {
		return (List<Recipes>) recipesRepository.findAll();
	}

	@Override
	public Recipes getRecipeById(Long id) {
		return recipesRepository.findById(id).get();
	}

	@Override
	public Recipes createRecipe(Recipes recipe) {
		return recipesRepository.save(recipe);
	}

	@Override
	public void deleteRecipe(Long id) {
		recipesRepository.deleteById(id);
	}

}
