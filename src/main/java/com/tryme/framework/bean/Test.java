package com.tryme.framework.bean;

import java.util.Arrays;
import java.util.List;

import javax.validation.constraints.Pattern;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

public class Test {

	@Id
	private String id;

	@Field
	private List<Question> questions;

	@Field
	private String subject;

	@Field
	private String grade;

	@Field
	private String title;
	
	public Test(String title, String subject, String grade, Question... questions) {
		this.questions = Arrays.asList(questions);
		this.subject = subject;
		this.grade = grade;
		this.title = title;
	}

	public Test() {
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public List<Question> getQuestions() {
		return questions;
	}

	public void setQuestions(List<Question> questions) {
		this.questions = questions;
	}

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public String getGrade() {
		return grade;
	}

	public void setGrade(String grade) {
		this.grade = grade;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

}
