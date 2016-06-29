package com.tryme.framework.criteria;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.core.query.Query;

/**
 * An account criteria to search for an accounts.
 * 
 * @author Hristo
 *
 */
public interface AccountCriterion extends BaseCriteria {

	/**
	 * Add account 'id' criteria to search.
	 * 
	 * @param objectId the account id
	 * @return the account criteria
	 */
	AccountCriterion id(String objectId);
	
	/**
	 * Add account 'username' criteria to search.
	 * 
	 * @param username the account username
	 * @return the account criteria
	 */
	AccountCriterion username(String username);
	
	/**
	 * Add account 'email' criteria to search.
	 * 
	 * @param email the account email
	 * @return the account criteria
	 */
	AccountCriterion email(String email);
	
}
