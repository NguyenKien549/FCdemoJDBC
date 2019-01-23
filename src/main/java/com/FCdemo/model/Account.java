package com.FCdemo.model;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
@Scope("user")
public class Account {
	
	private String Name;
	private String Mail;
	private String username;
	private String password;
	
	public Account() {
		super();
	}
	
	public Account(String name, String mail, String username, String password) {
		super();
		Name = name;
		Mail = mail;
		this.username = username;
		this.password = password;
	}

	public final String getName() {
		return Name;
	}
	public final void setName(String name) {
		Name = name;
	}
	public final String getMail() {
		return Mail;
	}
	public final void setMail(String mail) {
		Mail = mail;
	}
	public final String getUsername() {
		return username;
	}
	public final void setUsername(String username) {
		this.username = username;
	}
	public final String getPassword() {
		return password;
	}
	public final void setPassword(String password) {
		this.password = password;
	}
	
	
}
