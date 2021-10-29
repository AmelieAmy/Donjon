package donjon;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.TreeMap;

public class Score {

	public void txt(String nom, int score, LocalDate date) throws IOException {

		// Recuperer les valeurs du fichier txt
		Scanner text = new Scanner(new File("fichierScore"));
		HashMap<Integer, String> listScore = new HashMap<Integer, String>();
		int indice = 0;
		while (text.hasNext()) {
			listScore.put(indice, text.nextLine());
			indice++;
		}
		text.close();

		// inserer une nouvelle valeur et recuperer la taille du score
		String newScore = score + " " + nom + " " + date.format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));

		listScore.put(indice, newScore);

		// stocker les valeurs dans un tableau de string
		String[] tempArray = new String[listScore.size()];
		indice = 0;
		for (String i : listScore.values()) {
			tempArray[indice] = i;
			indice++;
		}
		// nbARanger pour stocker les scores en int
		HashMap<Integer, Integer> nbARanger = new HashMap<Integer, Integer>();

		// Processus de string to int
		for (int i = 0; i < listScore.size(); i++) {
			int tailleScore = 0;
			for (int j = 0; j < tempArray[i].length(); j++) {
				if (tempArray[i].charAt(j) != ' ') {
					tailleScore++;
				} else {
					break;
				}
			}
			nbARanger.put(i, Integer.parseInt(tempArray[i].substring(0, tailleScore)));
		}

		// ranger les scores dans l'ordre décroissant
		ValueComparator bvc = new ValueComparator(nbARanger);
		TreeMap<Integer, Integer> nbRange = new TreeMap<Integer, Integer>(bvc);
		nbRange.putAll(nbARanger);

		// Inserer la nouvelle valeur dans listScore
		FileWriter newText = new FileWriter(new File("fichierScore"));

		for (Integer i : nbRange.keySet()) {
			for (Integer j : listScore.keySet()) {
				if (i == j) {
					newText.write(listScore.get(i) + "\n");
					break;
				}
			}
		}

		newText.flush();
		newText.close();

	}
	

	public void hightScore() throws FileNotFoundException {
		System.out.println("Voici le tableau des hight scores");
		System.out.println("");
		Scanner text = new Scanner(new File("fichierScore"));
		HashMap<Integer, String> listScore = new HashMap<Integer, String>();
		int indice = 0;
		while (text.hasNext() && indice < 10) {
			listScore.put(indice, text.nextLine());
			System.out.println((indice + 1) + " " + listScore.get(indice));
			indice++;
		}
		text.close();
	}
	

	// Cette class permet de comparer les differentes valeurs pour les ranger en
	// decroissant
	class ValueComparator implements Comparator<Integer> {
		Map<Integer, Integer> base;

		public ValueComparator(Map<Integer, Integer> base) {
			this.base = base;
		}

		public int compare(Integer a, Integer b) {
			if (base.get(a) >= base.get(b)) {
				return -1;
			} else {
				return 1;
			}
		}
	}
}
