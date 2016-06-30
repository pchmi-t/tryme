package com.tryme.ws;

import java.util.Arrays;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.tryme.core.Factory;
import com.tryme.framework.bean.Test;
import com.tryme.managers.TestManager;

@Path("/tests")
@Produces(MediaType.APPLICATION_JSON)
public class TestsResources {
	
	private TestManager testManager = Factory.getInstance().getTestManager();

	@GET
	public Response getTests() throws Exception {
		List<Test> tests = testManager.getAll();
		if (tests.isEmpty()) {
			return Response.status(HttpServletResponse.SC_NOT_FOUND).build();
		}
		return Response.ok(tests).build();
	}
	
	@GET
	@Path("/{id}/")
	public Response getTest(@PathParam("id") String id) throws Exception {
		Test test = testManager.get(id);
		return Response.ok(test).build();
	}
	
	@GET
	@Path("/{subject}/grades/{grade}/")
	public Response getTests(@PathParam("subject") String subject, @PathParam("grade") String grade) throws Exception {
		List<Test> tests = testManager.get(subject, grade);
		if (tests.isEmpty()) {
			return Response.status(HttpServletResponse.SC_NOT_FOUND).build();
		}
		return Response.ok(tests).build();
	}

}
