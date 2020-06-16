package com.adrinur.springboot.backend.services;

import java.security.NoSuchAlgorithmException;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.adrinur.springboot.backend.dto.UserRegisterDto;
import com.adrinur.springboot.backend.entities.Recipe;
import com.adrinur.springboot.backend.entities.Users;
import com.adrinur.springboot.backend.repositories.RecipeRepository;
import com.adrinur.springboot.backend.repositories.UserRepository;
import com.adrinur.springboot.backend.utils.ImageUtils;
import com.adrinur.springboot.backend.utils.SecurityUtils;

@Service
public class UserServicesImpl implements UserServices{

	@Autowired
	private UserRepository usersRepository;
	
	@Autowired
	private RecipeRepository recipesRepository;
	
	@Autowired
	public ImageUtils imageUtils;
	
	@Autowired
	public SecurityUtils securityUtils;
	
	
	@Override
	public List<Users> getAllUsers() {
		return (List<Users>) usersRepository.findAll();
	}

	@Override
	public Users getUserById(Long id) {
		return usersRepository.findById(id).get();
	}

	@Override
	public Users findUserByUserName(String userName) {
		return usersRepository.findUserByUserName(userName);
	}

	@Override
	@Transactional
	public Users recipeAssociation(Long idRecipe, Long idUser) {
		Users user = usersRepository.findById(idUser).get();
		Recipe recipe = recipesRepository.findById(idRecipe).get();
		
		if (user.getRecipes().contains(recipe)) {
			usersRepository.save(user);
		} else {
			user.getRecipes().add(recipe);
			usersRepository.save(user);
		}
		
		return user;
	}

	@Override
	@Transactional
	public Users deleteRecipe(Long idRecipe, Long idUser) {
		Users user = usersRepository.findById(idUser).get();
		Recipe recipe = recipesRepository.findById(idRecipe).get();
		
		user.getRecipes().remove(recipe);
		usersRepository.save(user);
		
		return user;
	}

	@Override
	@Transactional
	public void deleteUser(Long id) {
		usersRepository.deleteById(id);
	}

	@Override
	public Users login(String email, String password) throws NoSuchAlgorithmException{
		
		return usersRepository.login(email, securityUtils.encodePassword(password));
	}


	public void register(UserRegisterDto userDto) throws NoSuchAlgorithmException {
		Users newUser = new Users();
		newUser.setAvatar(imageUtils.saveImageBase64("users", userDto.getAvatar()));
		newUser.setPassword(securityUtils.encodePassword(userDto.getPassword()));
		newUser.setEmail(userDto.getEmail());
		newUser.setUserName(userDto.getUserName());
		this.usersRepository.save(newUser);
	}

	@Override
	public Users save(Users user) {
		return usersRepository.save(user);
	}
	
}
