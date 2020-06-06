package com.adrinur.springboot.backend.services;

import java.util.List;

import com.adrinur.springboot.backend.entities.Users;


public interface UsersServices {

	public List<Users> getAllUsers();
	
	public Users getUser(Long id);
	
	public Users createUser(Users user);
	
	public void deleteUser(Long id);
	
	
}
