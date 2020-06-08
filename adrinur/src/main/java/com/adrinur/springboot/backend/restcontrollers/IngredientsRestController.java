package com.adrinur.springboot.backend.restcontrollers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.adrinur.springboot.backend.entities.Ingredients;
import com.adrinur.springboot.backend.services.IngredientsServices;

@RestController
public class IngredientsRestController {

	@Autowired
	IngredientsServices ingredientsServices;
	
	@PostMapping("/ingredients")
	public void createIngredient(@RequestBody Ingredients ingredient) {
		ingredientsServices.createIngredient(ingredient);
		
	}
	
}
