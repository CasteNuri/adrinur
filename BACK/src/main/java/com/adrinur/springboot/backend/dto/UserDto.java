package com.adrinur.springboot.backend.dto;

import java.io.Serializable;
import java.util.List;

import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.adrinur.springboot.backend.entities.Recipe;
import com.adrinur.springboot.backend.entities.Users;

public class UserDto implements Serializable{

	private Long id;
	private String userName;
	private String email;
	private String avatar;
	private List<Recipe> recipes;
	
	
	public static UserDto fromEntity(Users user) {
		UserDto dto = new UserDto();
		dto.setId(user.getId());
		dto.setEmail(user.getEmail());
		dto.setUserName(user.getUserName());
		dto.setAvatar(ServletUriComponentsBuilder.fromCurrentContextPath().build().toUriString() + '/' + user.getAvatar());
		
		return dto;
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
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	
}
