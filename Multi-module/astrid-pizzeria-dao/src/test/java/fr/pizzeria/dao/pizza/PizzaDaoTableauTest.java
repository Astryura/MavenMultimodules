package fr.pizzeria.dao.pizza;

import static org.junit.Assert.assertEquals;

import java.util.List;

import org.junit.Before;
import org.junit.Test;

import fr.pizzeria.model.Pizza;

public class PizzaDaoTableauTest {

	PizzaDaoTableau pdt;
	List<Pizza> list;

	@Before
	public void testPizzaDaoTableau() {
		pdt = new PizzaDaoTableau();

		list = pdt.listPizzas;
	}

	@Test
	public void testFindAllPizzas() {

		list.forEach(p -> {
			assertEquals(p.getClass(), Pizza.class);
		});
		assertEquals(list.size(), Pizza.getNbPizzas());
	}

}
