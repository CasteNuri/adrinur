package com.adrinur.springboot.backend.dto;


import java.io.Serializable;

import com.adrinur.springboot.backend.entities.Users;

public class RecipeResumeDto implements Serializable{
	private Long codRec;
	private String title;
	private String image;
	private String time;
	private String difficulty;
	private int rating;
	private Boolean favorite;
	private String type;
	private Users user;
	

	public RecipeResumeDto(Long codRec, String title, String image, String time, String difficulty, int rating,
			Boolean favorite, String type, Users user) {
		super();
		this.codRec = codRec;
		this.title = title;
		this.image = image;
		this.time = time;
		this.difficulty = difficulty;
		this.rating = rating;
		this.favorite = favorite;
		this.type = type;
		this.user = user;
	}


	public RecipeResumeDto() {
		
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

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}

	public String getDifficulty() {
		return difficulty;
	}

	public void setDifficulty(String difficulty) {
		this.difficulty = difficulty;
	}

	public int getRating() {
		return rating;
	}

	public void setRating(int rating) {
		this.rating = rating;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}


	public Long getCodRec() {
		return codRec;
	}


	public void setCodRec(Long codRec) {
		this.codRec = codRec;
	}


	public Boolean getFavorite() {
		return favorite;
	}


	public void setFavorite(Boolean favorite) {
		this.favorite = favorite;
	}


	public Users getUser() {
		return user;
	}


	public void setUser(Users user) {
		this.user = user;
	}
}
