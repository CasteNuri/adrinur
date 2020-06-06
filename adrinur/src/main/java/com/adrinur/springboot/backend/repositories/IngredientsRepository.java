package com.adrinur.springboot.backend.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.adrinur.springboot.backend.entities.Ingredients;

public interface IngredientsRepository extends JpaRepository<Ingredients, Long>{

}
