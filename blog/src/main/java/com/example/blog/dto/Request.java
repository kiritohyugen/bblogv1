package com.example.blog.dto;

public class Request<T> {
    private RequestInfo requestInfo;
    private T data;

    public Request() {}

    public Request(RequestInfo requestInfo, T data) {
        this.requestInfo = requestInfo;
        this.data = data;
    }

	public RequestInfo getRequestInfo() {
		return requestInfo;
	}

	public void setRequestInfo(RequestInfo requestInfo) {
		this.requestInfo = requestInfo;
	}

	public T getData() {
		return data;
	}

	public void setData(T data) {
		this.data = data;
	}
    
    

}
