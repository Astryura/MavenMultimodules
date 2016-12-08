package fr.pizzeria.ihm;

import fr.pizzeria.model.CategoriePizza;
import fr.pizzeria.model.Pizza;

public class UpdatePizza implements Action {

	private IhmUtil ihmUtil;
	CategoriePizza catP;

	public UpdatePizza(IhmUtil ihmUtil) {
		super();
		this.ihmUtil = ihmUtil;
	}

	@Override
	public void doAction() {
		System.out.println("Modifiez votre Pizza. \nCode de la Pizza que vous voulez modifier");
		String codePizza = ihmUtil.getScanner().next();
		System.out.println("Nouveau Code de la Pizza");
		String codeU = ihmUtil.getScanner().next();
		System.out.println("Nouveau Nom de la Pizza");
		String nomU = ihmUtil.getScanner().next();
		System.out.println("Nouveau Prix de la Pizza");
		String prixU = ihmUtil.getScanner().next();
		System.out.println("Nouvelle Cat�gorie de la Pizza : V = VIANDE, P = POISSON, SV = SANS_VIANDE");
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
			Pizza pizza = new Pizza(codeU, nomU, Double.parseDouble(prixU), catP);
			ihmUtil.getPizzaDao().updatePizza(codePizza, pizza);
		} else {
			System.out.println("Rentrez une cat�gorie valide");
		}

	}

	@Override
	public void describeAction() {
		System.out.println("3. Mettre � jour une pizza");
	}

}
