package fr.pizzeria.dao;

import java.sql.SQLException;

import fr.pizzeria.dao.pizza.PizzaDaoJDBC;

public class JDBCDaoFactory extends DaoFactory {

	public JDBCDaoFactory() throws SQLException {
		super(new PizzaDaoJDBC());
	}

}
