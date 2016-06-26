package com.tryme.core.exceptions;

public class InvalidAccountException  extends Exception {

	/**
	 * The error message.
	 */
	private String errorMessage;

	/**
	 * Build the new InvalidAccountException based on error message.
	 * 
	 * @param errorMessage the error message.
	 */
	public InvalidAccountException(String errorMessage) {
		super(errorMessage);
		this.errorMessage = errorMessage;
	}

	/**
	 * Gets the error message.
	 * 
	 * @return the error message.
	 */
	public String getErrorMessage() {
		return errorMessage;
	}

	/**
	 * Sets the error message.
	 * 
	 * @param errorMessage the error message.
	 */
	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}
}
