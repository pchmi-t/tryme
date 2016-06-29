package com.tryme.framework.bean;

/**
 * The current status for question.
 * 
 */
public enum Status {

	PENDING("pending"),
	ACCEPT("accept"),
	REJECT("reject");
	
	private String status;
	
	Status(String status) {
		this.status = status;
	}
	
	public String getStatus() {
		return this.status;
	}
}
