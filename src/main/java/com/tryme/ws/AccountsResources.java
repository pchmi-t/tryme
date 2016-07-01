package com.tryme.ws;

import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.UnknownHostException;
import java.security.NoSuchAlgorithmException;
import java.text.MessageFormat;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import org.apache.commons.lang3.StringUtils;
import org.bson.types.ObjectId;
import org.hibernate.validator.constraints.NotBlank;

import com.sun.istack.NotNull;
import com.sun.jersey.multipart.BodyPart;
import com.sun.jersey.multipart.MultiPart;
import com.tryme.constants.CoreConstants;
import com.tryme.core.Factory;
import com.tryme.core.PasswordService;
import com.tryme.core.exceptions.InvalidAccountException;
import com.tryme.core.exceptions.InvalidUserInfoException;
import com.tryme.core.exceptions.NoSuchAccountException;
import com.tryme.core.exceptions.WSBaseException;
import com.tryme.framework.bean.Account;
import com.tryme.framework.bean.UserInformation;
import com.tryme.framework.bean.UserLogin;
import com.tryme.framework.criteria.AccountCriterion;
import com.tryme.framework.validation.AccountValidationUtils;
import com.tryme.managers.AccountManager;

@Path("/accounts")
public class AccountsResources {

	/**
	 * An account manager instance.
	 */
	private AccountManager accountManager = Factory.getInstance().getAccountManager();

	/** The context. */
	@Context 
	private HttpServletRequest request;
	
	@Context
	 private HttpServletResponse response;
	
	/**
	 * Get all accounts.
	 * 
	 * @return return all the accounts
	 * @throws UnknownHostException
	 */
	@GET
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	public List<Account> getAccounts() {
		List<Account> accounts = new LinkedList<>();
		AccountCriterion criterion = accountManager.getAccountCriterion();
		try {
			accounts = accountManager.getAccounts(criterion, Integer.MAX_VALUE);
		} catch (Exception e) {
			// TODO Throw some exception
			e.printStackTrace();
		}
		return accounts;
	}

	/**
	 * Get the account, associated with the given id.
	 * 
	 * @param id the account id
	 * @return the account
	 */
	@GET
	@Path("{username}")
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	public Account getAccountByUsername(@PathParam("username") String username) {
		AccountCriterion criterion = accountManager.getAccountCriterion();
		criterion.username(username);
		Account account;
		try {
			account = accountManager.getAccount(criterion);
			if (account != null) {
				return account;
			} else {
				throw new WSBaseException("The account with the specify id does not exist");
			}
		} catch (Exception e) {
			if (e instanceof NoSuchAccountException || e instanceof WSBaseException) {
				throw new WSBaseException("The specified account does not exist.");
			} else {
				throw new WSBaseException(Status.INTERNAL_SERVER_ERROR, 
						CoreConstants.GENERAL_ERROR_MSG);
			}
		}
	}

	@POST
	@Path("{username}")
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	@Consumes({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	public Response authenticateAccount(@NotBlank(message="Username can not be empty")
	@PathParam("username")String username, @NotBlank(message="Please specify the account") UserLogin account) {
		try {
			if (!username.equals(account.getUsername())) {
				return Response.status(Status.BAD_REQUEST).entity("").build();
			}
			String plainPassword = account.getPassword();
			String encryptedPassword = PasswordService.getInstance().encrypt(plainPassword);
			AccountCriterion criterion = accountManager.getAccountCriterion();
			criterion.username(username);
			criterion.password(encryptedPassword);
			Account domainAccount = accountManager.getAccount(criterion);
			if (domainAccount != null) {
				Cookie cookie = new  Cookie("account", username);
			    cookie.setMaxAge(60 * 5);
			    //Add the cookie to the response
			    response.addCookie(cookie);
				return Response.ok().entity(account).build();
			} else {
				return Response.status(Status.BAD_REQUEST).entity("").build();
			}
		} catch (Exception e) {
			return Response.status(Status.BAD_REQUEST).entity("").build();
		}
	}

	/**
	 * Create an account.
	 * 
	 * @param account the account that is about created.
	 */
	@POST
	@Consumes({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	public Response createAccount(Account account) {
		try {
			if (AccountValidationUtils.validateUsername(account)) {
				account.setPassword(PasswordService
						.getInstance()
						.encrypt(account.getPassword()));
				accountManager.addAccount(account);
			}
		} catch (Exception e) {
			if (e instanceof InvalidAccountException) {
				final String errorMessage = "An error occured while saving the account.";
				return Response.status(Status.BAD_REQUEST).entity(e.getMessage()).build();
			}
		}
		return Response.status(Status.CREATED).entity(account).build();
	}

	/**
	 * Update an account.
	 * 
	 * @param multiPart the multi-part first part should be the account and the second -
	 * the updated avatar if exist.
	 * 
	 * @return the newly updated account
	 */
	@PUT
	@Consumes({ MediaType.MULTIPART_FORM_DATA })
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	public Response updateAccount(MultiPart multiPart) {
		try {
			List<BodyPart> bodyParts = multiPart.getBodyParts();
			Account account = null;
			Account updatedAccount = null;
			MediaType type;
			String fileName = null;
			InputStream inputStream = null;
			for (BodyPart bodyPart : bodyParts) {
				type = bodyPart.getMediaType();
				if (MediaType.APPLICATION_JSON_TYPE.equals(type) ||
						MediaType.APPLICATION_XML_TYPE.equals(type)) {
					updatedAccount = bodyPart.getEntityAs(Account.class);
				} else if (MediaType.APPLICATION_OCTET_STREAM_TYPE.equals(type)) {
					fileName = bodyPart.getContentDisposition().getFileName();
					inputStream = bodyPart.getEntityAs(InputStream.class);
				}
			}
			if (updatedAccount == null) {
				return Response.notModified().build();
			}
			AccountCriterion criterion = accountManager.getAccountCriterion();
			criterion.id(updatedAccount.getId());
			List<Account> accounts = new LinkedList<>();

			accounts = accountManager.getAccounts(criterion, 1);

			if (accounts != null && accounts.isEmpty()) {
				account = accounts.get(0);
			}

			AccountValidationUtils.mergeToDomainAccount(account, updatedAccount);
			if (!StringUtils.isBlank(fileName) && inputStream != null) {
				if (AccountValidationUtils.updateAccountAvatar(fileName, inputStream)) {
					if (updatedAccount != null) {
						if (updatedAccount.getUserInformation() != null) {
							updatedAccount.getUserInformation().setAvatar(
									CoreConstants.AVATAR_DIR_PREFIX.concat(fileName));
						} else {
							updatedAccount.setUserInformation(new UserInformation());
							updatedAccount.getUserInformation().setAvatar(
									CoreConstants.AVATAR_DIR_PREFIX.concat(fileName));
						}
					}
				}
			}
			accountManager.updateAccount(updatedAccount);
			Response.ok(updatedAccount, MediaType.APPLICATION_JSON).build();
			return null;
		} catch (Exception e) {
			return Response.serverError().build();
		}
	}
}
