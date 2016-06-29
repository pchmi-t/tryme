package com.tryme.framework.validation;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.security.NoSuchAlgorithmException;
import java.text.MessageFormat;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.math.NumberUtils;

import com.tryme.constants.CoreConstants;
import com.tryme.core.Factory;
import com.tryme.core.PasswordService;
import com.tryme.core.exceptions.InvalidAccountException;
import com.tryme.framework.bean.Account;
import com.tryme.framework.bean.UserInformation;
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
	 * @throws Exception 
	 */
	public static boolean validateEmail(String email) throws Exception {
		EmailValidator validator = getEmailValidator();
		if (!StringUtils.isBlank(email)) {
			return validator.validate(email) && !isEmailTaken(email);
		} else {
			throw new InvalidAccountException("The e-mail is mandatory.");
		}
	}

	/**
	 * Verify if the user's email is already taken.
	 * 
	 * @param email the email that is about to be verify.
	 * @return <code>true</code> if and only if the email is not taken so far, 
	 * <code>false</code> otherwise.
	 * @throws Exception 
	 */
	private static boolean isEmailTaken(String email) throws Exception {
		AccountCriterion criterion = accountManager.getAccountCriterion();
		if (StringUtils.isBlank(email)) {
			throw new InvalidAccountException("The e-mail is mandatory.");
		}
		criterion.email(email);
		Account account = accountManager.getAccount(criterion);
		return account != null;
	}

	/**
	 * Check whether or not the username is already taken.
	 * 
	 * @param username the username
	 * @return <code>true</code> if the username is already in use. <br />
	 * <b>NOTE: This not validate the username! Instead use {@link isUsernameValid()}</b>
	 * @throws Exception 
	 * @throws InvalidAccountException
	 */
	private static boolean isUsernameTaken(String username) throws Exception {
		AccountCriterion criterion = accountManager.getAccountCriterion();
		criterion.username(username);//Possible NPE?
		return accountManager.getAccount(criterion) != null;
	}

	/**
	 * Validate the account.
	 * 
	 * @param account the account to be validated.
	 * @return <code>true</code> if the account is a valid, <code>false</code> otherwise.
	 * @throws Exception 
	 * @throws DuplicateAccountException 
	 */
	public static boolean validateUsername(Account account) throws 
	Exception {
		//TODO Check for the correctneses for this function behavior
		if(account == null) {
			throw new InvalidAccountException("The account can not be null");
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
			throw new InvalidAccountException("The username is already taken.");
		}

		return true;
	}

	/**
	 * Merge to domain account.
	 * 
	 * @param domainAccount the old account
	 * @param updateAccount the updated account
	 * @return the newly updated account or <code>null</code> 
	 * if the update account and the domain account are the same.
	 * @throws Exception 
	 */
	public static void mergeToDomainAccount(Account domainAccount, Account updateAccount) 
			throws Exception {

		if (domainAccount == null || updateAccount == null) {
			return;
		}

		if (!StringUtils.isBlank(updateAccount.getPassword())) {
			String plainPassword = updateAccount.getPassword();
			if (PasswordValidationUtils.isPasswordStrongEnough(plainPassword)) {
				String newPassword = PasswordService.getInstance().encrypt(plainPassword);
				if (!domainAccount.getPassword().equals(newPassword)) {
					domainAccount.setPassword(newPassword);
				}
			}
		}
		
		//Update  the user information
		mergeToDomainUserInfo(domainAccount.getUserInformation(), updateAccount.getUserInformation());
		
	}

	private static void mergeToDomainUserInfo(UserInformation domainUserInfo, UserInformation updateUserInfo) {
		
	}

	public static boolean updateAccountAvatar(String fileName, InputStream inputStream) 
			throws IOException{

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
	public static void rollbackAccountInfo(UserInformation userInfo) {
		//UserInformation userInfo = account.getUserInformation();
		if (userInfo != null) {
			String avatarPath = userInfo.getAvatar();
			try {
				Files.deleteIfExists(Paths.get(
						CoreConstants.AVATAR_DIR_PREFIX.concat(avatarPath)));
			} catch (IOException e) {
				//Do nothing
			}
		}
	}

	public static EmailValidator getEmailValidator() {
		return new EmailValidator();
	}

	/**
	 * 
	 * The email validator.
	 *
	 */
	private static class EmailValidator {

		private Pattern pattern;
		private Matcher matcher;

		public EmailValidator() {
			pattern = Pattern.compile(CoreConstants.EMAIL_PATTERN);
		}

		/**
		 * Validate hex with regular expression
		 * 
		 * @param hex hex for validation
		 * @return <code>true</code> valid hex, <code>false</code> otherwise.
		 */
		public boolean validate(final String hex) {
			matcher = pattern.matcher(hex);
			return matcher.matches();
		}
	}
}
