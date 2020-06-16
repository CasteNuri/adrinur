package com.adrinur.springboot.backend.restcontrollers;

import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.adrinur.springboot.backend.dto.UserLoginDto;
import com.adrinur.springboot.backend.dto.UserRegisterDto;
import com.adrinur.springboot.backend.entities.Users;
import com.adrinur.springboot.backend.security.SecurityConstants;
import com.adrinur.springboot.backend.services.UserServices;
import com.adrinur.springboot.backend.utils.SecurityUtils;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

//@CrossOrigin(origins= {"http://localhost:4200"})
@RestController
@RequestMapping("/auth")
public class AuthController {

	@Autowired
	private UserServices usersServices;
	
	@Autowired
	SecurityUtils security;
	
	@PostMapping("/login")
	public ResponseEntity<Map<String,Object>> login(@RequestBody UserLoginDto userLogin) throws NoSuchAlgorithmException {
		Map<String, Object> resp = new HashMap<>();
		
		Users user = usersServices.login(userLogin.getEmail(), userLogin.getPassword());
		
		if (user != null) {
			resp.put("accessToken", getToken(user));
			return ResponseEntity.ok().body(resp);
		} else {
			resp.put("user", userLogin);
			resp.put("pass", security.encodePassword(userLogin.getPassword()));
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(resp);
		}
	}
	
	
	// In order to create a new user and add it to the ddbb
	@PostMapping("/register")
	public ResponseEntity<Void> register(@RequestBody UserRegisterDto userDto) throws NoSuchAlgorithmException{
		usersServices.register(userDto);
		return ResponseEntity.status(HttpStatus.CREATED).body(null);
	}
	
	
	@GetMapping("/validate")
	public void validateToken() {}
	
	
	private String getToken(Users user) {
		Map<String, Object> data = new HashMap<String, Object> ();
		
		data.put("id", user.getId());
		data.put("email", user.getEmail());
		data.put("authorities", Arrays.asList("ROLE_USER"));
		
		String token = Jwts.builder().setId("BACK")
				.setSubject(user.getUserName()).addClaims(data)
				.setIssuedAt(new Date(System.currentTimeMillis()))
				.setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60 *10))
				.signWith(SignatureAlgorithm.HS512, SecurityConstants.SECRET_KEY).compact();
		
		return token;
	}
	
}
