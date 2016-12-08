package fr.pizzeria.ihm;

import fr.pizzeria.dao.pizza.PizzaDaoJDBC;

public class ImportePizza implements Action {

	private IhmUtil ihmUtil;

	public ImportePizza(IhmUtil ihmUtil) {
		super();
		this.ihmUtil = ihmUtil;
	}

	@Override
	public void doAction() {
		System.out.println("Voulez vous importez les données dans la base de données (oui ou non)");
		String choix = ihmUtil.getScanner().next();
		if (choix.equalsIgnoreCase("oui")) {
			PizzaDaoJDBC jdbc = new PizzaDaoJDBC();
			jdbc.saveDataPizza();
		}
	}

	@Override
	public void describeAction() {
		System.out.println("7. Importer les fichiers");
	}

}
