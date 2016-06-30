package com.tryme.core;

import com.tryme.managers.AccountManager;
import com.tryme.managers.AuthenticationManager;
import com.tryme.managers.TestManager;
import com.tryme.managers.impl.AccountManagerImpl;
import com.tryme.managers.impl.AuthenticationManagerImpl;
import com.tryme.managers.impl.TestManagerImpl;

public final class Factory {
	
	/** An instance of this factory. */
	private static Factory instance;

	/** A private constructor preventing instantiation of this factory. */
	private Factory() {
	}

	/**
	 * Get the instance of the factory.
	 * 
	 * @return the factory instance.
	 */
	public static synchronized Factory getInstance() {
		return instance == null ? new Factory() : instance;
	}

	/**
	 * Get the account manager.
	 * 
	 * @return the account manager.
	 */
	public static AccountManager getAccountManager() {
		return new AccountManagerImpl();
	}

	/**
	 * Get the authentication manager.
	 * 
	 * @return the authentication manager.
	 */
	public static AuthenticationManager getAuthenticationManager() {
		return new AuthenticationManagerImpl();
	}
	
	public static TestManager getTestManager() {
		return new TestManagerImpl();
	}
	
}
