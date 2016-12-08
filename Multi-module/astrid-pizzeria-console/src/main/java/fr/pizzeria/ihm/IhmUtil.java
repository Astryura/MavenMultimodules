package fr.pizzeria.ihm;

import java.util.Scanner;

import fr.pizzeria.dao.DaoFactory;
import fr.pizzeria.dao.pizza.PizzaDao;

public class IhmUtil {

	private Scanner scanner;
	private DaoFactory daoFactory;

	public IhmUtil(Scanner scanner, DaoFactory daoFactory) {

		this.scanner = scanner;
		this.daoFactory = daoFactory;
	}

	public Scanner getScanner() {
		return scanner;
	}

	public void setScanner(Scanner scanner) {
		this.scanner = scanner;
	}

	public PizzaDao getPizzaDao() {
		return daoFactory.getPizzaDao();
	}

	public void setPizzaDao(DaoFactory daoFactory) {
		this.daoFactory = daoFactory;
	}

}
