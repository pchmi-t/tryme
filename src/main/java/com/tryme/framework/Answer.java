package com.tryme.framework;

public class Answer {
	
	private String content;
	
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
