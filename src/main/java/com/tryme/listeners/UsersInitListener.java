package com.tryme.listeners;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import com.tryme.core.Factory;
import com.tryme.framework.bean.Account;
import com.tryme.framework.bean.UserInformation;
import com.tryme.framework.criteria.AccountCriterion;
import com.tryme.managers.AccountManager;

public class UsersInitListener implements ServletContextListener {
	
	private AccountManager accountManager = Factory.getInstance().getAccountManager();

	@Override
	public void contextDestroyed(ServletContextEvent arg0) {
	}

	@Override
	public void contextInitialized(ServletContextEvent arg0) {
		List<Account> accounts = new ArrayList<>();
		
		UserInformation tanyaUI = new UserInformation();
		tanyaUI.setAge(23);
		tanyaUI.setAvatar("imgs/tanya.jpg");
		tanyaUI.setFullName("Таня Христова");
		tanyaUI.setSchool("ЕГ 'Гео Милев'");
		tanyaUI.setTown("Добрич");
		tanyaUI.setDescription("Направих си акаунт, защото мама ме накара!");
		Account accountTanya = new Account("tanyaHristova", "tanya.hristova@gmail.com", "Abcd1234", tanyaUI);
		accounts.add(accountTanya);
		
		UserInformation emcheUI = new UserInformation();
		emcheUI.setAge(23);
		emcheUI.setAvatar("imgs/emine.jpg");
		emcheUI.setFullName("Емине Башева");
		emcheUI.setSchool("ПМГ 'Иван Вазов'");
		emcheUI.setTown("Сърница");
		emcheUI.setDescription("Аз съм руса и с 3 метли!");
		Account accountEmche = new Account("eminkaa", "emine.basheva@gmail.com", "Abcd1234", emcheUI);
		accounts.add(accountEmche);
		
		UserInformation pavelUI = new UserInformation();
		pavelUI.setAge(28);
		pavelUI.setAvatar("imgs/pavel.jpg");
		pavelUI.setFullName("Павел Тошев");
		pavelUI.setSchool("ПГМЕ");
		pavelUI.setTown("Бургас");
		pavelUI.setDescription("Аз съм готин. :)");
		Account accountPavel = new Account("pavkata", "pavel.toshev@gmail.com", "Abcd1234", pavelUI);
		accounts.add(accountPavel);
		
		UserInformation hristoUI = new UserInformation();
		hristoUI.setAge(22);
		hristoUI.setAvatar("imgs/hristo.jpg");
		hristoUI.setFullName("Христо Тодоров");
		hristoUI.setSchool("СМГ 'Паисий Хилендарски'");
		hristoUI.setTown("София");
		hristoUI.setDescription("О, я не ме занимавай...");
		Account accountHristo = new Account("hristoto", "hristo.todorov@gmail.com", "Abcd1234", hristoUI);
		accounts.add(accountHristo);
		
		UserInformation monikaUI = new UserInformation();
		monikaUI.setAge(22);
		monikaUI.setAvatar("imgs/monika.jpg");
		monikaUI.setFullName("Моника Петева");
		monikaUI.setSchool("ПМГ 'Нанчо Пoпович'");
		monikaUI.setTown("Шумен");
		monikaUI.setDescription(":)");
		Account accountMonika = new Account("moni", "monika.peteva@gmail.com", "Abcd1234", monikaUI);
		accounts.add(accountMonika);
		
		try {
			for (Account account : accounts) {
				AccountCriterion criterion = accountManager.getAccountCriterion();
				criterion.username(account.getUsername());
				boolean taken = accountManager.getAccount(criterion) != null;
				if(!taken) {
					accountManager.addAccount(account);
				}
			}
		
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

}
