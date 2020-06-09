package com.adrinur.springboot.backend.restcontrollers;

import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.adrinur.springboot.backend.dto.RecipesDto;
import com.adrinur.springboot.backend.dto.RecipesResumeDto;
import com.adrinur.springboot.backend.entities.Recipes;
import com.adrinur.springboot.backend.services.RecipesServices;

@CrossOrigin(origins= {"http://localhost:4200"})
@RestController
public class RecipesRestController {
	
	@Autowired
	RecipesServices recipesServices;
	
	@GetMapping("/recipes")
	public ResponseEntity<?> getAllRecipes() {
		return ResponseEntity.ok().body(recipesServices.getAllRecipes());
	}
	
	@GetMapping("/recipes/detail/{id}")
	public ResponseEntity<RecipesDto> getRecipesById(@PathVariable Long id) {
		ModelMapper modelMapper = new ModelMapper();
		Recipes recipe = recipesServices.getRecipeById(id);
		RecipesDto recipeDto = modelMapper.map(recipe, RecipesDto.class);
		return ResponseEntity.ok().body(recipeDto);
	}
	
	@GetMapping("/recipes/resume/{type}")
	public ResponseEntity<?> getRecipesByType(@PathVariable String type) {
		ModelMapper modelMapper = new ModelMapper();
		List<Recipes> recList = recipesServices.getRecipesByType(type);
		List<RecipesResumeDto> resumeList = new ArrayList<RecipesResumeDto>();
		
		for (Recipes recipe : recList) {
	        resumeList.add(modelMapper.map(recipe, RecipesResumeDto.class));
	    }
		return new ResponseEntity<>(resumeList, HttpStatus.OK);
	}
	
	
	@PutMapping("/recipes/changefav/{id}")
    public ResponseEntity<Recipes> updateFav(@RequestBody Boolean favorite, @PathVariable Long id) {
        Recipes currentRecipe = recipesServices.getRecipeById(id);
        if (currentRecipe == null || id <= 0 || id == null) {
            return new ResponseEntity<Recipes>(HttpStatus.NOT_FOUND);
        }
        
        currentRecipe.setFavorite(favorite);
        
        recipesServices.createRecipe(currentRecipe);
        return new ResponseEntity<Recipes>(currentRecipe, HttpStatus.OK);
    }
	
	@PutMapping("/recipes/changerating/{id}")
    public ResponseEntity<Recipes> updateRating(@RequestBody Recipes recipe, @PathVariable Long id) {
        Recipes currentRecipe = recipesServices.getRecipeById(id);
        if (currentRecipe == null || id <= 0 || id == null) {
            return new ResponseEntity<Recipes>(HttpStatus.NOT_FOUND);
        }
        
        currentRecipe.setRating(recipe.getRating());
        
        recipesServices.createRecipe(currentRecipe);
        return new ResponseEntity<Recipes>(currentRecipe, HttpStatus.OK);
    }
	
	@PostMapping("/recipes")
	public ResponseEntity<Recipes> createRecipe(@RequestBody Recipes recipe) {
		Recipes newRecipe = recipesServices.createRecipe(recipe);
		return ResponseEntity.status(HttpStatus.CREATED).body(newRecipe);
	}
	
	@DeleteMapping("/recipes/{id}")
	public ResponseEntity<?> deleteRecipe(@PathVariable Long id) {
		if (id == null || id <= 0) {
			return new ResponseEntity<Object>(HttpStatus.NO_CONTENT);
		}
		recipesServices.deleteRecipe(id);
		return ResponseEntity.noContent().build();
	}
	
	

}
