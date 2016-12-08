package fr.pizzeria.dao.pizza;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.AbstractList;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.logging.Logger;
import java.util.stream.Collectors;

import fr.pizzeria.dao.exception.PizzaException;
import fr.pizzeria.model.CategoriePizza;
import fr.pizzeria.model.Pizza;

public class PizzaDaoJDBC implements PizzaDao {

	interface IRunSql<T> {
		T exec(Statement st) throws SQLException;
	}

	interface IRunSqlPrep<T> {
		T exec(Connection conn) throws SQLException;
	}

	public <T> T execute(IRunSql<T> run) {
		String url = "jdbc:mysql://localhost:3306/pizzadb";
		try (Connection connection = DriverManager.getConnection(url, "root", "");
				Statement statement = connection.createStatement();) {

			return run.exec(statement);
		} catch (SQLException e) {
			Logger.getLogger(PizzaDaoJDBC.class.getName()).severe(e.getMessage());
			throw new PizzaException(e);
		}

	}

	public <T> T executePrep(IRunSqlPrep<T> run) {
		String url = "jdbc:mysql://localhost:3306/pizzadb";
		try (Connection connection = DriverManager.getConnection(url, "root", "");) {

			return run.exec(connection);
		} catch (SQLException e) {
			Logger.getLogger(PizzaDaoJDBC.class.getName()).severe(e.getMessage());
			throw new PizzaException(e);
		}

	}

	@Override
	public List<Pizza> findAllPizzas() throws PizzaException {
		List<Pizza> listPizzas = new ArrayList<Pizza>();
		return execute((Statement statement) -> {
			Pizza.setNbPizzas(0);
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
			return listPizzas;
		});
	}

	@Override
	public void saveNewPizza(Pizza pizza) throws PizzaException {
		executePrep((Connection connection) -> {
			PreparedStatement addPizzaSt = connection
					.prepareStatement("INSERT INTO PIZZA (CODE, NOM, PRIX, CATEGORIE) VALUES (?,?,?,?)");
			addPizzaSt.setString(1, pizza.getCode());
			addPizzaSt.setString(2, pizza.getNom());
			addPizzaSt.setDouble(3, pizza.getPrix());
			addPizzaSt.setString(4, pizza.getCatP());
			addPizzaSt.executeUpdate();
			return Void.TYPE;
		});

	}

	@Override
	public void updatePizza(String codePizza, Pizza pizza) throws PizzaException {
		executePrep((Connection connection) -> {
			PreparedStatement updatePizzaSt = connection
					.prepareStatement("UPDATE PIZZA SET ID=?,CODE=?,NOM=?,PRIX=?,CATEGORIE=? WHERE CODE = ?");
			updatePizzaSt.setInt(1, pizza.getId());
			updatePizzaSt.setString(2, pizza.getCode());
			updatePizzaSt.setString(3, pizza.getNom());
			updatePizzaSt.setDouble(4, pizza.getPrix());
			updatePizzaSt.setString(5, pizza.getCatP());
			updatePizzaSt.setString(6, codePizza);
			updatePizzaSt.executeUpdate();
			return Void.TYPE;
		});
	}

	@Override
	public void deletePizza(String codePizza) throws PizzaException {
		executePrep((Connection connection) -> {
			PreparedStatement deletePizzaSt = connection.prepareStatement("DELETE FROM PIZZA WHERE CODE = ?");
			deletePizzaSt.setString(1, codePizza);
			deletePizzaSt.executeUpdate();
			return Void.TYPE;
		});

	}

	@Override
	public List<Pizza> findAllPizzasCat() {
		List<Pizza> listPizzas = findAllPizzas();
		Comparator<Pizza> comp = Comparator.comparing(Pizza::getCatP);
		List<Pizza> list = listPizzas.stream().sorted(comp).collect(Collectors.toList());
		return list;
	}

	@Override
	public Pizza findAllPizzasPrix() {
		List<Pizza> listPizzas = findAllPizzas();
		Comparator<Pizza> comp = Comparator.comparing(Pizza::getPrix);
		Optional<Pizza> pizza = listPizzas.stream().max(comp);
		if (pizza.isPresent()) {
			return pizza.get();
		} else {
			return null;
		}
	}

	public void saveDataPizza() throws PizzaException {
		PizzaDaoTableau tableau = new PizzaDaoTableau();
		List<Pizza> listPizzas = tableau.findAllPizzas();
		executePrep((Connection connection) -> {
			connection.setAutoCommit(false);
			List<List<Pizza>> list = partition(listPizzas, 3);

			for (List<Pizza> liste : list) {
				for (Pizza pizza : liste) {
					PreparedStatement addPizzaSt = connection
							.prepareStatement("INSERT INTO PIZZA (CODE, NOM, PRIX, CATEGORIE) VALUES (?,?,?,?)");
					addPizzaSt.setString(1, pizza.getCode());
					addPizzaSt.setString(2, pizza.getNom());
					addPizzaSt.setDouble(3, pizza.getPrix());
					addPizzaSt.setString(4, pizza.getCatP());
					//addPizzaSt.executeUpdate();
					if (!addPizzaSt.execute()) {
						connection.rollback();
					}

				}
				connection.commit();
				}
			

			//connection.rollback();
			//connection.commit();
			return Void.TYPE;
		});

	}

	public static <T> List<List<T>> partition(final List<T> list, final int size) {
		if (list == null) {
			throw new NullPointerException("List must not be null");
		}
		if (size <= 0) {
			throw new IllegalArgumentException("Size must be greater than 0");
		}
		return new Partition<T>(list, size);
	}

	private static class Partition<T> extends AbstractList<List<T>> {
		private final List<T> list;
		private final int size;

		private Partition(final List<T> list, final int size) {
			this.list = list;
			this.size = size;
		}

		@Override
		public List<T> get(int arg0) {
			return null;
		}

		@Override
		public int size() {
			return 0;
		}
	}
}
