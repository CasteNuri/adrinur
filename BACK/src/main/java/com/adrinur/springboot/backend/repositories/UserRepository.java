package com.adrinur.springboot.backend.repositories;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.adrinur.springboot.backend.entities.Users;

@Repository
public interface UserRepository extends JpaRepository<Users, Long>{

	@Query( "SELECT u FROM Users u WHERE u.userName = ?1")
	public Users findUserByUserName (String userName);
	
	@Query("SELECT u FROM Users u WHERE u.email = ?1 AND u.password = ?2")
	public Users login (String email, String password);
}
