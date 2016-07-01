package com.tryme.listeners;

import java.net.UnknownHostException;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.springframework.data.mongodb.core.MongoTemplate;

import com.mongodb.MongoClient;
import com.tryme.constants.CoreConstants;
import com.tryme.core.Session;

public class CleanDBListener implements ServletContextListener {

	@Override
	public void contextDestroyed(ServletContextEvent arg0) {
	}

	@Override
	public void contextInitialized(ServletContextEvent arg0) {
		try {
			MongoClient connection = new MongoClient(CoreConstants.DB_HOST, CoreConstants.DB_PORT);
			MongoTemplate mongoTemplate = new MongoTemplate(connection, CoreConstants.TRYME_DB_NAME);
			mongoTemplate.getDb().dropDatabase();
		} catch (UnknownHostException e) {
			e.printStackTrace();
		}
	}

}
