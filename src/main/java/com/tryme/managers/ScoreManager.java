package com.tryme.managers;

import java.util.Date;
import java.util.TreeSet;

import com.tryme.framework.bean.Score;

public interface ScoreManager {
	
	public TreeSet<Score> getScores(Date startDate, Date endDate) throws Exception;

}
