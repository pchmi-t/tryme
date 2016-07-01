package com.tryme.managers;

import java.util.Date;
import java.util.TreeSet;

import com.tryme.framework.bean.Account;
import com.tryme.framework.bean.Score;
import com.tryme.framework.bean.TakenQuiz;

public interface ScoreManager {
	
	public TakenQuiz save(Account user, int score) throws Exception;
	
	public TreeSet<Score> get(Date startDate, Date endDate) throws Exception;

}
