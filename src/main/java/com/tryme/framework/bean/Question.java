package com.tryme.framework.bean;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.data.mongodb.core.mapping.Field;

public class Question {
	
	@Field
	private String text;

	@Field
	private List<Answer> answers = new ArrayList<>();
	

	public Question(String text, Answer... answers) {
		this.answers = Arrays.asList(answers);
		this.text = text;
	}

	public Question() {
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public List<Answer> getAnswers() {
		return answers;
	}

	public void setAnswers(List<Answer> answers) {
		this.answers = answers;
	}
	
	public void addAnswer(Answer answer) {
		this.answers.add(answer);
	}
}
