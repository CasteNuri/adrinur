package com.adrinur.springboot.backend.utils;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

import org.springframework.stereotype.Component;

@Component("securityUtils")
public class SecurityUtils {

	
	
	public String encodePassword(String pass) throws NoSuchAlgorithmException {
		MessageDigest digest = MessageDigest.getInstance("SHA-256");
		byte[] hash = digest.digest(pass.getBytes(StandardCharsets.UTF_8));
		String encodedPass = Base64.getEncoder().encodeToString(hash);
		return encodedPass;
	}
}
