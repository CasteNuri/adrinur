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
import com.adrinur.springboot.backend.services.UsersServices;


// This CrossOrigin allows us to connect with Angular
@CrossOrigin(origins= {"http://localhost:4200"})
@RestController
public class UsersRestController {

	@Autowired
	private UsersServices userServices;
	
	@GetMapping("/users")
	public ResponseEntity<?> getAllUsers() {
		return ResponseEntity.ok().body(userServices.getAllUsers());
	}
	
	/*@GetMapping("/users/{id}")
	public Users getUser(@PathVariable Long id) {
		return userServices.getUser(id);
	}*/
	
	@GetMapping("/users/dto/{id}")
	public ResponseEntity<UsersDto> findUserDto(@PathVariable Long id) {
		Users user = new Users();
		user = userServices.getUserById(id);
		UsersDto userDto = new UsersDto(user);
		return ResponseEntity.ok().body(userDto);
	}
	
	@PostMapping("/users")
	public ResponseEntity<?> createUser(@RequestBody Users user) {
		Users userCreated = userServices.createUser(user);
		return ResponseEntity.status(HttpStatus.CREATED).body(userCreated);
	}
	
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
	
	@DeleteMapping("/users/{id}")
	public ResponseEntity<?> deleteUser(@PathVariable Long id) {
		if (id == null || id <= 0) {
			return new ResponseEntity<Object>(HttpStatus.NO_CONTENT);
		}
		userServices.deleteUser(id);
		return ResponseEntity.noContent().build();
	}

}

