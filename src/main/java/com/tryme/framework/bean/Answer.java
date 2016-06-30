package com.tryme.framework.bean;

import javax.validation.constraints.Pattern;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;

import org.springframework.beans.factory.annotation.Required;
import org.springframework.data.mongodb.core.mapping.Field;

@XmlAccessorType(XmlAccessType.FIELD)
public class Answer {
	
	@Field
	private String content;
	 
	@Field
	private Boolean correct;
	
	public Answer() {	
	}

	public Answer(String content, Boolean correct) {
		setContent(content);
		setCorrect(correct);
	}
	
	public void setContent(String content) {
		this.content = content;
	}
	
	public String getContent() {
		return this.content;
	}
	
	public void setCorrect(Boolean correct) {
		this.correct = correct;
	}
	
	public Boolean getCorrect() {
		return this.correct;
	}
}
