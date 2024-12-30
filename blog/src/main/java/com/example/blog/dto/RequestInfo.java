package com.example.blog.dto;

public class RequestInfo {
	private String token;
	private String timestamp;

	public RequestInfo() {
	}

	public RequestInfo(String token, String timestamp) {
		this.token = token;
		this.timestamp = timestamp;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public String getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(String timestamp) {
		this.timestamp = timestamp;
	}
}
