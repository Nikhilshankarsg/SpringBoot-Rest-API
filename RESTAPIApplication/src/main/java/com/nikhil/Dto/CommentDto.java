package com.nikhil.Dto;


public class CommentDto {
	
	private long id;
	
	private String username;
	
	private String email;
	
	private String body;

	public long getId() {
		return id;
	}

	public String getUsername() {
		return username;
	}

	public String getEmail() {
		return email;
	}

	public String getBody() {
		return body;
	}

	public void setId(long id) {
		this.id = id;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public void setBody(String body) {
		this.body = body;
	}

	
}