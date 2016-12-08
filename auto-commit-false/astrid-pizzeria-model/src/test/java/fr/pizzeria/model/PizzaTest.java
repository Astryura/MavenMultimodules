package fr.pizzeria.model;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class PizzaTest {

	Pizza pizza = new Pizza(0, "P", "Pep", 10.00, CategoriePizza.VIANDE);

	@Test
	public void testSetCatP() {
		assertEquals(CategoriePizza.VIANDE.toString(), pizza.getCatP());
	}

	@Test
	public void testSetId() {
		assertEquals(0, pizza.getId());
	}

	@Test
	public void testSetCode() {
		assertEquals("P", pizza.getCode());
	}

	@Test
	public void testSetNom() {
		assertEquals("Pep", pizza.getNom());
	}

}
