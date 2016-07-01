package com.tryme.listeners;

import java.util.Date;
import java.util.List;
import java.util.Random;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import com.tryme.core.Factory;
import com.tryme.framework.bean.Account;
import com.tryme.managers.AccountManager;
import com.tryme.managers.ScoreManager;

public class ScoresInitListener implements ServletContextListener {

	private ScoreManager scoreManager = Factory.getInstance().getScoreManager();
	private AccountManager accountManager = Factory.getInstance().getAccountManager();

	@Override
	public void contextDestroyed(ServletContextEvent arg0) {
	}

	@Override
	public void contextInitialized(ServletContextEvent arg0) {
		try {
			List<Account> allAccounts = accountManager.getAccounts(accountManager.getAccountCriterion(),
					Integer.MAX_VALUE);
			for (Account account : allAccounts) {
				scoreManager.save(account, new Random(new Date().getTime()).nextInt(100) + 10);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
