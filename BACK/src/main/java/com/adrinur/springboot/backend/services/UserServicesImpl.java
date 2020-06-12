package com.adrinur.springboot.backend.services;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.adrinur.springboot.backend.entities.Recipe;
import com.adrinur.springboot.backend.entities.Users;
import com.adrinur.springboot.backend.repositories.RecipeRepository;
import com.adrinur.springboot.backend.repositories.UserRepository;

@Service
public class UserServicesImpl implements UserServices{

	@Autowired
	private UserRepository usersRepository;
	
	@Autowired
	private RecipeRepository recipesRepository;
	
	
	
	@Override
	public List<Users> getAllUsers() {
		return (List<Users>) usersRepository.findAll();
	}

	@Override
	public Users getUserById(Long id) {
		return usersRepository.findById(id).get();
	}

	@Override
	@Transactional
	public Users createUser(Users user) {
		return usersRepository.save(user);
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

}
