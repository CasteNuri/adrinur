package com.adrinur.springboot.backend.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.adrinur.springboot.backend.entities.Recipe;

@Repository
public interface RecipeRepository extends JpaRepository<Recipe, Long>{

	List<Recipe> findAllByType(String type);
	

}
