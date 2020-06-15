package com.adrinur.springboot.backend.restcontrollers;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.adrinur.springboot.backend.dto.UserDto;
import com.adrinur.springboot.backend.entities.Users;
import com.adrinur.springboot.backend.services.RecipeServices;
import com.adrinur.springboot.backend.services.UserServices;

//This CrossOrigin allows us to connect with Angular
@CrossOrigin(origins= {"http://localhost:4200"})
@RestController
public class UserRestController {

	@Autowired
	private UserServices userServices;
	
	@Autowired
	private RecipeServices recipeServices;
	
	@GetMapping("/users")
	public ResponseEntity<?> getAllUsers() {
		return ResponseEntity.ok().body(userServices.getAllUsers());
	}
	
	@GetMapping("/users/dto/{id}")
	public ResponseEntity<UserDto> findUserDto(@PathVariable Long id) {
		Users user = new Users();
		user = userServices.getUserById(id);
		UserDto userDto = new UserDto(user);
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
		recipeServices.deleteRecipe(idRecipe);
		
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

	
	
	@PostMapping("/users/login")
	public ResponseEntity<?> validationUser(@Validated @RequestBody Users user) {
		Map<String,Object> response = new HashMap();
		String message;
		
		Users userDataBase = userServices.matchUserDataBase(user.getUserName(), user.getPassword());
		
		if(userDataBase.getId() != -1.0) {
			message = "ok";
			response.put("ok", true);
			response.put("user", userDataBase);
		} else {
			message = "false";
			response.put("ok", false);
		}
		
		return new ResponseEntity<Map<String,Object>>(response, HttpStatus.OK);
	}
	
	
}
