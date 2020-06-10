package com.adrinur.springboot.backend.dto;

import com.adrinur.springboot.backend.entities.Users;

public class RecipesDto {
	
	private String title;
	private String image;
	private String quantities;
	private Long time;
	private String difficulty;
	private String description;
	private int rating;
	private boolean favorite;
	private Users user;
	
	public RecipesDto(String title, String image, String quantities, Long time, String difficulty, String description,
			int rating, boolean favorite, Users user) {
		super();
		this.title = title;
		this.image = image;
		this.quantities = quantities;
		this.time = time;
		this.difficulty = difficulty;
		this.description = description;
		this.rating = rating;
		this.favorite = favorite;
		this.user = user;
	}
	
	public RecipesDto() {
		
	}
	
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getImage() {
		return image;
	}
	public void setImage(String image) {
		this.image = image;
	}
	public String getQuantities() {
		return quantities;
	}
	public void setQuantities(String quantities) {
		this.quantities = quantities;
	}
	public Long getTime() {
		return time;
	}
	public void setTime(Long time) {
		this.time = time;
	}
	public String getDifficulty() {
		return difficulty;
	}
	public void setDifficulty(String difficulty) {
		this.difficulty = difficulty;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public int getRating() {
		return rating;
	}
	public void setRating(int rating) {
		this.rating = rating;
	}
	public boolean isFavorite() {
		return favorite;
	}
	public void setFavorite(boolean favorite) {
		this.favorite = favorite;
	}
	public Users getUser() {
		return user;
	}
	public void setUser(Users user) {
		this.user = user;
	}
	
	
	
	
	

}
