package com.adrinur.springboot.backend.repositories;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.adrinur.springboot.backend.entities.Users;

@Repository
public interface UserRepository extends JpaRepository<Users, Long>{

	@Query( "SELECT u FROM Users u WHERE u.userName = ?1")
	public Users findUserByUserName (String userName);
	
	@Query(value = "SELECT u FROM Users u WHERE u.email = ?1 AND u.password = ?2", nativeQuery = true)
	public Users login (@Param("email") String email, @Param("password") String password);
}
