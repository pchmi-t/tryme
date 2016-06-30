package com.tryme.ws;

import java.time.LocalDate;
import java.time.Month;
import java.time.ZoneId;
import java.util.Date;
import java.util.TreeSet;

import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.springframework.data.mongodb.core.mapping.Field;

import com.tryme.core.Factory;
import com.tryme.core.exceptions.NoSuchAccountException;
import com.tryme.framework.bean.Account;
import com.tryme.framework.bean.Score;
import com.tryme.framework.bean.TakenQuiz;
import com.tryme.framework.criteria.AccountCriterion;
import com.tryme.managers.AccountManager;
import com.tryme.managers.ScoreManager;

@Path("/scores")
@Produces(MediaType.APPLICATION_JSON)
public class TakenTestResource {

	private ScoreManager scoreManager = Factory.getInstance().getScoreManager();
	private AccountManager accountManager = Factory.getInstance().getAccountManager();

	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	public Response createScoreEntry(ScoreBean score) throws Exception {
		AccountCriterion criterion = accountManager.getAccountCriterion();
		criterion.username(score.username);
		try {
			Account account = accountManager.getAccount(criterion);
			TakenQuiz saved = scoreManager.save(account, score.score);
			return Response.status(HttpServletResponse.SC_CREATED).entity(saved).build();
		} catch (NoSuchAccountException e) {
			return Response.status(HttpServletResponse.SC_NOT_FOUND).build();
		}
	}

	@GET
	public Response getTestResults() throws Exception {
		Date startDate = Date.from(LocalDate.of(2016, Month.JULY, 1).atStartOfDay(ZoneId.systemDefault()).toInstant());
		TreeSet<Score> scores = scoreManager.get(startDate, new Date());
		return Response.ok(scores).build();
	}

	public static class ScoreBean {
		@Field
		String username;
		@Field
		int score;

		public String getUsername() {
			return username;
		}

		public void setUsername(String username) {
			this.username = username;
		}

		public int getScore() {
			return score;
		}

		public void setScore(int score) {
			this.score = score;
		}

	}

}
