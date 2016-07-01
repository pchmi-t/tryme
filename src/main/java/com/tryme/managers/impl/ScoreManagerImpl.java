package com.tryme.managers.impl;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.TreeSet;

import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;

import com.tryme.core.Session;
import com.tryme.framework.bean.Account;
import com.tryme.framework.bean.Score;
import com.tryme.framework.bean.TakenQuiz;
import com.tryme.managers.ScoreManager;

public class ScoreManagerImpl implements ScoreManager {

	@Override
	public TreeSet<Score> get(Date startDate, Date endDate) throws Exception {
		List<TakenQuiz> quizesResults = new ArrayList<>();
		try (Session session = new Session()) {
			Query dateQuery = new Query();
			dateQuery.addCriteria(Criteria.where("dateTaken").gte(startDate).lt(endDate));
			quizesResults = session.openSession().find(dateQuery, TakenQuiz.class);
		}
		
		Map<Account, Integer> userResults = new HashMap<>();
		for (TakenQuiz takenQuiz : quizesResults) {
			Account user = takenQuiz.getUser();
			Integer previousScore = userResults.get(user);
			if (previousScore == null) {
				userResults.put(user, takenQuiz.getScore());
			} else {
				userResults.put(user, previousScore + takenQuiz.getScore());
			}
		}
		
		TreeSet<Score> scores = new TreeSet<>((Score o1, Score o2) -> o2.getTotalScore() -  o1.getTotalScore());
		for (Entry<Account, Integer>  userResult : userResults.entrySet()) {
			Score score = new Score(userResult.getKey(), userResult.getValue());
			scores.add(score);
		}
		return scores;
		
	}

	@Override
	public TakenQuiz save(Account user, int score) throws Exception {
		TakenQuiz takenQuiz = new TakenQuiz(user, score);
		try (Session session = new Session()) {
			session.openSession().insert(takenQuiz);
		}
		return takenQuiz;
	}

}
