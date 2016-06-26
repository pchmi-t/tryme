package com.tryme.managers;

import java.util.List;

import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;

import com.tryme.core.exceptions.InvalidAccountException;
import com.tryme.core.exceptions.NoSuchAccountException;
import com.tryme.framework.Account;
import com.tryme.framework.criteria.AccountCriterion;

/**
 * Account manager for managing the operations related to the account entity
 * 
 * @author Hristo
 *
 */
public interface AccountManager {
	
	/**
	 * Update the existing account.
	 * 
	 * @param account the account that is about to be update
	 * @return the updated account
	 * @throws NoSuchAccountException in case there is no such account
	 * @throws IllegalIDFormatException in case the id is invalid by some reasons
	 */
	void updateAccount(Account account) throws Exception;
	
	/**
	 * Adding an account.
	 * 
	 * @param account the account to be added
	 * @return the added account
	 * @throws InvalidAccountException in case the account is not valid
	 * @throws DuplicateAccountException in case the user already exist
	 */
	void addAccount(Account account) throws InvalidAccountException;
	
	/**
	 * Get all the accounts specified by the account criteria.
	 * 
	 * @param criterion the account criteria
	 * @return the all the accounts matched the given criteria
	 */
	List<Account> getAccounts(AccountCriterion criterion, int limit, int offset);
	
	/**
	 * Get the account criteria
	 * 
	 * @return the account criteria to search by
	 */
	AccountCriterion getAccountCriterion();

	/**
	 * Get the account represented by his id.
	 * 
	 * @param id the account's id
	 * @return the account with the given id.
	 */
	Account getAccount(AccountCriterion criterion) throws Exception;
	
}
