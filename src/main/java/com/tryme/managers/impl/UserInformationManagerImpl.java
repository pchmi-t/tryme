package com.tryme.managers.impl;

import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;

import com.tryme.framework.Account;
import com.tryme.framework.UserInformation;
import com.tryme.framework.criteria.UserInformationCriterion;
import com.tryme.managers.UserInformationManager;

public class UserInformationManagerImpl implements UserInformationManager {

	@Override
	public void updateUserInformation(UserInformation updateUserInfo) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void createUserInformation(UserInformation userInformation) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public UserInformation getUserInformation(UserInformationCriterion criterion) {
		// TODO Auto-generated method stub
		return null;
	}

	public final class UserInformationCriterionImpl implements UserInformationCriterion {

		/**
		 * An user information query to build.
		 */
		private Query userInfoQuery = new Query();
		
		@Override
		public Query getQuery() {
			return this.userInfoQuery;
		}

		@Override
		public UserInformationCriterion id(String id) {
			userInfoQuery.addCriteria(Criteria.where("id").is(id));
			return this;
		}

		@Override
		public UserInformationCriterion accountId(String id) {
			userInfoQuery.addCriteria(Criteria.where("accountId").is(id));
			return this;
		}

		@Override
		public UserInformationCriterion account(Account account) {
			userInfoQuery.addCriteria(Criteria.where("account").is(account));
			return this;
		}	
	}
}
