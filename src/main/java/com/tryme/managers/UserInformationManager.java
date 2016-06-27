package com.tryme.managers;

import com.tryme.framework.Account;
import com.tryme.framework.UserInformation;
import com.tryme.framework.criteria.UserInformationCriterion;

public interface UserInformationManager {

	/**
	 * Get the user information by id.
	 * 
	 * @param userInfoId
	 * @return
	 */
	UserInformation getUserInformation(UserInformationCriterion criterion);
	
	/**
	 * Update the user information.
	 * 
	 * @param updateUserInfo the user information that is about to be updated.
	 */
	void updateUserInformation(UserInformation updateUserInfo);
	
	/**
	 * Create the user information.
	 * 
	 * @param userInformation the user information.
	 */
	void createUserInformation(UserInformation userInformation);
}
