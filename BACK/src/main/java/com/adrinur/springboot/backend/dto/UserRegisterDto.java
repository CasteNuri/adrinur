package com.adrinur.springboot.backend.dto;

import java.io.Serializable;

public class UserRegisterDto implements Serializable{

	private String userName;
	private String email;
	private String password;
	private String avatar;
	
	
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getAvatar() {
		return avatar;
	}
	public void setAvatar(String avatar) {
		this.avatar = avatar;
	}
	public String getUserName() {
		return userName;
	}
	public void setName(String userName) {
		this.userName = userName;
	}
}
