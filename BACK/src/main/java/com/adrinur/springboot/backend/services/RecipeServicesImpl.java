package com.adrinur.springboot.backend.services;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.adrinur.springboot.backend.entities.Ingredient;
import com.adrinur.springboot.backend.entities.Recipe;
import com.adrinur.springboot.backend.repositories.IngredientRepository;
import com.adrinur.springboot.backend.repositories.RecipeRepository;

@Service
public class RecipeServicesImpl implements RecipeServices{

	
	@Autowired
	private RecipeRepository recipesRepository;
	
	@Autowired
	private IngredientRepository ingredientsRepository;
	
	
	@Override
	public List<Recipe> getAllRecipes() {
		return (List<Recipe>) recipesRepository.findAll();
	}

	@Override
	public Recipe getRecipeById(Long id) {
		return recipesRepository.findById(id).get();
	}

	@Override
	public List<Recipe> getRecipesByType(String type) {
		return recipesRepository.findAllByType(type);
	}

	@Override
	public Recipe createRecipe(Recipe recipe) {
		return recipesRepository.save(recipe);
	}

	@Override
	@Transactional
	public Recipe ingredientAssociation(Long idRecipe, Long idIngredient) {
		Recipe recipe = recipesRepository.findById(idRecipe).get();
		Ingredient ingredient = ingredientsRepository.findById(idIngredient).get();
		
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
	public Recipe deleteIngredient(Long idRecipe, Long idIngredient) {
		Recipe recipe = recipesRepository.findById(idRecipe).get();
		Ingredient ingredient = ingredientsRepository.findById(idIngredient).get();
		
		recipe.getIngredients().remove(ingredient);
		recipesRepository.save(recipe);
		return recipe;
	}

	@Override
	@Transactional
	public void deleteRecipe(Long id) {
		recipesRepository.deleteById(id);
	}

	@Override
	public List<Recipe> findAllFavorites() {
		
		return recipesRepository.findAllFavorites();
	}

}
