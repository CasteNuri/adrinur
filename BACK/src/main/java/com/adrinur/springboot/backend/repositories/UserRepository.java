package com.adrinur.springboot.backend.repositories;


import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.adrinur.springboot.backend.entities.Users;

@Repository
public interface UserRepository extends JpaRepository<Users, Long>{

	@Query(value = "SELECT u FROM Users u WHERE u.username = ?1 AND u.password = ?2", nativeQuery = true)
	Optional<Users> matchUserDataBase (@Param("username") String userName, @Param("password") String password);
}
