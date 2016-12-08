package fr.pizzeria.ihm;

public class ExitMenu implements Action {

	@Override
	public void doAction() {
		System.out.println("AU REVOIR :'(");
	}

	@Override
	public void describeAction() {
		System.out.println("99. Sortir");
	}

}
