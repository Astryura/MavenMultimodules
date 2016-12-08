package fr.pizzeria.dao.pizza;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import fr.pizzeria.model.CategoriePizza;
import fr.pizzeria.model.Pizza;

public class PizzaDaoJDBC implements PizzaDao {

	List<Pizza> listPizzas = new ArrayList<Pizza>();
	String url;
	Connection connection;
	Statement statement;

	public void PizzaDaoJDBCStart() throws SQLException {
		url = "jdbc:mysql://localhost:3306/pizzadb";
		connection = DriverManager.getConnection(url, "root", "");
		statement = connection.createStatement();
	}

	public void PizzaDaoJDBCEnd() throws SQLException {
		connection.close();
	}

	@Override
	public List<Pizza> findAllPizzas() throws SQLException {
		listPizzas.clear();
		Pizza.setNbPizzas(0);
		PizzaDaoJDBCStart();
		ResultSet resultats = statement.executeQuery("SELECT * FROM PIZZA");
		while (resultats.next()) {
			Integer id = resultats.getInt("ID");
			String code = resultats.getString("CODE");
			String name = resultats.getString("NOM");
			Double price = resultats.getDouble("PRIX");
			String cat = resultats.getString("CATEGORIE");
			Pizza pizza = new Pizza(id, code, name, price,
					CategoriePizza.valueOf(cat.toUpperCase().replaceAll(" ", "_")));
			listPizzas.add(pizza);
		}
		PizzaDaoJDBCEnd();
		return listPizzas;
	}

	@Override
	public void saveNewPizza(Pizza pizza) throws SQLException {
		PizzaDaoJDBCStart();
		PreparedStatement addPizzaSt = connection
				.prepareStatement("INSERT INTO PIZZA (CODE, NOM, PRIX, CATEGORIE) VALUES (?,?,?,?)");
		addPizzaSt.setString(1, pizza.getCode());
		addPizzaSt.setString(2, pizza.getNom());
		addPizzaSt.setDouble(3, pizza.getPrix());
		addPizzaSt.setString(4, pizza.getCatP());
		addPizzaSt.executeUpdate();
		PizzaDaoJDBCEnd();

	}

	@Override
	public void updatePizza(String codePizza, Pizza pizza) throws SQLException {
		PizzaDaoJDBCStart();
		PreparedStatement updatePizzaSt = connection
				.prepareStatement("UPDATE PIZZA SET ID=?,CODE=?,NOM=?,PRIX=?,CATEGORIE=? WHERE CODE = ?");
		updatePizzaSt.setInt(1, pizza.getId());
		updatePizzaSt.setString(2, pizza.getCode());
		updatePizzaSt.setString(3, pizza.getNom());
		updatePizzaSt.setDouble(4, pizza.getPrix());
		updatePizzaSt.setString(5, pizza.getCatP());
		updatePizzaSt.setString(6, codePizza);
		updatePizzaSt.executeUpdate();
		PizzaDaoJDBCEnd();
	}

	@Override
	public void deletePizza(String codePizza) throws SQLException {
		PizzaDaoJDBCStart();
		PreparedStatement deletePizzaSt = connection.prepareStatement("DELETE FROM PIZZA WHERE CODE = ?");
		deletePizzaSt.setString(1, codePizza);
		deletePizzaSt.executeUpdate();
		PizzaDaoJDBCEnd();

	}

	@Override
	public List<Pizza> findAllPizzasCat() throws SQLException {
		listPizzas = findAllPizzas();
		Comparator<Pizza> comp = Comparator.comparing(Pizza::getCatP);
		List<Pizza> list = listPizzas.stream().sorted(comp).collect(Collectors.toList());
		return list;
	}

	@Override
	public Pizza findAllPizzasPrix() throws SQLException {
		listPizzas = findAllPizzas();
		Comparator<Pizza> comp = Comparator.comparing(Pizza::getPrix);
		Optional<Pizza> pizza = listPizzas.stream().max(comp);
		if (pizza.isPresent()) {
			return pizza.get();
		} else {
			return null;
		}
	}

}
