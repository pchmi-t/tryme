package com.tryme.framework.bean;

import java.time.LocalDate;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Field;

public class Score {

	private Account user;

	private int averageScore;

	public Score() {
	}

	public Score(Account user, int averageScore) {
		this.user = user;
		this.averageScore = averageScore;
	}

	public Account getUser() {
		return user;
	}

	public void setUser(Account user) {
		this.user = user;
	}

	public int getTotalScore() {
		return averageScore;
	}

	public void setAverageScore(int averageScore) {
		this.averageScore = averageScore;
	}

}
