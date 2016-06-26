package com.tryme.core.utils;

import com.tryme.managers.AccountManager;
import com.tryme.managers.AuthenticationManager;
import com.tryme.managers.impl.AccountManagerImpl;
import com.tryme.managers.impl.AuthenticationManagerImpl;

public final class Factory {
	
	private static Factory instance;
	
	private Factory() {
	}

	/**
	 * Get the instance of the factory.
	 * 
	 * @return
	 */
	public static synchronized Factory getInstance() {
		return instance == null ? new Factory() : instance;
	}

	public static AccountManager getAccountManager() {
		return new AccountManagerImpl();
	}

	public static AuthenticationManager getAuthenticationManager() {
		return new AuthenticationManagerImpl();
	}
}
