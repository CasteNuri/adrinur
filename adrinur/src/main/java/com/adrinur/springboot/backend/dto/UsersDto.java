package com.adrinur.springboot.backend.dto;

import java.util.List;

import com.adrinur.springboot.backend.entities.Recipes;
import com.adrinur.springboot.backend.entities.Users;

public class UsersDto {

	private String userName;
	private String avatar;
	private List<Recipes> recipes;
	
	
	

	public UsersDto(Users user) {
		super();
		this.userName = user.getUserName();
		this.avatar = user.getAvatar();
		this.recipes = user.getRecipes();
	}
	
	/**
	 * Getters & Setters | DTO
	 * @return
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
	public List<Recipes> getRecipes() {
		return recipes;
	}
	public void setRecipes(List<Recipes> recipes) {
		this.recipes = recipes;
	}
	
	
}
