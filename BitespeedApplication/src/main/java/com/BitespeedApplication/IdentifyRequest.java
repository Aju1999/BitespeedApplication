package com.BitespeedApplication;

public class IdentifyRequest {
	private String email;
	private String phoneNumber;

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public IdentifyRequest(String email, String phoneNumber) {
		super();
		this.email = email;
		this.phoneNumber = phoneNumber;
	}

	public IdentifyRequest() {
		super();
		// TODO Auto-generated constructor stub
	}

}