package com.tryme.framework.bean;

import java.time.LocalDate;
import java.util.Date;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Field;

public class TakenQuiz {

	@Id
	private String id;

	@Field
	private Account user;

	@Field
	private LocalDate dateTaken;

	@Field
	private int score;

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

	public LocalDate getDateTaken() {
		return dateTaken;
	}

	public void setDateTaken(LocalDate dateTaken) {
		this.dateTaken = dateTaken;
	}

	public int getScore() {
		return score;
	}

	public void setScore(int score) {
		this.score = score;
	}

}
