package com.twoteethreeeight.userservice.Provider;



public class AuthenticationResponse {

	private static final long serialVersionUID = -8091254;
	private String token;
	private String role;
	public AuthenticationResponse(String token,String role) {
		this.token = token;
		this.role=role;
	}
//	public AuthenticationResponse(String token) {
//		this.token = token;
//	}
	public String getToken() {
		return token;
	}
	public void setToken(String token) {
		this.token = token;
	}
	
	public String getRole() {
		return role;
	}
	public void setRole(String role) {
		this.role = role;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	
	
}
