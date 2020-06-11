package com.adrinur.springboot.backend.restcontrollers;


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

import com.adrinur.springboot.backend.dto.UsersDto;
import com.adrinur.springboot.backend.entities.Users;
import com.adrinur.springboot.backend.services.RecipesServices;
import com.adrinur.springboot.backend.services.UsersServices;


// This CrossOrigin allows us to connect with Angular
@CrossOrigin(origins= {"http://localhost:4200"})
@RestController
public class UsersRestController {

	@Autowired
	private UsersServices userServices;
	
	@Autowired
	RecipesServices recipesServices;
	
	@GetMapping("/users")
	public ResponseEntity<?> getAllUsers() {
		return ResponseEntity.ok().body(userServices.getAllUsers());
	}
	
	@GetMapping("/users/dto/{id}")
	public ResponseEntity<UsersDto> findUserDto(@PathVariable Long id) {
		Users user = new Users();
		user = userServices.getUserById(id);
		UsersDto userDto = new UsersDto(user);
		return ResponseEntity.ok().body(userDto);
	}
	
	
	
	
	
	
	// In order to create a new user and add it to the ddbb
	@PostMapping("/users")
	public ResponseEntity<?> createUser(@RequestBody Users user) {
		Users userCreated = userServices.createUser(user);
		//Base64.encodeBase64String(userCreated.getPassword());
		return ResponseEntity.status(HttpStatus.CREATED).body(userCreated);
	}
	
	
	//This method allows the user to update his profile
	@PutMapping("/users/updateUser/{id}")
    public ResponseEntity<Users> updateUser(@RequestBody Users user, @PathVariable Long id) {
        Users currentUser = userServices.getUserById(id);
        if (currentUser == null || id <= 0 || id == null) {
            return new ResponseEntity<Users>(HttpStatus.NOT_FOUND);
        }
        
        currentUser.setUserName(user.getUserName());
        currentUser.setEmail(user.getEmail());
        currentUser.setAvatar(user.getAvatar());
        
        userServices.createUser(currentUser);
        return new ResponseEntity<Users>(currentUser, HttpStatus.OK);
    }
	
	//This method is used to change an user's password whenever they forgot theirs
	@PutMapping("/users/changePassword/{id}")
    public ResponseEntity<Users> updatePassword(@RequestBody Users user, @PathVariable Long id) {
        Users currentUser = userServices.getUserById(id);
        if (currentUser == null || id <= 0 || id == null) {
            return new ResponseEntity<Users>(HttpStatus.NOT_FOUND);
        }
        
        currentUser.setPassword(user.getPassword());
        
        userServices.createUser(currentUser);
        return new ResponseEntity<Users>(currentUser, HttpStatus.OK);
    }
	
	
	@PutMapping("/users/{idUser}/{idRecipe}")
	public ResponseEntity<Users> addRecipe(@PathVariable Long idUser, @PathVariable Long idRecipe)  {
		Users user = userServices.recipeAssociation(idRecipe, idUser);
		
		if (idUser <= 0 || idUser == null || idRecipe <= 0 || idRecipe == null) {
            return new ResponseEntity<Users>(HttpStatus.NO_CONTENT);
        }
		
		return ResponseEntity.status(HttpStatus.CREATED).body(user);
	}
	
	@PutMapping("/users/delete/{idUser}/{idRecipe}")
	public ResponseEntity<?> deleteRecipe(@PathVariable Long idUser, @PathVariable Long idRecipe)  {
		userServices.deleteRecipe(idRecipe, idUser);
		recipesServices.deleteRecipe(idRecipe);
		
		if (idUser <= 0 || idUser == null || idRecipe <= 0 || idRecipe == null) {
            return new ResponseEntity<Users>(HttpStatus.NO_CONTENT);
        }
		
		return ResponseEntity.noContent().build();
	}
	
	
	
	@DeleteMapping("/users/{id}")
	public ResponseEntity<?> deleteUser(@PathVariable Long id) {
		if (id == null || id <= 0) {
			return new ResponseEntity<Object>(HttpStatus.NO_CONTENT);
		}
		userServices.deleteUser(id);
		return ResponseEntity.noContent().build();
	}

}

