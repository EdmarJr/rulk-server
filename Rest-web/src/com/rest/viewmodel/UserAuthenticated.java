package com.rest.viewmodel;

public class UserAuthenticated {
	private String token;
	private String userEmail;
	private String[] roles;

	public String getToken() {
		return token;
	}

	public UserAuthenticated setToken(String token) {
		this.token = token;
		return this;
	}

	public String getUserEmail() {
		return userEmail;
	}

	public UserAuthenticated setUserEmail(String userEmail) {
		this.userEmail = userEmail;
		return this;
	}

	public String[] getRoles() {
		return roles;
	}

	public UserAuthenticated setRoles(String[] roles) {
		this.roles = roles;
		return this;
	}


}
