package com.tryme.framework.validation;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.nio.file.Files;
import java.security.NoSuchAlgorithmException;
import java.text.MessageFormat;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.math.NumberUtils;

import com.tryme.constants.CoreConstants;
import com.tryme.core.exceptions.InvalidAccountException;
import com.tryme.core.utils.Factory;
import com.tryme.core.utils.PasswordService;
import com.tryme.framework.Account;
import com.tryme.framework.UserInformation;
import com.tryme.framework.criteria.AccountCriterion;
import com.tryme.managers.AccountManager;

/**
 * 
 * Perform the validation process over the account.
 *
 */
public class AccountValidationUtils {
	
	/** An account manager instance. */
	private static AccountManager accountManager;
	
	static {
		accountManager = Factory.getInstance().getAccountManager();
	}

	/**
	 * Verify the user's email.
	 * 
	 * @param email the user's e-mail
	 * @return <code>true</code> if and only if the email is in a correct format
	 * <b>AND</b> is not taken, <code>false</code> otherwise.
	 */
	public static boolean verifyEmail(String email) {
		//TODO Finish the function
		return true;
	}

	/**
	 * Verify if the user's email is already taken.
	 * 
	 * @param email the email that is about to be verify.
	 * @return <code>true</code> if and only if the email is not taken so far, 
	 * <code>false</code> otherwise.
	 */
	private static boolean isEmailTaken(String email) {
		AccountCriterion criterion = accountManager.getAccountCriterion();
		criterion.email(email);
		//TODO finish the function
		return true;
	}

	/**
	 * Check whether or not the username is already taken.
	 * 
	 * @param username the username
	 * @return <code>true</code> if the username is already in use. <br />
	 * <b>NOTE: This not validate the username! Instead use {@link isUsernameValid()}</b>
	 * @throws InvalidAccountException
	 */
	private static boolean isUsernameTaken(String username) {
		AccountCriterion criterion = accountManager.getAccountCriterion();
		criterion.username(username);//Possible NPE?
		return false;
	}

	/**
	 * Validate the account.
	 * 
	 * @param account the account to be validated.
	 * @return <code>true</code> if the account is a valid, <code>false</code> otherwise.
	 * @throws InvalidAccountException 
	 * @throws DuplicateAccountException 
	 */
	public static boolean validateUsername(Account account) throws 
	InvalidAccountException {
		//TODO Check for the correctneses for this function behavior
		if(account == null) {
			throw new InvalidAccountException("");
		}
		
		String username = account.getUsername();
		
		if(StringUtils.isBlank(username)) {
			final String errorMessage = "The username can't be null or empty.";
			throw new InvalidAccountException(errorMessage);
		}

		if(StringUtils.containsWhitespace(username)) {
			final String errorMessage = "The username can't cantain any whitespaces.";
			throw new InvalidAccountException(errorMessage);
		}

		if(NumberUtils.isNumber(username)) {
			final String errorMessage = "The username can't be number-only.";
			throw new InvalidAccountException(errorMessage);
		}
		
		//Check of the username is occupy
		if(isUsernameTaken(account.getUsername())) {
			//TODO Add meaningfull error message
			throw new InvalidAccountException("");
		}
		return true;
	}

	/**
	 * Merge to domain account.
	 * 
	 * @param account the old account
	 * @param updateAccount the updated account
	 * @return the newly updated account or <code>null</code> 
	 * if the update account and the domain account are the same.
	 * @throws InvalidAccountException 
	 * @throws UnsupportedEncodingException 
	 * @throws NoSuchAlgorithmException 
	 */
	public static Account mergeToDomainAccount(Account account, Account updateAccount) 
			throws InvalidAccountException, NoSuchAlgorithmException, 
			UnsupportedEncodingException {
		
		if (account == null || updateAccount == null) {
			return null;
		}
		
		//The user's e-mail can not be changed
		if (!StringUtils.isBlank(updateAccount.getEmail()) &&
				!account.getEmail().equals(updateAccount.getEmail())) {
			rollbackAccountInfo(updateAccount);
			throw new IllegalArgumentException("The user email can not be changed!");
		}

		//The user's username is changed
		if (!StringUtils.isBlank(updateAccount.getUsername())
				&& !account.getUsername().equals(updateAccount.getUsername())) {
			//The username is different, so perform the basic verifications over the new one
			validateUsername(updateAccount);
			account.setUsername(updateAccount.getUsername());
		}
		
		if (!StringUtils.isBlank(updateAccount.getPassword())) {
			String plainPassword = updateAccount.getPassword();
			if (PasswordValidationUtils.isPasswordStrongEnough(plainPassword)) {
				String newPassword = PasswordService.getInstance().encrypt(plainPassword);
				if (!account.getPassword().equals(newPassword)) {
					account.setPassword(newPassword);
				}
			}
		}
		
		mergeToDomainUserInfo(account, updateAccount);
		
		return account;
	}

	private static void mergeToDomainUserInfo(Account account, Account updateAccount) {
		UserInformation userInfo = account.getUserInformation();
		UserInformation updateUserInfo = updateAccount.getUserInformation();
		//TODO finish the function
		if (updateAccount == null) {
			return;
		}

	}
	
	public static boolean updateAccountAvatar(String fileName, InputStream inputStream) throws IOException{
		File file = new File(CoreConstants.AVATAR_DIR_PREFIX.concat(fileName));
		try (OutputStream outputStream = new FileOutputStream(file);) {
			int read = 0;
			byte[] bytes = new byte[1024];
			while ((read = inputStream.read(bytes)) != -1) {
				outputStream.write(bytes, 0, read);
			}
		}
		return true;
	}

	/**
	 * In case of an exception rollback every persist info.
	 * 
	 * @param account the account
	 */
	public static void rollbackAccountInfo(Account account) {
		//TODO For now check if the account is change his picture
	}
}
