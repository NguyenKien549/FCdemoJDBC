package com.FCdemo.model;

import org.springframework.stereotype.Component;

@Component
public class QLRole {
	private String username;
	private String Role;
	
	
	public QLRole(String username, String role) {
		super();
		this.username = username;
		Role = role;
	}
	public final String getRole() {
		return Role;
	}
	public final void setRole(String role) {
		Role = role;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	
}
