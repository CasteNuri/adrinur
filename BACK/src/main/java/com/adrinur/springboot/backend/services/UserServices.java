package com.adrinur.springboot.backend.services;

import java.security.NoSuchAlgorithmException;
import java.util.List;

import com.adrinur.springboot.backend.dto.UserRegisterDto;
import com.adrinur.springboot.backend.entities.Users;

public interface UserServices {

	public List<Users> getAllUsers();

	public Users getUserById(Long id);

	public void register(UserRegisterDto userDto) throws NoSuchAlgorithmException;

	public Users save(Users user);
	
	public Users findUserByUserName (String userName);
	
	public Users recipeAssociation(Long idRecipe, Long idUser);

	public Users deleteRecipe(Long idRecipe, Long idUser);
	
	public void deleteUser(Long id);
	
	public Users login(String email, String password) throws NoSuchAlgorithmException;
}
