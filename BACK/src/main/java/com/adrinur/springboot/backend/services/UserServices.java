package com.adrinur.springboot.backend.services;

import java.util.List;

import com.adrinur.springboot.backend.entities.Users;

public interface UserServices {

	public List<Users> getAllUsers();

	public Users getUserById(Long id);

	public Users createUser(Users user);

	public Users recipeAssociation(Long idRecipe, Long idUser);

	public Users deleteRecipe(Long idRecipe, Long idUser);
	
	public void deleteUser(Long id);
	
	public Users matchUserDataBase(String username, String password);
}
