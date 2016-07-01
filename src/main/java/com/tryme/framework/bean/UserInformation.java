package com.tryme.framework.bean;

import java.util.LinkedList;
import java.util.List;

import javax.jws.Oneway;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
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

import com.tryme.framework.beans.Badges;

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
	@Min(value = 1, message = "The minimum age should be a positive integer larger or egual to 1.")
	@Max(value = 100, message = "The maximum age should be a positive integer smaller or equsl to 100.")
	private Integer age;

	@Field
	private String description = "Type your description here ... ";

	@Field
	private Badges badges;

	@Field
	private String avatar;

	@Field
	private String town;
	
	@Field
	private String school;

	public String getTown() {
		return town;
	}

	public void setTown(String town) {
		this.town = town;
	}

	public String getSchool() {
		return school;
	}

	public void setSchool(String school) {
		this.school = school;
	}

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
