package com.adrinur.springboot.backend.utils;

import java.security.NoSuchAlgorithmException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

@Component("securityUtils")
public class SecurityUtils {

	@Autowired
	private BCryptPasswordEncoder bCrypt;
	
	@Bean
	public BCryptPasswordEncoder bCryptPasswordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
	public String encodePassword(String pass) throws NoSuchAlgorithmException {
		String encodedPass = bCrypt.encode(pass);
		return encodedPass;
	}
}
