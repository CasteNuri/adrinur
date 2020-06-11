package com.adrinur.springboot.backend.restcontrollers;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
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
	public ResponseEntity<?> getRecipesById(@PathVariable Long id) {
		ModelMapper modelMapper = new ModelMapper();
		
		Recipes recipe = null;
		RecipesDto recipeDto = null;
		Map<String,Object> errorResponse = new HashMap();
		
		try {
			recipe = recipesServices.getRecipeById(id);
			
		} catch (DataAccessException e) {
			errorResponse.put("mensaje", "Error al acceder a la base de datos");
			errorResponse.put("error", e.getMessage().concat(": ")
					.concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String,Object>>(errorResponse,HttpStatus.INTERNAL_SERVER_ERROR);
		}
		if(recipe == null) {
			errorResponse.put("mensaje", "El cliente con ID".concat(id.toString().concat(" no existe")));
			return new ResponseEntity<Map<String,Object>>(errorResponse, HttpStatus.NOT_FOUND);
		}
		recipeDto = modelMapper.map(recipe, RecipesDto.class);
		return new ResponseEntity<RecipesDto>(recipeDto, HttpStatus.OK);
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
	
	
	@PutMapping("/recipes/{idRecipe}/{idIngredient}")
	public ResponseEntity<Recipes> addIngredient(@PathVariable Long idRecipe, @PathVariable Long idIngredient)  {
		Recipes recipe = recipesServices.ingredientAssociation(idRecipe, idIngredient);
		
		if (idIngredient <= 0 || idIngredient == null || idRecipe <= 0 || idRecipe == null) {
            return new ResponseEntity<Recipes>(HttpStatus.NO_CONTENT);
        } 
		
		return ResponseEntity.status(HttpStatus.CREATED).body(recipe);
	}
	
	@PutMapping("/recipes/delete/{idRecipe}/{idIngredient}")
	public ResponseEntity<?> deleteIngredient(@PathVariable Long idRecipe, @PathVariable Long idIngredient)  {
		recipesServices.deleteIngredient(idRecipe, idIngredient);
		
		if (idIngredient <= 0 || idIngredient == null || idRecipe <= 0 || idRecipe == null) {
            return new ResponseEntity<Recipes>(HttpStatus.NO_CONTENT);
        }
		
		return ResponseEntity.noContent().build();
	}
	
	
	
	@PostMapping("/recipes")
	public ResponseEntity<?> createRecipe(@RequestBody Recipes recipe) {
		
		Recipes newRecipe = null;
		Map<String,Object> errorResponse = new HashMap();
		try {
			newRecipe = recipesServices.createRecipe(recipe);
		} catch (DataAccessException e) {
			errorResponse.put("mensaje", "Error al insertar en la base de datos");
			errorResponse.put("error", e.getMessage()
					.concat(": ")
					.concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String,Object>>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		errorResponse.put("newRecipe", newRecipe);
		return new ResponseEntity<Map<String,Object>>(errorResponse, HttpStatus.CREATED);
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
