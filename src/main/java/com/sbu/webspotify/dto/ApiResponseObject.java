package com.sbu.webspotify.dto;

import java.io.Serializable;

public class ApiResponseObject implements Serializable {
	private static final long serialVersionUID = 1L;
    
    private boolean success;
    private String  message;
    private Object  responseData;

    public ApiResponseObject() { 
    }

    public ApiResponseObject(boolean success, String message,
                              Object responseData) {
        this.success = success;
        this.message = message;
        this.responseData = responseData;
    }

	public boolean isSuccess() {
		return success;
	}

	public void setSuccess(boolean success) {
		this.success = success;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public Object getResponseData() {
		return responseData;
    }
    
	public void setResponseData(Object responseData) {
		this.responseData = responseData;
	}

}