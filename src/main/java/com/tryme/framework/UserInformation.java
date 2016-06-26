package com.tryme.framework;

import java.util.LinkedList;
import java.util.List;

import javax.jws.Oneway;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import org.codehaus.jackson.annotate.JsonProperty;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

/**
 * This class holds all the user information.
 * 
 * @author Hristo
 *
 */
@Document
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class UserInformation {
	
	@Id
	private String id;
	
	@Field
	@DBRef
	private Account account;
	
	@Field
	private String fullName = "";
	
	@Field
	private Integer age;
	
	@Field
	private String description = "Type your description here ... ";
	
	@Field
	@DBRef
	private List<Badge> badges;
	
	@Field
	private String avatar;

	public UserInformation(Account account) {
		this.account = account;
	}
	
	public UserInformation(String id, String fullName, Integer age, 
			String description, List<Badge> badges, String avatar) {

		super();
		this.id = id;
		this.fullName = fullName;
		this.age = age;
		this.description = description;
		this.badges = new LinkedList<>();
		this.badges.add(new Badge());
		this.avatar = avatar;
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
	public List<Badge> getBadges() {
		return badges;
	}
	public void setBadges(List<Badge> badges) {
		this.badges = badges;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}
	
	public String getAvatar() {
		return this.avatar;
	}
	
	public void setAvatar(String avatar) {
		this.avatar = avatar;
	}

	public Account getAccount() {
		return account;
	}

	public void setAccount(Account account) {
		this.account = account;
	}

}
