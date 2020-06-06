package com.adrinur.springboot.backend.dto;

import java.util.List;

import com.adrinur.springboot.backend.entities.Receipts;

public class UsersDto {

	private Long id;
	private String userName;
	private String avatar;
	private List<Receipts> receipts;
	
	/**
	 * Getters & Setters | DTO
	 * @return
	 */
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getAvatar() {
		return avatar;
	}
	public void setAvatar(String avatar) {
		this.avatar = avatar;
	}
	public List<Receipts> getReceipts() {
		return receipts;
	}
	public void setReceipts(List<Receipts> receipts) {
		this.receipts = receipts;
	}
	
	
}
