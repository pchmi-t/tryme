package com.tryme.listeners;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import com.tryme.core.Factory;
import com.tryme.framework.bean.Answer;
import com.tryme.framework.bean.Question;
import com.tryme.framework.bean.Test;
import com.tryme.managers.TestManager;

public class DatabaseInitListener implements ServletContextListener {

	private TestManager testManager = Factory.getInstance().getTestManager();

	@Override
	public void contextDestroyed(ServletContextEvent arg0) { //
	}

	@Override
	public void contextInitialized(ServletContextEvent arg0) {
		Question question11 = new Question("Какво наричаме вектор?", new Answer("Отсечка", false),
				new Answer("Повърхност", false),
				new Answer("Отсечка, на която единият край е избран за първи (начало), а другият за втори (край).",
						true),
				new Answer("Отсечка, на която няма краища", false), new Answer("Насочена отсечка", false));

		Question question12 = new Question("Кое от изброените не е елемент на вектор?", new Answer("Начало", false),
				new Answer("Среда", true), new Answer("Край.", false), new Answer("Посока", false),
				new Answer("Директриса", false), new Answer("Дължина", false));

		Question question13 = new Question(
				"На колко е равна релативната мярка на сбор от два сегмента, взети последователно върху една ос?",
				new Answer("На разликата от абцисите на края и началото му", false),
				new Answer("На сбора от релативните мерки на тези сегменти", true));

		Test test1 = new Test("Вектори", "math", "class8", question11, question12, question13);
		try {
			testManager.save(test1);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
