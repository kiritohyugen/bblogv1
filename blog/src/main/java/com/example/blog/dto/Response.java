package com.example.blog.dto;

public class Response<T> {

    private ResponseInfo responseInfo;
    private T data;

    public Response() {}

    public Response(ResponseInfo responseInfo, T data) {
        this.responseInfo = responseInfo;
        this.data = data;
    }


    public ResponseInfo getResponseInfo() {
        return responseInfo;
    }

    public void setResponseInfo(ResponseInfo responseInfo) {
        this.responseInfo = responseInfo;
    }
    
    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}