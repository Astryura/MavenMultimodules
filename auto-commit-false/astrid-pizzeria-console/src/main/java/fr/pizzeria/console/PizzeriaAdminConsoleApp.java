package fr.pizzeria.console;

import java.util.ResourceBundle;
import java.util.Scanner;

import fr.pizzeria.dao.DaoFactory;
import fr.pizzeria.ihm.IhmUtil;
import fr.pizzeria.ihm.MainMenu;

public class PizzeriaAdminConsoleApp {

	public static void main(String[] args)
			throws InstantiationException, IllegalAccessException, ClassNotFoundException, NumberFormatException {

		ResourceBundle bundle = ResourceBundle.getBundle("application");

		String choix = bundle.getString("dao.impl");

		DaoFactory daoFactory = (DaoFactory) Class.forName(choix).newInstance();

		IhmUtil ihmUtil = new IhmUtil(new Scanner(System.in), daoFactory);

		MainMenu mainMenu = new MainMenu(ihmUtil);

		mainMenu.displayMenu();

	}
}