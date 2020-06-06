package com.adrinur.springboot.backend.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.adrinur.springboot.backend.entities.Users;
import com.adrinur.springboot.backend.repositories.UsersRepository;

@Service
public class UsersServicesImpl implements UsersServices{

	
	@Autowired
	UsersRepository userRepository;
	
	@Override
	public List<Users> getAllUsers() {
		return userRepository.findAll();
	}

	@Override
	public Users getUser(Long id) {
		return userRepository.findById(id).get();
	}

	@Override
	public void createUser(Users user) {
		userRepository.save(user);
	}

	@Override
	public void deleteUser(Long id) {
		userRepository.deleteById(id);
	}

	
}
