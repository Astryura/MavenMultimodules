package fr.pizzeria.ihm;

import java.util.HashMap;
import java.util.Map;

public class MainMenu {

	Map<Integer, Action> actions = new HashMap<Integer, Action>();
	private IhmUtil ihmUtil;

	public MainMenu(IhmUtil ihmUtil) {
		actions.put(0, new ListPizza(ihmUtil));
		actions.put(1, new AddPizza(ihmUtil));
		actions.put(2, new UpdatePizza(ihmUtil));
		actions.put(3, new DeletePizza(ihmUtil));
		actions.put(4, new ListPizzaCat(ihmUtil));
		actions.put(5, new ListPizzaPrix(ihmUtil));
		actions.put(6, new ImportePizza(ihmUtil));
		actions.put(7, new ExitMenu());
		this.ihmUtil = ihmUtil;
	}

	public void displayMenu() throws NumberFormatException {
		actions.forEach((k, v) -> {
			actions.get(k).describeAction();
		});
		parseAndExec();
	}

	private void parseAndExec() throws NumberFormatException {
		String input = ihmUtil.getScanner().next();

		if (Integer.parseInt(input) <= 7) {
			actions.get((Integer.parseInt(input)) - 1).doAction();
			displayMenu();
		} else if (Integer.parseInt(input) == 99) {
			actions.get(6).doAction();
		}
	}
}
