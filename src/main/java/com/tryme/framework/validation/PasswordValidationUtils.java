package com.tryme.framework.validation;

import org.apache.commons.lang3.StringUtils;

import com.tryme.constants.CoreConstants;


/**
 * 
 * The password validation class.
 *
 */
public class PasswordValidationUtils {
	
	/**
	 * Is password valid in means that it should not be empty, or contains any whitespaces.
	 * 
	 * @param plainPassword the password to check
	 * @return <code>true</code> if password is valid, <code>false</code> otherwise.
	 */
	private static boolean isPassowrdContainSpaces(String plainPassword) {
		return StringUtils.containsWhitespace(plainPassword) 
				|| StringUtils.isWhitespace(plainPassword);
	}

	/**
	 * Check whether or not the password is strong enough. <br />
	 * A 'strong' password must contain the following:
	 * <li>At least 6 symbols.</li>
	 * <li>At least one upper case.</li>
	 * <li>At least one special character.</li>
	 * A list of the special characters can be find here: {@link AccountConstants.SPECIAL_CHARACTERS}
	 * 
	 * @param password the user's password
	 * @return <code>true</code> if the password meet the security bar, <code>false</code> otherwise.
	 */
	public static boolean isPasswordStrongEnough(String password) {
		if(isPassowrdContainSpaces(password)) {
			return false;
		}
		if (password.length() < 6) {
			return false;
		}
		return StringUtils.containsAny(password, CoreConstants.SPECIAL_CHARACTERS) && 
				!password.equals(password.toLowerCase());
	}
	
}
