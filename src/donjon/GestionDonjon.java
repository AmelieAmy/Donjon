package donjon;

import java.util.ArrayList;
import java.util.Scanner;

public class GestionDonjon {
	Scanner in = new Scanner(System.in);
	private String[][] donjon;
	Personnage joueur = new Joueur(10, 5, 0);
    ArrayList<Personnage> MonstersList = new ArrayList<Personnage>();
    ArrayList<String> ObjetsList = new ArrayList<String>();
    String contenuSalle = " ";
    int indexMonstre = 0;
	
	public GestionDonjon() {

		System.out.print("Saisir le nombre de lignes du donjon : ");
		int lignes = in.nextInt();

		System.out.print("Saisir le nombre de colonnes du donjon: ");
		int colonnes = in.nextInt();

		generationDonjon(lignes, colonnes);
		menuJoueur();
	}

	public String objectMonsterOrVoidGeneration(int i, int j) {
		String oMV = "0";
		int randomCase = randInt(0, 100);
		if(randomCase < 10) {
			oMV = "M";
		} else if ((randomCase > 10) && (randomCase < 50)) {
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
					donjon[i][j + 1] = objectMonsterOrVoidGeneration(i,j);
					
				}
				if(j == colonnes-2) {
					donjon[i][j + 1] = "S";
				}
				j++;
				break;
			case 3:
				if (donjon[i + 1][j].equals("#")) {
					donjon[i + 1][j] = objectMonsterOrVoidGeneration(i, j);
				
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
						donjon[i - 1][j] = objectMonsterOrVoidGeneration(i, j);
						
					}
					i = i - 1;
				} 
				break;
			case 2:
				if (donjon[i][j + 1].equals("#")) {
					donjon[i][j + 1] = objectMonsterOrVoidGeneration(i, j);
					
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
        System.out.println("C - Attaquer");
        System.out.println("D - Utiliser un objet");
        System.out.println("-------------  MENU D'ACTIONS  -------------");
        System.out.println("");
        System.out.println("Que voulez-vous faire ?");

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
	        	deplacer();
	        	break;
	        case "C":
	        	combat();
	        	break;
	        case "D":
//	        	System.out.println("m�thode utiliser un objet");
	        	((Joueur)joueur).useObject(donjon, lignes, colonnes,ObjetsList);
	        	menuJoueur();
	        	break;
        }
    }	
    
    public void deplacer() {
    	if(MonstersList.isEmpty()) {
    		ObjetsList.clear();
    		contenuSalle = ((Joueur)joueur).deplacer(donjon);
    		monstersAndObjectsGeneration(contenuSalle);
    		}
    		else {
    			System.out.println("Il reste des monstres dans la salle. Tuez les pour avancer.");
    		}
    		affichageCarte(donjon);
	        menuJoueur();
    }
    
    public void combat() {
    	if(contenuSalle != "M") {
    		System.out.println("Pas de monstre dans la salle !");
    	}
    	else {
        	System.out.println(MonstersList);
        	System.out.println("Quel monstre attaquer ?");
        	indexMonstre = in.nextInt();
        	((Joueur)joueur).attaquer(MonstersList.get(indexMonstre));
    	}
    	if(MonstersList.get(indexMonstre).getVie() <= 0) {
    		MonstersList.remove(indexMonstre);
    	}
    	else {
    		MonstersList.get(indexMonstre).attaquer(joueur);
    		if(joueur.getVie() <= 0) {
    			System.out.println("Vous êtes mort. GAME OVER.");
    		}
    		else {
    			System.out.println("Il vous reste " + joueur.getVie() + " point de vie.");
    		}
    	}
    	if(MonstersList.isEmpty()) {
    		System.out.println("Vous avez tuez tous les monstres ! Vous pouvez desormais accéder à la salle suivante !");
    	}
    	menuJoueur();
    }

    public void monstersAndObjectsGeneration(String roomType) {

    	if(roomType.contentEquals("O")) {
        	for (int i = 0; i < randInt(1, 3); i++) { // genere un nombre un nombre d'item de 1 � 3.
            	ObjetsList.add(randomisedObjects()); // genere le type d'item
    		}
    	}
    	
    	if(roomType.equals("M")) {
        	for (int i = 0; i < randInt(1, 3); i++) { // genere un nombre un nombre de monstre de 1 � 3.
        		MonstersList.add(new Monstre(randInt(3, 10), randInt(3, 5), randInt(20, 150)));
    		}
    	}
    	
    	System.out.println(ObjetsList);
    	System.out.println(MonstersList);
    }

	public String randomisedObjects() {
		String result = "0";
		int random = randInt(0, 60);
		if(random < 20) {
			result = "Bourse d'or";
		} else if ((random > 21) && (random < 40)) {
			result = "Potion de vie";
		} else {
			result = "Potion de force";
		}
		return result;
	}

	public static int randInt(int min, int max) {
		int x = (int) ((Math.random() * ((max - min) + 1)) + min);
		return x;
	}
}