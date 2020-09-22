package com.kattyolv.login.system.api.model;

public class UserModel {

	private String name;
	private String email;
	private String password;
	private int id;
	
	public UserModel() {
		
	}
	
	public UserModel(String name, String email, String password, int id) {
		this.name = name;
		this.email = email;
		this.password = password;
		this.id = id;
	}
	
	public String getName() {
		return this.name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public String getEmail() {
		return this.email;
	}
	
	public void setEmail(String email) {
		this.email = email;
	}
	
	public String getPassword() {
		return this.password;
	}
	
	public void setPassword(String password) {
		this.password = password;
	}
	
	public int getId() {
		return this.id;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	public String toString() {
		return this.name + " " + this.email + " " + this.id;
	}
}
