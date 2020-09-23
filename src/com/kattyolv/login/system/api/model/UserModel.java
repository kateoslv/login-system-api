package com.kattyolv.login.system.api.model;

public class UserModel {

	private String firstName;
	private String lastName;
	private String email;
	private String password;
	private int id;
	
	public UserModel() {
		
	}
	
	public UserModel(String firstName, String lastName, String email, String password, int id) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.password = password;
		this.id = id;
	}
	
	public String getFirstName() {
		return this.firstName;
	}
	
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	
	public String getLastName() {
		return this.lastName;
	}
	
	public void setLastName(String lastName) {
		this.lastName = lastName;
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
		return this.id + " " + this.firstName + " " + this.lastName + " " + this.email;
	}
}
