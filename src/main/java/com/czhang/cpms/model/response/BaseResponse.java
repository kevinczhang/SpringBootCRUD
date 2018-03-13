package com.czhang.cpms.model.response;

public class BaseResponse<T> {
	
	private Boolean success;	
	private String message;
	// Application specific data
	private T payload;	

	public BaseResponse(String msg) {
		this.message = msg;
	}
	
	public BaseResponse(){}

	public Boolean getSuccess() {
		return success;
	}

	public void setSuccess(Boolean success) {
		this.success = success;
	}

	public T getPayload() {
		return payload;
	}

	public void setPayload(T payload) {
		this.payload = payload;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}    
}
