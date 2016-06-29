package com.tryme.framework;

import java.util.LinkedList;
import java.util.List;

import javax.jws.Oneway;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import org.codehaus.jackson.annotate.JsonProperty;
import org.springframework.beans.factory.annotation.Required;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.PersistenceConstructor;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

/**
 * This class holds all the user information.
 * 
 * @author Hristo
 *
 */
@XmlAccessorType(XmlAccessType.FIELD)
public class UserInformation {

	@Field
	private String fullName = "";
	
	@Field
	private Integer age;
	
	@Field
	private String description = "Type your description here ... ";
	
	@Field
	private Badges badges;
	
	@Field
	private String avatar;

	/** The default constructor. */
	public UserInformation() {
	}
	
	public String getFullName() {
		return fullName;
	}
	
	public void setFullName(String fullName) {
		this.fullName = fullName;
	}
	
	public Integer getAge() {
		return age;
	}
	
	public void setAge(Integer age) {
		this.age = age;
	}
	
	public String getDescription() {
		return description;
	}
	
	public void setDescription(String description) {
		this.description = description;
	}
	
	public Badges getBadges() {
		return badges;
	}
	public void setBadges(Badges badges) {
		this.badges = badges;
	}

	public String getAvatar() {
		return this.avatar;
	}
	
	public void setAvatar(String avatar) {
		this.avatar = avatar;
	}
}
