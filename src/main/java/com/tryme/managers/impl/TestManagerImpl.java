package com.tryme.managers.impl;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import static org.springframework.data.mongodb.core.query.Criteria.where;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Query;

import com.tryme.constants.Entity;
import com.tryme.core.Session;
import com.tryme.framework.bean.Account;
import com.tryme.framework.bean.Test;
import com.tryme.managers.TestManager;

public class TestManagerImpl implements TestManager {

	@Override
	public void save(Test test) throws Exception {
		try (Session session = new Session()) {
			session.openSession().save(test);
		}
	}

	@Override
	public Test get(String id) throws Exception {
		try (Session session = new Session()) {
			MongoOperations mongoOps = session.openSession();
			return mongoOps.findOne(new Query(where("id").is(id)), Test.class);
		}
	}

	@Override
	public List<Test> get(String subject, String grade) throws Exception {
		try (Session session = new Session()) {
			MongoOperations mongoOps = session.openSession();
			return mongoOps.find(new Query(where("subject").is(subject).and("grade").is(grade)), Test.class);
		}
	}

	@Override
	public List<Test> getAll() throws Exception {
		try (Session session = new Session()) {
			MongoOperations mongoOps = session.openSession();
			return mongoOps.findAll(Test.class);
		}
	}

}
