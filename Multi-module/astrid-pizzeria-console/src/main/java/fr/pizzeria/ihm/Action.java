package fr.pizzeria.ihm;

import java.sql.SQLException;

public interface Action {

	void doAction() throws SQLException;

	void describeAction();
}
