package com.adrinur.springboot.backend.services;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.adrinur.springboot.backend.entities.Recipes;
import com.adrinur.springboot.backend.entities.Users;
import com.adrinur.springboot.backend.repositories.RecipesRepository;
import com.adrinur.springboot.backend.repositories.UsersRepository;

@Service
public class UsersServicesImpl implements UsersServices{

	@Autowired
	private UsersRepository userRepository;
	@Autowired
	private RecipesRepository recipesRepository;
	
	@Override
	public List<Users> getAllUsers() {
		return (List<Users>) userRepository.findAll();
	}

	@Override
	public Users getUserById(Long id) {
		return userRepository.findById(id).get();
	}

	@Override
	@Transactional
	public Users createUser(Users user) {
		return userRepository.save(user);
	}

	@Override
	@Transactional
	public void deleteUser(Long id) {
		userRepository.deleteById(id);
	}

	@Override
	@Transactional
	public Users recipeAssociation(Long idRecipe, Long idUser) {
		Users user = userRepository.findById(idUser).get();
		Recipes recipe = recipesRepository.findById(idRecipe).get();
		
		if (user.getRecipes().contains(recipe)) {
			userRepository.save(user);
		} else {
			user.getRecipes().add(recipe);
			userRepository.save(user);
		}
		
		return user;
	}

	@Override
	@Transactional
	public Users deleteRecipe(Long idRecipe, Long idUser) {
		Users user = userRepository.findById(idUser).get();
		Recipes recipe = recipesRepository.findById(idRecipe).get();
		
		user.getRecipes().remove(recipe);
		userRepository.save(user);
		
		return user;
	}

	
}
