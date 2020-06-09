package com.adrinur.springboot.backend.dto;

public class RecipesResumeDto {
	
	private String title;
	private String image;
	private String time;
	private String difficulty;
	private int rating;
	private String type;
	
	
	public RecipesResumeDto(String title, String image, String time, String difficulty, int rating, String type) {
		super();
		this.title = title;
		this.image = image;
		this.time = time;
		this.difficulty = difficulty;
		this.rating = rating;
		this.type = type;
	}
	

	public RecipesResumeDto() {
		
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

	
}
