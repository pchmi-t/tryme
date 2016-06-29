package com.tryme.framework;

import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;

import org.springframework.data.mongodb.core.mapping.Field;

@XmlAccessorType(XmlAccessType.FIELD)
public class Badges {

	@Field
	private List<Badge> badges;
	
	/** The default constructor. */
	public Badges() {
	}

	public List<Badge> getBadges() {
		return badges;
	}

	public void setBadges(List<Badge> badges) {
		this.badges = badges;
	}
	
}
