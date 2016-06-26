package com.tryme.core.utils;

import java.net.UnknownHostException;

import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.MongoTemplate;

import com.mongodb.MongoClient;
import com.tryme.constants.CoreConstants;

public class Session implements AutoCloseable {
	
	private MongoOperations session;
	
	private MongoClient connection;
	
	/**
	 * Open the new db session.
	 * 
	 * @return the opened db session
	 * @throws UnknownHostException in case the unknown or unreachable host is specified
	 */
	public MongoOperations openSession() throws UnknownHostException {
		this.connection = new MongoClient(CoreConstants.DB_HOST, CoreConstants.DB_PORT);
		this.session = new MongoTemplate(connection, CoreConstants.TRYME_DB_NAME);
		return this.session;
	}
	
	/**
	 * Close the opened session.
	 * 
	 */
	public void closeSession() {
		if(connection != null) {
			connection.close();
		}
	}

	@Override
	public void close() throws Exception {
		closeSession();
	}

}
