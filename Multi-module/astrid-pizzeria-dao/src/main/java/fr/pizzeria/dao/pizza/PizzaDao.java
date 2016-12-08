package fr.pizzeria.dao.pizza;

import java.sql.SQLException;
import java.util.List;

import fr.pizzeria.model.Pizza;

public interface PizzaDao {

	List<Pizza> findAllPizzas() throws SQLException;

	void saveNewPizza(Pizza pizza) throws SQLException;

	void updatePizza(String codePizza, Pizza pizza) throws SQLException;

	void deletePizza(String codePizza) throws SQLException;

	List<Pizza> findAllPizzasCat() throws SQLException;

	Pizza findAllPizzasPrix() throws SQLException;
}
