package fr.pizzeria.ihm;

import java.sql.SQLException;
import java.util.List;

import fr.pizzeria.model.Pizza;

public class ListPizzaCat implements Action {

	private IhmUtil ihmUtil;

	public ListPizzaCat(IhmUtil ihmUtil) {
		super();
		this.ihmUtil = ihmUtil;
	}

	@Override
	public void doAction() throws SQLException {
		List<Pizza> list = this.ihmUtil.getPizzaDao().findAllPizzasCat();
		list.forEach(p -> {
			System.out
					.println(p.getId() + " " + p.getCode() + " " + p.getNom() + " " + p.getPrix() + " " + p.getCatP());
		});
		// System.out.println(Pizza.getNbPizzas() + " Pizzas enregistr�es");
	}

	@Override
	public void describeAction() {
		System.out.println("5. Lister les pizzas par Cat�gories");
	}

}
