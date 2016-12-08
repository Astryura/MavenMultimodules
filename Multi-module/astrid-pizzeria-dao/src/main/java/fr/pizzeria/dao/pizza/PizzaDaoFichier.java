package fr.pizzeria.dao.pizza;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import fr.pizzeria.model.CategoriePizza;
import fr.pizzeria.model.Pizza;

public class PizzaDaoFichier implements PizzaDao {

	List<Pizza> listPizzas = new ArrayList<Pizza>();

	public PizzaDaoFichier() {
		List<String> listFichiers = lire("data/");
		listFichiers.forEach(s -> {
			File f = new File(s);
			if (f.canRead()) {
				try {
					InputStream ips = new FileInputStream(f);
					InputStreamReader ipsr = new InputStreamReader(ips);
					BufferedReader br = new BufferedReader(ipsr);
					String ligne = br.readLine();
					String code = f.getName().replaceAll(".txt", "");
					String a[] = ligne.split(";");
					Pizza pizza = new Pizza(Integer.parseInt(a[0]), code, a[1], Double.parseDouble(a[2]),
							CategoriePizza.valueOf(a[3].toUpperCase().replaceAll(" ", "_")));
					listPizzas.add(pizza);
					br.close();
				} catch (FileNotFoundException e) {
					e.printStackTrace();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		});
		Comparator<Pizza> comp = Comparator.comparing(Pizza::getId);
		listPizzas = listPizzas.stream().sorted(comp).collect(Collectors.toList());
	}

	@Override
	public List<Pizza> findAllPizzas() {
		return listPizzas;
	}

	public List<Pizza> findAllPizzasCat() {
		Comparator<Pizza> comp = Comparator.comparing(Pizza::getCatP);
		List<Pizza> list = listPizzas.stream().sorted(comp).collect(Collectors.toList());
		return list;
	}

	public Pizza findAllPizzasPrix() {
		Comparator<Pizza> comp = Comparator.comparing(Pizza::getPrix);
		Optional<Pizza> pizza = listPizzas.stream().max(comp);
		if (pizza.isPresent()) {
			return pizza.get();
		} else {
			return null;
		}
	}

	@Override
	public void saveNewPizza(Pizza pizza) {
		Comparator<Pizza> comp = Comparator.comparing(Pizza::getId);
		Optional<Pizza> p = listPizzas.stream().max(comp);
		if (p.isPresent()) {
			Pizza max = p.get();
			pizza.setId(max.getId() + 1);
			listPizzas.add(pizza);
			int nbPizza = Pizza.getNbPizzas();
			nbPizza++;
			Pizza.setNbPizzas(nbPizza);
			stockage(pizza);
		}
	}

	@Override
	public void updatePizza(String codePizza, Pizza pizza) {

		listPizzas.forEach(p -> {
			if (p.getCode().equals(codePizza)) {
				pizza.setId(p.getId());
				int a = listPizzas.indexOf(p);
				listPizzas.set(a, pizza);
				destockage(p);
				stockage(pizza);
			}
		});

	}

	@Override
	public void deletePizza(String codePizza) {

		Optional<Pizza> findFirst = listPizzas.stream().filter(p -> p.getCode().equals(codePizza)).findFirst();

		if (findFirst.isPresent()) {
			Pizza pizza = findFirst.get();
			listPizzas.remove(pizza);
			destockage(pizza);
		}

		int nbPizza = Pizza.getNbPizzas();
		nbPizza--;
		Pizza.setNbPizzas(nbPizza);

	}

	public void stockage(Pizza pizza) {

		File file = new File("data/" + pizza.getCode() + ".txt");
		Path path = file.toPath();
		try (BufferedWriter writer = Files.newBufferedWriter(path)) {
			writer.write(pizza.getId() + ";" + pizza.getNom() + ";" + pizza.getPrix() + ";" + pizza.getCatP());
		}

		catch (java.io.IOException e) {
			e.printStackTrace();
		}
	}

	public void destockage(Pizza pizza) {
		File fichier = new File("data/" + pizza.getCode() + ".txt");
		fichier.delete();
	}

	public static List<String> lire(String dir) {

		final List<String> files = new ArrayList<>();

		Path path = Paths.get(dir);
		try {
			DirectoryStream<Path> stream = Files.newDirectoryStream(path);
			for (Path entry : stream) {
				files.add(entry.toString());
			}
			stream.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return files;

	}

}