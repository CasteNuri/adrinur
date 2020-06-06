package com.adrinur.springboot.backend.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.adrinur.springboot.backend.entities.Ingredients;
import com.adrinur.springboot.backend.repositories.IngredientsRepository;

@Service
public class IngredientsServicesImpl implements IngredientsServices{

	@Autowired 
	IngredientsRepository ingredientsRepository;
	
	@Override
	public void createIngredient(Ingredients ingredient) {
		ingredientsRepository.save(ingredient);
		
	}

}
