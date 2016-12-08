package fr.pizzeria.ihm;

import static org.junit.Assert.assertEquals;

import java.util.Scanner;

import org.junit.Test;

import fr.pizzeria.dao.DaoFactory;
import fr.pizzeria.dao.FichierDaoFactory;

public class IhmUtilTest {

	Scanner sc = new Scanner(System.in);
	DaoFactory dao = new FichierDaoFactory();
	IhmUtil ihm = new IhmUtil(sc, dao);

	@Test
	public void testSetScanner() {
		assertEquals(sc, ihm.getScanner());
	}

	@Test
	public void testSetPizzaDao() {
		assertEquals(dao.getPizzaDao(), ihm.getPizzaDao());
	}

}
