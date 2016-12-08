package fr.pizzeria.dao.pizza;

import java.util.List;

import fr.pizzeria.dao.exception.PizzaException;
import fr.pizzeria.model.Pizza;

public interface PizzaDao {

	List<Pizza> findAllPizzas() throws PizzaException;

	void saveNewPizza(Pizza pizza) throws PizzaException;

	void updatePizza(String codePizza, Pizza pizza) throws PizzaException;

	void deletePizza(String codePizza) throws PizzaException;

	List<Pizza> findAllPizzasCat();

	Pizza findAllPizzasPrix();

}
