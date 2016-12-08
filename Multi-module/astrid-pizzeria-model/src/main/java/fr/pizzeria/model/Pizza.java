package fr.pizzeria.model;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

public class Pizza {
	int id;
	String code;
	String nom;
	double prix;
	static int nbPizzas;
	CategoriePizza catP;

	public Pizza(int id, String code, String nom, double prix, CategoriePizza catP) {
		super();
		this.id = id;
		this.code = code;
		this.nom = nom;
		this.prix = prix;
		nbPizzas++;
		this.catP = catP;
	}

	public Pizza(String code, String nom, double prix, CategoriePizza catP) {
		super();
		this.code = code;
		this.nom = nom;
		this.prix = prix;
		this.catP = catP;
	}

	public Pizza() {
		// TODO Auto-generated constructor stub
	}

	public String getCatP() {
		return catP.toString();
	}

	public void setCatP(CategoriePizza catP) {
		this.catP = catP;
	}

	public static int getNbPizzas() {
		return nbPizzas;
	}

	public static void setNbPizzas(int nbPizzas) {
		Pizza.nbPizzas = nbPizzas;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public double getPrix() {
		return prix;
	}

	public void setPrix(double prix) {
		this.prix = prix;
	}

	@Override
	public boolean equals(Object obj) {
		if (obj == null) {
			return false;
		}
		if (obj == this) {
			return true;
		}
		if (obj.getClass() != getClass()) {
			return false;
		}
		Pizza rhs = (Pizza) obj;
		return new EqualsBuilder().append(id, rhs.id).append(code, rhs.code).append(nom, rhs.nom).append(prix, rhs.prix)
				.append(catP, rhs.catP).isEquals();
	}

	@Override
	public int hashCode() {
		// you pick a hard-coded, randomly chosen, non-zero, odd number
		// ideally different for each class
		return new HashCodeBuilder(17, 37).append(nom).append(id).toHashCode();
	}
}
