package com.adrinur.springboot.backend.services;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.adrinur.springboot.backend.entities.Ingredients;
import com.adrinur.springboot.backend.entities.Recipes;
import com.adrinur.springboot.backend.repositories.IngredientsRepository;
import com.adrinur.springboot.backend.repositories.RecipesRepository;

@Service
public class RecipesServicesImpl implements RecipesServices{

	@Autowired
	private RecipesRepository recipesRepository;
	
	@Autowired
	private IngredientsRepository ingredientsRepository;
	
	
	@Override
	public List<Recipes> getAllRecipes() {
		return (List<Recipes>) recipesRepository.findAll();
	}

	@Override
	public Recipes getRecipeById(Long id) {
		return recipesRepository.findById(id).get();
	}

	@Override
	public List<Recipes> getRecipesByType(String type) {
		return recipesRepository.findAllByType(type);
	}
	
	
	@Override
	public Recipes createRecipe(Recipes recipe) {
		return recipesRepository.save(recipe);
	}

	@Override
	public void deleteRecipe(Long id) {
		recipesRepository.deleteById(id);
	}

	@Override
	@Transactional
	public Recipes ingredientAssociation(Long idRecipe, Long idIngredient) {
		Recipes recipe = recipesRepository.findById(idRecipe).get();
		Ingredients ingredient = ingredientsRepository.findById(idIngredient).get();
		
		if (recipe.getIngredients().contains(ingredient)) {
			recipesRepository.save(recipe);
		} else {
			recipe.getIngredients().add(ingredient);
			recipesRepository.save(recipe);
		}
		
		return recipe;
	}

	@Override
	@Transactional
	public Recipes deleteIngredient(Long idRecipe, Long idIngredient) {
		Recipes recipe = recipesRepository.findById(idRecipe).get();
		Ingredients ingredient = ingredientsRepository.findById(idIngredient).get();
		
		recipe.getIngredients().remove(ingredient);
		recipesRepository.save(recipe);
		return recipe;
	}

}
