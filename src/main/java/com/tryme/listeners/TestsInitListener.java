package com.tryme.listeners;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import com.tryme.core.Factory;
import com.tryme.framework.bean.Answer;
import com.tryme.framework.bean.Question;
import com.tryme.framework.bean.Test;
import com.tryme.managers.TestManager;

public class TestsInitListener implements ServletContextListener {

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

		Test test1 = new Test("Вектори",
				"Това е тест за вектори. Включва само основните познания за тях. Не е подходящ за по-напреднали.",
				"math", "class8", question11, question12, question13);

		Question question21 = new Question("Приносът на Ренесанса за развитието на ценностния мироглед се състои от?",
				new Answer("Почит към индивидуалното право на личността", false),
				new Answer("Осъзнаване средищнотомясто на човека във Вселената", true),
				new Answer("Почит към божествения закон", true));

		Question question22 = new Question("Основната доктрина на Ренесанса се нарича?", new Answer("Хуманизъм", true),
				new Answer("Сантиментализъм", false), new Answer("Реализъм", false));

		Question question23 = new Question("На кого принадлежи трактът „За достойнството на човека”?",
				new Answer("Еразъм Ротердамски", false), new Answer("Джовани Пико дела Мирандола", true),
				new Answer("Данте Алигери", false));

		Question question24 = new Question("Типични ренесансови жанрове на литературата са?",
				new Answer("Епопея, идилия, сатира", false), new Answer("Житие, похвално слово, приказка", false),
				new Answer("Роман, новела, сонет", true));

		Question question25 = new Question("Основата на ренесансовата култура е?",
				new Answer("Феодалната йерархия", false), new Answer("Свободният град и свободният човек", true),
				new Answer("Земеделието", false));

		Question question26 = new Question("Къде възниква Ренесансът?", new Answer("Париж, Франция", false),
				new Answer("Лондон, Англия", false), new Answer("Флоренция, Италия", true));

		Question question27 = new Question("Кое от следните не е вярно за Ренесанса?",
				new Answer(
						"През Ренесанса възкръсва калокагатийният модел на древните за красивата хармония между дух и материя, човек и свят.",
						false),
				new Answer(
						"Ренесансът има тенденция да смята сатирата за нещо незаслужаващо сериозно внимание, предразсъдък, който има влияние и днес.",
						true),
				new Answer(
						"През Ренесанса красотата на голото човешко тяло става символен знак на новия културен модел",
						false),
				new Answer("През Ренесанса светското се отделя от църковно-духовното.", false));

		Test test2 = new Test("Ренесанс",
				"Това е тест за Ренесансова литература. Изисква повече от основните познания. Не е подходящ за начинаещи.",
				"lit", "class10", question21, question22, question23, question24, question25, question26, question27);

		Question question31 = new Question("След разгрома на Аварския хаганат България става непосредствен съсед на?",
				new Answer("Франкската империя.", true), new Answer("Немското кралство", false),
				new Answer("Арабски халифат", false), new Answer("Римската империя", false));

		Question question32 = new Question("Какви са последиците от административната реформа на кан Омуртаг?",
				new Answer("Прабългарската аристокрация засилва позициите си спрямо кана", false),
				new Answer(
						"Славянската аристокрация получава по-големи правомощия в администрирането на пограничните райони на страната",
						false),
				new Answer("Славянските племена загубват своята автономия", true),
				new Answer(
						"Християнското население в завладените от България византийски територии е върнато към езическата религия",
						false));

		Question question33 = new Question("Славянската писменост, създадена от Кирил и Методий, е известна като?",
				new Answer("Глаголица", true), new Answer("Кирилица", false), new Answer("Черти и резки", false),
				new Answer("Димотическо писмо", false));

		Question question34 = new Question(
				"На политическата карта на Югоизточна Европа в първите години от управлението на цар Иван Асен II НЕ съществува държавата?",
				new Answer("Византийска империя", true), new Answer("Латинска империя", false),
				new Answer("Епирско деспотство", false), new Answer("Сръбско кралство", false));

		Question question35 = new Question(
				"В кой от посочените варианти старите български столици са в хронологична последователност?",
				new Answer("Плиска, Охрид, Преслав, Търново", false),
				new Answer("Плиска, Преслав, Охрид, Търново", true),
				new Answer("Преслав, Плиска, Търново, Охрид", false),
				new Answer("Плиска, Търново, Преслав, Охрид", false));

		Test test3 = new Test("Средновековна България",
				"Това е тест за Средновековна България. Изисква се напреднало (кандидат-студентско) ниво.", "hist",
				"class12", question31, question32, question33, question34, question35);

		try {
			testManager.save(test1);
			testManager.save(test2);
			testManager.save(test3);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
