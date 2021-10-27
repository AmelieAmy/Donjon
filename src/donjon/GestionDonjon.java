package donjon;

import java.util.Scanner;

public class GestionDonjon {

	public String[][] donjon;
	Joueur joueur = new Joueur();
	
	public GestionDonjon() {
		Scanner in = new Scanner(System.in);

		System.out.print("Saisir le nombre de lignes du donjon : ");
		int lignes = in.nextInt();

		System.out.print("Saisir le nombre de colonnes du donjon: ");
		int colonnes = in.nextInt();

		generationDonjon(lignes, colonnes);
		menuJoueur();
	}

	public String objectMonsterOrVoidGeneration() {
		String oMV = "0";
		int randomCase = randInt(0, 100);
		if(randomCase < 10) {
			oMV = "M";
		} else if ((randomCase > 10) && (randomCase < 15)) {
			oMV = "O";
		} else {
			oMV = " ";
		}
		return oMV;
	}
	
	public void generationDonjon(int lignes, int colonnes) {

		// d�claration de la matrice
		donjon = new String[lignes][colonnes];
		
		for (int i = 0; i < lignes; i++) {
			for (int j = 0; j < colonnes; j++) {
				donjon[i][j] = "#";
			}
		}
		
		int i = 1;
		int j = 1;
		boolean stop = false;
		
		donjon[1][1] = "P";
		
		while (!stop) {
			if (i == lignes - 1 || j == colonnes - 1) {
				stop = true;
				break;
			}
			switch (randInt(2, 3)) {
			case 2:
				if (donjon[i][j + 1].equals("#")) {
					donjon[i][j + 1] = objectMonsterOrVoidGeneration();
				}
				if(j == colonnes-2) {
					donjon[i][j + 1] = "S";
				}
				j++;
				break;
			case 3:
				if (donjon[i + 1][j].equals("#")) {
					donjon[i + 1][j] = objectMonsterOrVoidGeneration();
				
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
					if (donjon[i - 1][j].equals("#")) {
						donjon[i - 1][j] = objectMonsterOrVoidGeneration();
						
					}
					i = i - 1;
				} 
				break;
			case 2:
				if (donjon[i][j + 1].equals("#")) {
					donjon[i][j + 1] = objectMonsterOrVoidGeneration();
					
				}
				if(j == colonnes-2) {
					donjon[i][j + 1] = "#";
				}
				j++;
			
				break;
			}

		}
		int nbVeines = randInt(1,lignes);
		
		for(int y = 0; y < nbVeines; y++) {
			veines(lignes,colonnes,donjon);
		}


		// affichage de la carte
		affichageCarte(donjon);

		// fermeture du scanner
//		in.close();
	}
	
	public void veines(int lignes, int colonnes, String donjon[][]) {
		int i = randInt(2, lignes-2);
		int j = randInt(2, colonnes-2);
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
					if (donjon[i - 1][j].equals("#")) {
						donjon[i - 1][j] = " ";
					}
					i = i - 1;
				} 
				break;
			case 2:
				if(j != colonnes -2 ) {
					if (donjon[i][j + 1].equals("#")) {
						donjon[i][j + 1] = " ";
					}
					j++;
				}
				break;
			case 3:
				if(i != lignes -2) {
					if (donjon[i + 1][j].equals("#")) {
						donjon[i + 1][j] = " ";		
					}
					i++;
				}
				break;
			case 4:
				if(donjon[i][j-1].equals("#")) {
					donjon[i][j-1] = " ";
				}
				break;
			}
		}
		
	}

	public void affichageCarte(String[][] donjon) {
		for (int i = 0; i < donjon.length; i++) {
			for (int k = 0; k < donjon[0].length; k++) {
				System.out.print(donjon[i][k] + " ");
			}
			System.out.println();
		}
	}
	
	public void menuJoueur() {
        System.out.println("");
        System.out.println("-------------  MENU D'ACTIONS  -------------");
        System.out.println("A - Regarder");
        System.out.println("B - Se deplacer");
        System.out.println("C - Combattre");
        System.out.println("D - Utiliser un objet");
        System.out.println("-------------  MENU D'ACTIONS  -------------");
        System.out.println("");
        System.out.println("Que voulez-vous faire ?");

        Scanner in = new Scanner(System.in);
        String choix = in.next();
        choixJoueur(choix);
    }

    public void choixJoueur(String choix) {
        switch(choix.toUpperCase()) {
	        case "A":
	        	// afficher le nombre d'objet, le type et le nombre de monstre
	        	System.out.println("methode regarder"); 
	        	menuJoueur();
	        	break;
	        case "B":
//        		if(monstersHP <= 0) { // total des HP de tout les monstres
		            joueur.deplacer(donjon);
		            affichageCarte(donjon);
		        	menuJoueur();
//        		}
	        	break;
	        case "C":
	        	System.out.println("methode combattre");
	        	menuJoueur();
	        	break;
	        case "D":
	        	System.out.println("methode utiliser un objet");
	        	menuJoueur();
	        	break;
        }
    }	
	
	public static int randInt(int min, int max) {
		int x = (int) ((Math.random() * ((max - min) + 1)) + min);
		return x;
	}
}