package com.tryme.framework;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

import org.codehaus.jackson.annotate.JsonProperty;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

/**
 * The Account representation
 *
 */
@Document
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class Account {

	/**
	 * The account id.
	 */
	@Id
	private String id;
	
	/**
	 * The account username.
	 */
	@Field
	private String username;
	
	/**
	 * The account email.
	 */
	@Field
	private String email;

	/**
	 * The account password.
	 */
	@Field
	private String password;

	@Field
	@XmlTransient
	private Boolean active;

	/**
	 * The default constructor.
	 */
	public Account() {
	}

	public Account(String id, String username, String email, String password) {
		this.id = id;
		this.username = username;
		this.email = email;
		this.password = password;
		this.userInformation = new UserInformation(this);
	}

	/**
	 * Get the account's first name.
	 * 
	 * @return the account first name.
	 */
	public String getUsername() {
		return username;
	}

	/**
	 * Set the username
	 * 
	 * @param username the username
	 */
	public void setUsername(String username) {
		this.username = username;
	}

	/**
	 * The user's email.
	 * 
	 * @return the user email
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * Set the user's email.
	 * 
	 * @param email the email to be set
	 */
	public void setEmail(String email) {
		this.email = email;
	}

	/**
	 * Set the user id.
	 * 
	 * @param id the user id
	 */
	public void setId(String id) {
		this.id = id;
	}

	/**
	 * Get the user id.
	 * 
	 * @return the user's id
	 */
	public String getId() {
		return this.id;
	}

	/**
	 * Get the user's password.
	 * 
	 * @return the user's password
	 */
	public String getPassword() {
		return this.password;
	}

	/**
	 * Set the user's password.
	 * 
	 * @param password the user's password
	 */
	public void setPassword(String password) {
		this.password = password;
	}

	public Boolean getActive() {
		return this.active;
	}
	
	public void setActive(Boolean active) {
		this.active = active;
	}
}
