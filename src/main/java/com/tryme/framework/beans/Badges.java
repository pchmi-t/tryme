package com.tryme.framework.beans;

import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;

import org.springframework.data.mongodb.core.mapping.Field;

import com.tryme.framework.bean.Badge;

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
