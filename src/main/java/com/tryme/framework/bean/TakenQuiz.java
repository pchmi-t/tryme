package com.tryme.framework.bean;

import java.time.LocalDate;
import java.util.Date;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Document
public class TakenQuiz {

	@Id
	private String id;

	@Field
	private Account user;

	@Field
	private Date dateTaken;

	@Field
	private int score;
	

	public TakenQuiz(Account user, int score) {
		this.user = user;
		this.score = score;
		this.dateTaken = new Date();
	}

	public TakenQuiz() {
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Account getUser() {
		return user;
	}

	public void setUser(Account user) {
		this.user = user;
	}

	public Date getDateTaken() {
		return dateTaken;
	}

	public void setDateTaken(Date dateTaken) {
		this.dateTaken = dateTaken;
	}

	public int getScore() {
		return score;
	}

	public void setScore(int score) {
		this.score = score;
	}

}
