package com.adrinur.springboot.backend.dto;

import java.util.List;

import com.adrinur.springboot.backend.entities.Recipe;
import com.adrinur.springboot.backend.entities.Users;

public class UserDto {

	private String userName;
	private String avatar;
	private List<Recipe> recipes;
	
	
	public UserDto(Users user) {
		super();
		this.userName = user.getUserName();
		this.avatar = user.getAvatar();
		this.recipes = user.getRecipes();
	}
	
	
	/**
	 * Getters && Setters
	 * 
	 */
	
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getAvatar() {
		return avatar;
	}
	public void setAvatar(String avatar) {
		this.avatar = avatar;
	}
	public List<Recipe> getRecipes() {
		return recipes;
	}
	public void setRecipes(List<Recipe> recipes) {
		this.recipes = recipes;
	}
	
	
}
