package com.adrinur.springboot.backend.services;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.adrinur.springboot.backend.entities.Ingredients;
import com.adrinur.springboot.backend.repositories.IngredientsRepository;

@Service
public class IngredientsServicesImpl implements IngredientsServices{

	@Autowired 
	private IngredientsRepository ingredientsRepository;

	@Override
	public List<Ingredients> getAllIngredients() {
		return (List<Ingredients>) ingredientsRepository.findAll();
	}
	
	@Override
	@Transactional
	public Ingredients createIngredient(Ingredients ingredient) {
		return ingredientsRepository.save(ingredient);
		
	}

	@Override
	@Transactional
	public void deleteIngredient(Long id) {
		ingredientsRepository.deleteById(id);
	}

}
