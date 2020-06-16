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
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.adrinur.springboot.backend.dto.RecipeDto;
import com.adrinur.springboot.backend.dto.RecipeResumeDto;
import com.adrinur.springboot.backend.entities.Recipe;
import com.adrinur.springboot.backend.services.RecipeServices;

@CrossOrigin(origins= {"http://localhost:4200"})
@RestController
public class RecipeRestController {

	@Autowired
	RecipeServices recipeServices;
	
	
	@GetMapping("/recipes")
	public ResponseEntity<?> getAllRecipes() {
		ModelMapper modelMapper = new ModelMapper();
		return ResponseEntity.ok().body(recipeServices.getAllRecipes().stream().map(r -> modelMapper.map(r, RecipeDto.class)));
	}
	
	@GetMapping("/recipes/detail/{id}")
	public ResponseEntity<?> getRecipesById(@PathVariable Long id) {
		ModelMapper modelMapper = new ModelMapper();
		
		Recipe recipe = null;
		RecipeDto recipeDto = null;
		Map<String,Object> errorResponse = new HashMap();
		
		//try {
			recipe = recipeServices.getRecipeById(id);
			if (recipe == null || id <= 0 || id == null) {
				errorResponse.put("mensaje", "El cliente con ID".concat(id.toString().concat(" no existe")));
				return new ResponseEntity<Map<String,Object>>(errorResponse, HttpStatus.NOT_FOUND);
	        }
		/*} catch (DataAccessException e) {
			errorResponse.put("mensaje", "Error al acceder a la base de datos");
			errorResponse.put("error", e.getMessage().concat(": ")
					.concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String,Object>>(errorResponse,HttpStatus.INTERNAL_SERVER_ERROR);
		}*/
		
		recipeDto = modelMapper.map(recipe, RecipeDto.class);
		return new ResponseEntity<RecipeDto>(recipeDto, HttpStatus.OK);
	}
	
	@GetMapping("/recipes/resume/{type}")
	public ResponseEntity<?> getRecipesByType(@PathVariable String type) {
		ModelMapper modelMapper = new ModelMapper();
		List<Recipe> recList = recipeServices.getRecipesByType(type);
		List<RecipeResumeDto> resumeList = new ArrayList<RecipeResumeDto>();
		
		if(recList.size() <= 0) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		} else {
			for (Recipe recipe : recList) {
	        resumeList.add(modelMapper.map(recipe, RecipeResumeDto.class));
	    }
		return new ResponseEntity<>(resumeList, HttpStatus.OK);
		}
		
	}
	
	
	@GetMapping("/recipes/favorites")
	public ResponseEntity<?> findAllFavorites() {
		ModelMapper modelMapper = new ModelMapper();
		List<Recipe> recList = recipeServices.findAllFavorites();
		List<RecipeResumeDto> favoriteList = new ArrayList<RecipeResumeDto>();
		
		if(recList.size() <= 0) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		} else {
			for (Recipe recipe : recList) {
	        favoriteList.add(modelMapper.map(recipe, RecipeResumeDto.class));
	    }
		return new ResponseEntity<>(favoriteList, HttpStatus.OK);
		}
		
	}
	
	
	@PutMapping("/recipes/changefav/{id}")
    public ResponseEntity<Recipe> updateFav(@RequestBody Boolean favorite, @PathVariable Long id) {
        Recipe currentRecipe = recipeServices.getRecipeById(id);
        if (currentRecipe == null || id <= 0 || id == null) {
            return new ResponseEntity<Recipe>(HttpStatus.NOT_FOUND);
        }
        
        currentRecipe.setFavorite(favorite);
        
        recipeServices.save(currentRecipe);
        return new ResponseEntity<Recipe>(currentRecipe, HttpStatus.OK);
    }
	
	@PutMapping("/recipes/changerating/{id}")
    public ResponseEntity<Recipe> updateRating(@RequestBody int rating, @PathVariable Long id) {
        Recipe currentRecipe = recipeServices.getRecipeById(id);
        if (currentRecipe == null || id <= 0 || id == null) {
            return new ResponseEntity<Recipe>(HttpStatus.NOT_FOUND);
        }
        
        currentRecipe.setRating(rating);
        
        recipeServices.save(currentRecipe);
        return new ResponseEntity<Recipe>(currentRecipe, HttpStatus.OK);
    }
	
	
	@PutMapping("/recipes/{idRecipe}/{idIngredient}")
	public ResponseEntity<Recipe> addIngredient(@PathVariable Long idRecipe, @PathVariable Long idIngredient)  {
		Recipe recipe = recipeServices.ingredientAssociation(idRecipe, idIngredient);
		
		if (idIngredient <= 0 || idIngredient == null || idRecipe <= 0 || idRecipe == null) {
            return new ResponseEntity<Recipe>(HttpStatus.NO_CONTENT);
        } 
		
		return ResponseEntity.status(HttpStatus.CREATED).body(recipe);
	}
	
	@PutMapping("/recipes/delete/{idRecipe}/{idIngredient}")
	public ResponseEntity<?> deleteIngredient(@PathVariable Long idRecipe, @PathVariable Long idIngredient)  {
		recipeServices.deleteIngredient(idRecipe, idIngredient);
		
		if (idIngredient <= 0 || idIngredient == null || idRecipe <= 0 || idRecipe == null) {
            return new ResponseEntity<Recipe>(HttpStatus.NO_CONTENT);
        }
		
		return ResponseEntity.noContent().build();
	}
	
	
	@PostMapping("/recipes")
	public ResponseEntity<?> createRecipe(Authentication authentication, @RequestBody Recipe recipe) {
		Long idCreator = ((Integer)authentication.getCredentials()).longValue();
		Recipe newRecipe = null;
		Map<String,Object> errorResponse = new HashMap();
		try {
			newRecipe = recipeServices.createRecipe(recipe, idCreator);
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
	public ResponseEntity<?> deleteRecipe(Authentication authentication, @PathVariable Long id) {
		Long idCreatorToken = ((Integer)authentication.getCredentials()).longValue();
		Recipe recipe = recipeServices.getRecipeById(id);
		Long idCreatorRecipe = recipe.getUser().getId();
		if (id == null || id <= 0) {
			return new ResponseEntity<Object>(HttpStatus.NO_CONTENT);
		}
		
		if(idCreatorToken != idCreatorRecipe) {
			return new ResponseEntity<Object>(HttpStatus.FORBIDDEN);
		}
		recipeServices.deleteRecipe(id);
		return ResponseEntity.noContent().build();
	}
}
