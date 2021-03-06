package com.czhang.cpms.model.domain;

import com.fasterxml.jackson.annotation.JsonProperty;

public class User {

	@JsonProperty("email")
    private String username;
	
	@JsonProperty("password")
    private String password;

	@JsonProperty("firstName")
    private String firstName;

	@JsonProperty("lastName")
    private String lastName;
	
	public User(){}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
}
