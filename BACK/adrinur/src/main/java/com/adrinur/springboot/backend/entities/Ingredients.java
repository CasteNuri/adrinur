package com.adrinur.springboot.backend.entities;

import java.io.Serializable;
import java.util.List;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;




@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "serialNumber")
@Table
@Entity
public class Ingredients implements Serializable{

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long serialNumber;
	
	@Column
	private String name;
	
	@ManyToMany(mappedBy = "ingredients")
	private List<Recipes> recipes;

	
	/**
	 * Getters & Setters | Ingredients
	 * @return
	 */
	
	public Long getSerialNumber() {
		return serialNumber;
	}

	public void setSerialNumber(Long serialNumber) {
		this.serialNumber = serialNumber;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	
	
	
}
