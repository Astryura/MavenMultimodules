package fr.pizzeria.dao;

import fr.pizzeria.dao.pizza.PizzaDaoTableau;

public class TableauDaoFactory extends DaoFactory {

	public TableauDaoFactory() {
		super(new PizzaDaoTableau());
	}
}
