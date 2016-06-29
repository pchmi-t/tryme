package com.tryme.framework;

import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;

import org.springframework.data.mongodb.core.mapping.Field;

@XmlAccessorType(XmlAccessType.FIELD)
public class Answers {

	@Field
	private List<Answer> answers;
	
	/** The default constructor. */
	public Answers() {
	}

	public List<Answer> getAnswers() {
		return answers;
	}

	public void setAnswers(List<Answer> answers) {
		this.answers = answers;
	}	
}
