package com.tryme.framework.bean;

import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

import org.bson.types.ObjectId;
import org.codehaus.jackson.annotate.JsonProperty;
import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.PersistenceConstructor;
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
	private UserInformation userInformation;
	
	/**
	 * The default constructor.
	 */
	public Account() {
	}

	@PersistenceConstructor
	public Account(String id, String username, String email, String password) {
		this.id = id;
		this.username = username;
		this.email = email;
		this.password = password;
		this.userInformation = new UserInformation();
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
	@NotBlank(message = "The username can not be empty nor empty.")
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
	@Email
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
	@NotBlank(message = "The password can not be empty nor empty.")
	public void setPassword(String password) {
		this.password = password;
	}

	public UserInformation getUserInformation() {
		return userInformation;
	}

	public void setUserInformation(UserInformation userInformation) {
		this.userInformation = userInformation;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((username == null) ? 0 : username.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Account other = (Account) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (username == null) {
			if (other.username != null)
				return false;
		} else if (!username.equals(other.username))
			return false;
		return true;
	}
	
	
	
	
}
