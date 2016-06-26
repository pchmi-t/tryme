package com.tryme.core.exceptions;

public class NoSuchAccountException extends Exception {
	
	private String errorMessage;
	
	public NoSuchAccountException(String errorMessage) {
		super(errorMessage);
	}

	public String getErrorMessage() {
		return errorMessage;
	}

	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}
	
}
