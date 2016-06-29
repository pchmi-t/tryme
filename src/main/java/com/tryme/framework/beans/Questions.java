package com.tryme.framework.beans;

import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;

import org.springframework.data.mongodb.core.mapping.Field;

import com.tryme.framework.bean.Question;

@XmlAccessorType(XmlAccessType.FIELD)
public class Questions {

	@Field
	private List<Question> questions;
	
	/** The default constructor. */
	public Questions() {
	}

	public List<Question> getQuestions() {
		return questions;
	}

	public void setQuestions(List<Question> questions) {
		this.questions = questions;
	}
	
	
}
