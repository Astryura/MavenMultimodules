package fr.pizzeria.ihm;

import java.sql.SQLException;

import fr.pizzeria.model.CategoriePizza;
import fr.pizzeria.model.Pizza;

public class AddPizza implements Action {

	private IhmUtil ihmUtil;
	CategoriePizza catP;

	public AddPizza(IhmUtil ihmUtil) {
		super();
		this.ihmUtil = ihmUtil;
	}

	@Override
	public void doAction() throws SQLException {
		System.out.println("Cr�ez votre Pizza. \nCode de la Pizza");
		String code = ihmUtil.getScanner().next();
		System.out.println("Nom de la Pizza");
		String nom = ihmUtil.getScanner().next();
		System.out.println("Prix de la Pizza");
		String prix = ihmUtil.getScanner().next();
		System.out.println("Cat�gorie de la Pizza : V = VIANDE, P = POISSON, SV = SANS_VIANDE");
		String cat = ihmUtil.getScanner().next();
		if (cat.equals("V")) {
			catP = CategoriePizza.VIANDE;
		} else if (cat.equals("P")) {
			catP = CategoriePizza.POISSON;
		} else if (cat.equals("SV")) {
			catP = CategoriePizza.SANS_VIANDE;
		} else {
			catP = null;
		}
		if (catP != null) {
			Pizza pizza = new Pizza(code, nom, Double.parseDouble(prix), catP);
			ihmUtil.getPizzaDao().saveNewPizza(pizza);
		} else {
			System.out.println("Rentrez une cat�gorie valide");
		}

	}

	@Override
	public void describeAction() {
		System.out.println("2. Ajouter une nouvelle pizza");
	}

}
