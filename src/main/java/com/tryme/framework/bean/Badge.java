package com.tryme.framework.bean;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import org.codehaus.jackson.annotate.JsonProperty;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Document
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class Badge {

	@Transient
	public static final String BEGGINER = "begginer";
	
	@Id
	private String badgeId;

	@Field
	private String name = BEGGINER;
	
	public Badge() {
		
	}
	
	public Badge(String badgeId, String name) {
		this.badgeId = badgeId;
		this.name = name;
	}

	public void setBadge(String badge) {
		this.name = badge;
	}
	
	public String getBadge() {
		return this.name;
	}
}
