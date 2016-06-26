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

import javax.servlet.http.HttpServletRequest;
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

import com.sun.istack.NotNull;
import com.sun.jersey.multipart.BodyPart;
import com.sun.jersey.multipart.MultiPart;
import com.tryme.constants.CoreConstants;
import com.tryme.core.exceptions.InvalidAccountException;
import com.tryme.core.exceptions.NoSuchAccountException;
import com.tryme.core.exceptions.WSBaseException;
import com.tryme.core.utils.Factory;
import com.tryme.framework.Account;
import com.tryme.framework.UserInformation;
import com.tryme.framework.criteria.AccountCriterion;
import com.tryme.framework.validation.AccountValidationUtils;
import com.tryme.managers.AccountManager;

@Path("/accounts")
public class AccountsResources {

	/**
	 * An account manager instance.
	 */
	private AccountManager accountManager = Factory.getAccountManager();

	/** The context. */
	@Context 
	private HttpServletRequest request;

	/**
	 * Get all accounts.
	 * 
	 * @return return all the accounts
	 * @throws UnknownHostException
	 */
	@GET
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	public List<Account> getAccounts() {
		AccountCriterion criterion = accountManager.getAccountCriterion();
		return accountManager.getAccounts(criterion, Integer.MAX_VALUE, 0);
	}

	/**
	 * Get the account, associated with the given id.
	 * 
	 * @param id the account id
	 * @return the account
	 */
	@GET
	@Path("{id}")
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	public Account getAccountById(@PathParam("id") String id) {
		AccountCriterion criterion = accountManager.getAccountCriterion();
		criterion.id(id);
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

	@GET
	@Path("{id}/{username}/{email}")
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	public Account getAccountByUsername(@PathParam("id") String id,
			@PathParam("username") String username, @PathParam("email") String email) {

		AccountCriterion criterion = accountManager.getAccountCriterion();
		criterion.email(email);
		criterion.id(id);
		criterion.username(username);
		Account account;
		try {
			account = accountManager.getAccount(criterion);
			if (account != null) {
				return account;
			} else {
				throw new WSBaseException("No such account exist");
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
			accountManager.addAccount(account);
		} catch (InvalidAccountException e) {
			final String errorMessage = "An error occured while saving the account.";
			throw new WebApplicationException(e, Status.BAD_REQUEST);
		}
		return Response.status(Status.CREATED).entity(account).build();
	}

	@PUT
	@Consumes({ MediaType.MULTIPART_FORM_DATA })
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	public Response updateAccount(MultiPart multiPart) {
		List<BodyPart> bodyParts = multiPart.getBodyParts();
		Account updatedAccount = null;
		Account account = null;
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
			return Response.status(Status.BAD_REQUEST).build();
		}
		AccountCriterion criterion = accountManager.getAccountCriterion();
		criterion.id(updatedAccount.getId());
		List<Account> accounts = accountManager.getAccounts(criterion, 1, 0);
		if (accounts != null && accounts.isEmpty()) {
			account = accounts.get(0);
		}
	
		if (!StringUtils.isBlank(fileName) && inputStream != null) {
			try {
				if (AccountValidationUtils.updateAccountAvatar(fileName, inputStream)) {
					if (updatedAccount != null) {
						if (updatedAccount.getUserInformation() != null) {
							updatedAccount.getUserInformation().setAvatar(
									CoreConstants.AVATAR_DIR_PREFIX.concat(fileName));
						} else {
//							updatedAccount.setUserInformation(new UserInformation());
							updatedAccount.getUserInformation().setAvatar(
									CoreConstants.AVATAR_DIR_PREFIX.concat(fileName));
						}
					}
				}
			} catch (IOException e) {
				return Response.status(Status.BAD_REQUEST).build();
			}
		}
		try {
			accountManager.updateAccount(updatedAccount);
			Response.status(Status.NO_CONTENT).build();
		} catch (Exception e) {
			AccountValidationUtils.rollbackAccountInfo(updatedAccount);
			Response.status(Status.BAD_REQUEST).build();
		}
		return null;
	}
	
	
}
