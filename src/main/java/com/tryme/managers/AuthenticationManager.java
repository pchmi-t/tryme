package com.tryme.managers;

import com.tryme.framework.Account;

public interface AuthenticationManager {
	
	boolean logonUser(Account account);
	
	boolean logoffUser(Account account);

}
