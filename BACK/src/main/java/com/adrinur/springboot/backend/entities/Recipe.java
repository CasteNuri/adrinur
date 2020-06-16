package com.adrinur.springboot.backend.entities;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "codRec")
@Table
@Entity
public class Recipe implements Serializable{
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long codRec;
	
	@Column
	private String title;
	
	@Column(columnDefinition = "text")
	private String image;
	
	@ManyToMany
	@JoinTable(name="recipes_ingredients", joinColumns = @JoinColumn(name = "codRec"), 
	inverseJoinColumns = @JoinColumn(name = "serialNumber"))
	private List<Ingredient> ingredients;

	@Column(columnDefinition = "text")
	private String quantities;
	
	@Column
	private Long time;
	
	@Column
	private String difficulty;
	
	@Column
	private String type;
	
	@Column(columnDefinition = "text")
	private String description;
	
	@Column
	private int rating;
	
	@Column(columnDefinition = "boolean default false")
	private Boolean favorite;
	
	@ManyToOne(fetch=FetchType.LAZY)
		@JoinColumn(name="userId")
	private Users user;

	/**
	 * Constructors
	 */
	public Recipe() {
		
	}
	
	
	
	public Recipe(Long codRec, String title, String image, List<Ingredient> ingredients, String quantities, Long time,
			String difficulty, String type, String description, int rating, Boolean favorite, Users user) {
		super();
		this.codRec = codRec;
		this.title = title;
		this.image = image;
		this.ingredients = ingredients;
		this.quantities = quantities;
		this.time = time;
		this.difficulty = difficulty;
		this.type = type;
		this.description = description;
		this.rating = rating;
		this.favorite = favorite;
		this.user = user;
	}



	/**
	 * Getters && Setters
	 * 
	 */
	public Long getCodRec() {
		return codRec;
	}

	public void setCodRec(Long codRec) {
		this.codRec = codRec;
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

	public List<Ingredient> getIngredients() {
		return ingredients;
	}

	public void setIngredients(List<Ingredient> ingredients) {
		this.ingredients = ingredients;
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

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
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
