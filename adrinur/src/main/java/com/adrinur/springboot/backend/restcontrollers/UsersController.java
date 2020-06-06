package com.adrinur.springboot.backend.restcontrollers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.adrinur.springboot.backend.entities.Users;
import com.adrinur.springboot.backend.services.UsersServices;

@RestController
public class UsersController {

	@Autowired
	UsersServices userServices;
	
	@GetMapping("/users")
	public List<Users> getAllUsers() {
		return userServices.getAllUsers();
	}
	
	@GetMapping("/users/{id}")
	public Users getUser(Long id) {
		return userServices.getUser(id);
	}
}
