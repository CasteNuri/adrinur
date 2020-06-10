package com.adrinur.springboot.backend.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.adrinur.springboot.backend.entities.Ingredients;

@Repository
public interface IngredientsRepository extends JpaRepository<Ingredients, Long>{

}
