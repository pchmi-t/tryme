package com.tryme.core.exceptions;

import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;


public class WSBaseException extends WebApplicationException {

	/** The serial. */
	private static final long serialVersionUID = 1L;

	/** The error message. */
	private String errorMessage;

	/**
	 * Build the new base web application exception.
	 * 
	 * @param code the status code of the response
	 * @param errorMessage the error message
	 */
	public WSBaseException(Status code, String errorMessage) {
		super(Response.status(code).entity(errorMessage).build());
		this.errorMessage = errorMessage;
	}

	/** The default constructor. */
	public WSBaseException() {
	}

	/**
	 * Build the new base web application exception.
	 * The response code will be <b>400 BAD_REQUEST</b>.
	 * 
	 * @param errorMessage the error message.
	 */
	public WSBaseException(String errorMessage) {
		this(Status.BAD_REQUEST, errorMessage);
	}

	/**
	 * Set the error message.
	 * 
	 * @param errorMessage the error message.
	 */
	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}


	/**
	 * Get the error message.
	 * 
	 * @return the error message.
	 */
	public String getErrorMessage() {
		return this.errorMessage;
	}
}
