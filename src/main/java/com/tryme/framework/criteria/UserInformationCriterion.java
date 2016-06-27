package com.tryme.framework.criteria;

import com.tryme.framework.Account;

public interface UserInformationCriterion extends BaseCriteria {
	UserInformationCriterion id(String id);
	
	UserInformationCriterion accountId(String id);
	
	UserInformationCriterion account(Account account);
	
}
