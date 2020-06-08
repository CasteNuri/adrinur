package com.adrinur.springboot.backend.services;

import org.springframework.beans.factory.annotation.Autowired;

import com.adrinur.springboot.backend.repositories.RecipesRepository;

public class RecipesServicesImpl implements RecipesServices{

	@Autowired
	private RecipesRepository recipesrepository;
	
	@Override
	public void createRecipe() {
		// TODO Auto-generated method stub
		
	}

}
