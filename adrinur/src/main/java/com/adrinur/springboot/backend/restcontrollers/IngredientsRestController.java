package com.adrinur.springboot.backend.restcontrollers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.adrinur.springboot.backend.entities.Ingredients;
import com.adrinur.springboot.backend.services.IngredientsServices;

@CrossOrigin(origins= {"http://localhost:4200"})
@RestController
public class IngredientsRestController {

	@Autowired
	private IngredientsServices ingredientsServices;
	
	@GetMapping("/ingredients")
	public ResponseEntity<?> getAllIngredients() {
		return ResponseEntity.ok().body(ingredientsServices.getAllIngredients());
	}
	
	@PostMapping("/ingredients")
	public ResponseEntity<?> createIngredient(@RequestBody Ingredients ingredient) {
		Ingredients ingredientCreated = ingredientsServices.createIngredient(ingredient);
		return ResponseEntity.status(HttpStatus.CREATED).body(ingredientCreated);
	}
	
	@DeleteMapping("/ingredients/{id}")
	public ResponseEntity<?> deleteIngredient(@PathVariable Long id) {
		if (id == null || id <= 0) {
			return new ResponseEntity<Object>(HttpStatus.NO_CONTENT);
		}
		ingredientsServices.deleteIngredient(id);
		return ResponseEntity.noContent().build();
	}
	
}
