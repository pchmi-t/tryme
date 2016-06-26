package com.tryme.framework.criteria;

import org.springframework.data.mongodb.core.query.Query;

public interface BaseCriteria {
	
	/**
	 * Get the query to search by.
	 * @return the query to search by
	 */
	Query getQuery();
}
