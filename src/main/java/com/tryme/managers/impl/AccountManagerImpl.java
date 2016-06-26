package com.tryme.managers.impl;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import javax.ws.rs.core.Response.Status;

import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;

import com.mongodb.WriteResult;
import com.tryme.constants.CoreConstants;
import com.tryme.core.exceptions.InvalidAccountException;
import com.tryme.core.exceptions.NoSuchAccountException;
import com.tryme.core.exceptions.WSBaseException;
import com.tryme.core.utils.PasswordService;
import com.tryme.core.utils.Session;
import com.tryme.framework.Account;
import com.tryme.framework.UserInformation;
import com.tryme.framework.criteria.AccountCriterion;
import com.tryme.framework.validation.AccountValidationUtils;
import com.tryme.managers.AccountManager;

/**
 *  The Account manager implementation.
 *
 */
public class AccountManagerImpl implements AccountManager {

	@Override
	public void updateAccount(Account account) throws Exception {
		try (Session session = new Session()) {
			Account domainAccount = session
					.openSession()
					.findById(account.getId(), Account.class);
			if (domainAccount == null) {
				throw new NoSuchAccountException("The account with the specifyed "
						+ "id does not exist!");
			} else {
				Account newAccount = AccountValidationUtils.
						mergeToDomainAccount(domainAccount, account);
				session.openSession().save(newAccount);
			}
		}
	}

	@Override
	public void addAccount(Account account) throws InvalidAccountException {
		try (Session session = new Session()) {
			if (AccountValidationUtils.validateUsername(account) && 
					AccountValidationUtils.validateEmail(account.getEmail())) {
				account.setPassword(PasswordService
						.getInstance()
						.encrypt(account.getPassword()));
				session.openSession().insert(account, CoreConstants.ACCOUNT);
			} else {
				throw new InvalidAccountException("The account can not be created. "
						+ "Check the username and e-mail.");
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw new InvalidAccountException("An error occure while adding the account.");
		}
	}

	@Override
	public List<Account> getAccounts(AccountCriterion criterion, int limit, int offset) {
		try (Session session = new Session()) {
			List<Account> allAccounts = 
					session.openSession().findAll(Account.class, CoreConstants.ACCOUNT);
			List<Account> result = new LinkedList<>();
			for (Iterator iterator = allAccounts.iterator(); iterator.hasNext();) {
				if (limit > 0) {
					result.add((Account) iterator.next());
					limit--;
				} else {
					continue;
				}
			}
			return result;
		} catch (Exception e) {
			throw new WSBaseException("The specified account does not exist.");		
		}
	}

	@Override
	public AccountCriterion getAccountCriterion() {
		return new AccountCriterionImpl();
	}

	/**
	 * The implementation of an account criteria.
	 */
	public final class AccountCriterionImpl implements AccountCriterion {

		/**
		 * An account query to build.
		 */
		private Query accountQuery = new Query();

		@Override
		public AccountCriterion id(String id) {
			accountQuery.addCriteria(Criteria.where("id").is(id));
			return this;
		}

		@Override
		public AccountCriterion username(String username) {
			accountQuery.addCriteria(Criteria.where("username").is(username));
			return this;
		}

		@Override
		public AccountCriterion email(String email) {
			accountQuery.addCriteria(Criteria.where("email").is(email));
			return this;
		}

		@Override
		public Query getQuery() {
			return this.accountQuery;
		}

	}

	@Override
	public Account getAccount(AccountCriterion criterion) throws Exception {
		try(Session session = new Session()) {
			Account account = (Account) session
					.openSession()
					.findOne(criterion.getQuery(), Account.class, CoreConstants.ACCOUNT);
			return account;
		}
	}

}
