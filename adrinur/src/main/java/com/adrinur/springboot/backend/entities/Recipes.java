package com.adrinur.springboot.backend.entities;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;





@Table
@Entity
public class Recipes implements Serializable{
	
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long codRec;
	
	@Column
	private String title;
	
	@Column
	private String image;
	
	@ManyToMany
		@JoinTable(name="recipes_ingredients", joinColumns = @JoinColumn(name = "codRec"), inverseJoinColumns = @JoinColumn(name = "serialNumber"))
	private List<Ingredients> ingredients;
	
	@Column
	private String quantities;
	
	@Column
	private String time;
	
	@Column
	private String dificulty;
	
	@Column
	private String description;
	
	@Column
	private String type;
	
	@Column
	private int rating;
	
	@Column
	private boolean favorite;
	
	@ManyToOne
	private Users user;

	
	
	/**
	 * Getters & Setters of Receipts
	 * @return
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

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
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

	public List<Ingredients> getIngredients() {
		return ingredients;
	}

	public void setIngredients(List<Ingredients> ingredients) {
		this.ingredients = ingredients;
	}

	public String getQuantities() {
		return quantities;
	}

	public void setQuantities(String quantities) {
		this.quantities = quantities;
	}

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}

	public String getDificulty() {
		return dificulty;
	}

	public void setDificulty(String dificulty) {
		this.dificulty = dificulty;
	}

	public Users getUser() {
		return user;
	}

	public void setUser(Users user) {
		this.user = user;
	}
	

	
	
}
