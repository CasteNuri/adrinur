package com.adrinur.springboot.backend.services;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.adrinur.springboot.backend.entities.Ingredient;
import com.adrinur.springboot.backend.repositories.IngredientRepository;


@Service
public class IngredientServicesImpl implements IngredientServices{

	@Autowired 
	private IngredientRepository ingredientsRepository;
	
	@Override
	public List<Ingredient> getAllIngredients() {
		return (List<Ingredient>) ingredientsRepository.findAll();
	}

	@Override
	@Transactional
	public Ingredient createIngredient(Ingredient ingredient) {
		return ingredientsRepository.save(ingredient);
	}

	@Override
	@Transactional
	public void deleteIngredient(Long id) {
		ingredientsRepository.deleteById(id);
		
	}

}
