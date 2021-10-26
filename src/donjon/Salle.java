package donjon;

import java.util.Scanner;

public class Salle {

	private boolean mur;

	public Salle() {
//		mur = GenerationSalle();
	}

	public void GenerationSalle() {

		Scanner in = new Scanner(System.in);

		System.out.print("Saisir le nombre de lignes du donjon : ");
		int lignes = in.nextInt();

		System.out.print("Saisir le nombre de colonnes du donjon: ");
		int colonnes = in.nextInt();

		// d�claration de la matrice
		String[][] donjon = new String[lignes][colonnes];

		for (int i = 0; i < lignes; i++) {
			for (int j = 0; j < colonnes; j++) {
				donjon[i][j] = "X";
			}
		}

		int i = 1;
		int j = 1;
		boolean stop = false;

		while (!stop) {
			if (i == lignes - 1 || j == colonnes - 1) {
				stop = true;
				break;
			}
			switch (randInt(2, 3)) {
			case 2:
				if (donjon[i][j + 1].equals("X")) {
					donjon[i][j + 1] = " ";
					
				}
				if(j == colonnes-2) {
					donjon[i][j + 1] = "S";
				}
				j++;
				break;
			case 3:
				if (donjon[i + 1][j].equals("X")) {
					donjon[i + 1][j] = " ";
				
				}
				if(i == lignes-2) {
					donjon[i+1][j] = "S";
				}
				i++;
			}
			

		}
		i = lignes - 2;
		j = 1;
		stop = false;

		while (!stop) {
			if (i == lignes - 1 || j == colonnes - 1) {
				stop = true;
				break;
			}
			switch (randInt(1, 2)) {
			case 1:
				if (i != 1) {
					if (donjon[i - 1][j].equals("X")) {
						donjon[i - 1][j] = " ";
						
					}
					i = i - 1;
				} 
				break;
			case 2:
				if (donjon[i][j + 1].equals("X")) {
					donjon[i][j + 1] = " ";
					
				}
				j++;
			
				break;
			}

		}
		int nbVeines = randInt(1,lignes);
		
		for(int y = 0; y < nbVeines; y++) {
			veines(lignes,colonnes,donjon);
		}


		for (i = 0; i < donjon.length; i++) {
			for (int k = 0; k < donjon[0].length; k++) {
				System.out.print(donjon[i][k] + " ");
			}
			System.out.println();
		}

		// fermeture du scanner
		in.close();
	}
	public void veines(int lignes, int colonnes, String donjon[][]) {
		int i = randInt(1, lignes-2);
		int j = randInt(1, colonnes-2);
		int max = 0;
			if(lignes < colonnes) {
				max = lignes;
			}else {
				max = colonnes;
			}
		int tailleVeine = randInt(1, max-2);
		
		for(int x = 1; x < tailleVeine; x++) {
			switch (randInt(1,4)) {
			case 1:
				if (i != 1) {
					if (donjon[i - 1][j].equals("X")) {
						donjon[i - 1][j] = " ";
					}
					i = i - 1;
				} 
				break;
			case 2:
				if(j != colonnes -2 ) {
					if (donjon[i][j + 1].equals("X")) {
						donjon[i][j + 1] = " ";
					}
					j++;
				}
				break;
			case 3:
				if(i != lignes -2) {
					if (donjon[i + 1][j].equals("X")) {
						donjon[i + 1][j] = " ";		
					}
					i++;
				}
				break;
			case 4:
				if(donjon[i][j-1].equals("X")) {
					donjon[i][j-1] = " ";
				}
				break;
			}
		}
		
	}

	public static int randInt(int min, int max) {
		int x = (int) ((Math.random() * ((max - min) + 1)) + min);
		return x;
	}
}
