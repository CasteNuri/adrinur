package com.adrinur.springboot.backend.services;

import java.util.List;

import com.adrinur.springboot.backend.entities.Users;


public interface UsersServices {

	public List<Users> getAllUsers();
	
	public Users getUserById(Long id);
	
	public Users createUser(Users user);
	
	public void deleteUser(Long id);
	
	public Users recipeAssociation(Long idRecipe, Long idUser);
	
	public Users deleteRecipe(Long idRecipe, Long idUser);
	
}
