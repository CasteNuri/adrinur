package com.adrinur.springboot.backend.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.adrinur.springboot.backend.entities.Recipes;


@Repository
public interface RecipesRepository  extends JpaRepository<Recipes, Long>{

}
