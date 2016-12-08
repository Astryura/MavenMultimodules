package fr.pizzeria.model;

public enum CategoriePizza {

	VIANDE("Viande"), POISSON("Poisson"), SANS_VIANDE("Sans Viande");
	private String name = "";

	// Constructeur
	CategoriePizza(String name) {
		this.name = name;
	}

	public String toString() {
		return name;
	}

}
