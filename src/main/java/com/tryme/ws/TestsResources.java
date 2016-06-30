package com.tryme.ws;

import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.tryme.framework.bean.Answer;
import com.tryme.framework.bean.Question;
import com.tryme.framework.bean.Test;

@Path("/tests")
@Produces(MediaType.APPLICATION_JSON)
public class TestsResources {
	
	@GET
	public Response getAllTests() {
		List<Test> tests = new ArrayList<>();
		Test test = new Test();
		test.setCategory("Vectors");
		test.setGrade("12");
		test.setSubject("Math");
		List<Question> questions = new ArrayList<>();
		Question question = new Question();
		question.setText("Какво наричаме вектор?");
		question.addAnswer(new Answer("Отсечка, на която единият край е избран за първи (начало), а другият за втори (край).", true));
		question.addAnswer(new Answer("Отсечка", false));
		questions.add(question);
		test.setQuestions(questions);
		tests.add(test);
		return Response.ok(tests).build();
		
	}

}
