package com.tryme.managers.impl;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;

import com.mongodb.WriteResult;
import com.tryme.constants.AccountConstants;
import com.tryme.core.exceptions.DuplicateAccountException;
import com.tryme.core.exceptions.IllegalIDFormatException;
import com.tryme.core.exceptions.InvalidAccountException;
import com.tryme.core.exceptions.NoSuchAccountException;
import com.tryme.core.utils.AccountUtils;
import com.tryme.core.utils.PasswordService;
import com.tryme.core.utils.Session;
import com.tryme.framework.Account;
import com.tryme.framework.criteria.AccountCriterion;
import com.tryme.managers.AccountManager;

/**
 * 
 * @author Hristo
 *
 */
public class AccountManagerImpl implements AccountManager {

	@Override
	public Account updateAccount(Account account) throws NoSuchAccountException, IllegalIDFormatException {
		try (Session session = new Session()) {
			Account domainAccount = session.openSession().findById(account.getId(), Account.class);
			if (domainAccount == null) {
				throw new NoSuchAccountException();
			} else {
				
			}
		} catch (Exception e) {
			
		}
		return null;
	}

	@Override
	public void addAccount(Account account) {
		try (Session session = new Session()) {
			//TODO Verify the username and the login name
			account.setPassword(PasswordService.getInstance().encrypt(account.getPassword()));
			session.openSession().insert(account, AccountConstants.ACCOUNT);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public List<Account> getAccounts(AccountCriterion criterion, int limit, int offset) {
		return getAccountsByCriteria(criterion, limit, offset);
	}
	
	private List<Account> getAccountsByCriteria(AccountCriterion criterion, int limit, int offset) {
		try (Session session = new Session()) {
			List<Account> allAccounts = 
					session.openSession().findAll(Account.class, AccountConstants.ACCOUNT);
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
		} catch (Exception ex) {
			ex.printStackTrace();
			//TODO do something
		}
		return null;
	}
	
	@Override
	public AccountCriterion getAccountCriterion() {
		return new AccountCriterionImpl();
	}

	/**
	 * The implementation of an account criteria.
	 * 
	 * @author Hristo
	 *
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
	public Account getAccount(AccountCriterion criterion) {
		try(Session session = new Session()) {
			return (Account) session
					.openSession()
					.findOne(criterion.getQuery(), Account.class, AccountConstants.ACCOUNT);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

}
