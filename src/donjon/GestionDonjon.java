package donjon;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class GestionDonjon {
	Scanner in = new Scanner(System.in);
	private String[][] donjon;
	Personnage joueur = new Joueur(10, 5, 0);
    ArrayList<Personnage> monstersList = new ArrayList<Personnage>();
    ArrayList<String> objetsList = new ArrayList<String>();
    String contenuSalle = " ";
    private int indexMonstre = 0;
	private boolean banditMancho = false;
	private HashMap<String, Integer> monstersPositionI = new HashMap<String, Integer>();
	private HashMap<String, Integer> monstersPositionJ = new HashMap<String, Integer>();
	int lignes = 33;
	int colonnes = 31;
	
	public GestionDonjon() {

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
		if(randomCase < 5) {
			oMV = "F";
		} else if ((randomCase > 5) && (randomCase < 10)) {
			oMV = "~";
		} else if ((randomCase > 10) && (randomCase < 15)) {
			oMV = "O";
		} else if ((randomCase > 16) && (randomCase < 18) && !banditMancho) {
			oMV = "$";
		} else {
			oMV = " ";
		}
		if(oMV.equals("$")) {
			banditMancho = true;
		}
		return oMV;
	}
	
	public void getMonstersPosition(int lignes, int colonnes) {
		int count = 0;
		for (int i = 1; i < lignes; i++) {
			for (int j = 1; j < colonnes; j++) {
				if(donjon[i][j].equals("F")) {
					monstersPositionI.put("F"+count, i);
					monstersPositionJ.put("F"+count, j);
					count++;
				}
				if(donjon[i][j].equals("~")) {
					monstersPositionI.put("~"+count, i);
					monstersPositionJ.put("~"+count, j);
					count++;
				}
			}
		}
	}
	
//	public void clearMonstersPosition(int lignes, int colonnes) {
//		for (int i = 0; i < monstersPositionI.size(); i++) {
//			if(monstersPositionI.get("~"+i) != null) {
//				donjon[monstersPositionI.get("~"+i)][monstersPositionJ.get("~"+i)] = " ";
//			}
//		}
//	}

	public void deplacementFrankenstein(int lignes, int colonnes) {
		int i = 0;
		int j = 0;
		boolean movingFrank = false;
		for (int k = 0; k < monstersPositionI.size(); k++) {
			if(monstersPositionI.get("F"+k) != null) {
				i = monstersPositionI.get("F"+k);
				j = monstersPositionJ.get("F"+k);
				movingFrank = false;
				boolean notmovingFrank1 = false;
				boolean notmovingFrank2 = false;
				boolean notmovingFrank3 = false;
				boolean notmovingFrank4 = false;
				while (!movingFrank) {
					switch(randInt(1, 4)) {
						case 1:
							if(donjon[i-1][j].equals(" ")) {
								donjon[i-1][j] = "F";
								donjon[i][j] = " ";
								movingFrank=true;
							}
							notmovingFrank1 = true;
							break;
						case 2:
							if(donjon[i][j+1].equals(" ")) {
								donjon[i][j+1] = "F";
								donjon[i][j] = " ";
								movingFrank=true;
							}
							notmovingFrank2 = true;
							break;
						case 3:
							if(donjon[i+1][j].equals(" ")) {
								donjon[i+1][j] = "F";
								donjon[i][j] = " ";
								movingFrank=true;
							}
							notmovingFrank3 = true;
							break;
						case 4:
							if(donjon[i][j-1].equals(" ")) {
								donjon[i][j-1] = "F";
								donjon[i][j] = " ";
								movingFrank=true;
							}
							notmovingFrank4 = true;
							break;
					}
					if(notmovingFrank1 && notmovingFrank2 && notmovingFrank3 && notmovingFrank4) {
						donjon[i][j] = "F";
						movingFrank=true;
					}
				}
			}
		}
	}
	
	public void generationDonjon(int lignes, int colonnes) {

		// declaration de la matrice
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

		// Récupération des positions des monstres
		getMonstersPosition(lignes, colonnes);
		
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
		bandit();
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
        choixJoueur(choix, monstersList);
    }

    public void choixJoueur(String choix, ArrayList<Personnage> monstersList) {
        switch(choix.toUpperCase()) {
	        case "A":
	        	((Joueur)joueur).regarder(contenuSalle, objetsList, monstersList);
	    		affichageCarte(donjon);
		        menuJoueur();
	        	break;
	        case "B":
	        	deplacementJoueur();
	        	break;
	        case "C":
	        	combat();
	        	break;
	        case "D":
	        	((Joueur)joueur).useObject(donjon, objetsList);
	    		affichageCarte(donjon);
	        	menuJoueur();
	        	break;
        }
    }
    
    public void bandit() {
    	int prix = randInt(300, 800);
    	String objet = randomisedObjects();
    	if(contenuSalle.equals("$")){
    		System.out.println("Il y a un bandit manchot dans le coin ... Il reclame " + prix + " pieces pour vous donner un objet aleatoire. Souhaitez vous lui donner ? ( oui | non )");
    		if(in.next().equalsIgnoreCase("oui")) {
    			if(joueur.getPiece() < prix) {
    				System.out.println("Vous n'avez pas assez d'or. Le bandit disparait dans les profondeur du donjon...");
    			}
    			else {
    				System.out.println("Vous donnez " + prix + " pieces d'or au bandit , il vous reste " + joueur.getPiece() + " pieces d'or");
    				if(randInt(1,10) == 1) {
    					System.out.println("Le bandit s'enfuit avec votre argent sans rien vous donner ! Quel idee de faire confiance a un bandit...");
    				}
    				else {
    					System.out.println("Le bandit vous donne " + objet + " que vous utilisez immediatement");
    					if(objet.equals("Bourse d'or")) {
    						System.out.println("Le bandit vous donne une bourse d'or !");
    						int rand=randInt(1,1000);
    						if(rand < prix) {
    							System.out.println("Quelle arnaque ! Cette bourse est plus petite que celle que vous lui avez donne...");
    						}
    						joueur.setPiece(joueur.getPiece() + rand);
    						System.out.println(rand + " pieces d'or. Vous avez maintenant " + joueur.getPiece() + " pieces d'or.");
    					}
    					if(objet.equals("Potion de vie")) {
    						System.out.println("Le bandit vous donne une potion de vie !");
    						int rand=randInt(1,10);
    						joueur.setVie(joueur.getVie() + rand);
    						System.out.println("Potion de soin de " + rand + " point de vie. Vous avez maintenant " + joueur.getVie() + " points de vie.");
    					}
    					if(objet.equals("Potion de force")) {
    						System.out.println("Le bandit vous donne une potion de force !");
    						int rand=randInt(0,5);
    						if(rand == 0) {
    							System.out.println("C'etait de l'eau ... il vous a bien eu");
    						}
    						else {
    						joueur.setForce(joueur.getForce() + rand);
    						System.out.println("Potion de force de " + rand + " point de force. Vous avez maintenant " + joueur.getForce() + " points de force.");
    						}
    					}
    				}
    			}
    		}
    		else {
    			System.out.println("Le bandit disparait dans les profondeur du donjon...");
    		}
    		contenuSalle = " ";
    	}
    }

    public void deplacementJoueur() {
    	if(monstersList.isEmpty()) {
    		objetsList.clear();
    		contenuSalle = ((Joueur)joueur).deplacer(donjon, joueur);
    		monstersAndObjectsGeneration(contenuSalle);
		}
		else {
			System.out.println("Il reste des monstres dans la salle. Tuez les pour avancer.");
		}
		getMonstersPosition(lignes, colonnes);
		deplacementFrankenstein(lignes, colonnes);
		affichageCarte(donjon);
        menuJoueur();
    }
    
    public void combat() {
    	if((!contenuSalle.equals("F")) && (!contenuSalle.equals("~"))) {
    		System.out.println("Pas de monstre dans la salle !");
    	}
    	else {
        	for(int i = 0; i < monstersList.size(); i++) {
        		System.out.println("Monstre " + i + " " + monstersList.get(i));
        	}
        	System.out.println("Quel monstre attaquer ?");
        	indexMonstre = in.nextInt();
        	((Joueur)joueur).attaquer(monstersList.get(indexMonstre));
        	
        	if(monstersList.get(indexMonstre).getVie() <= 0) {
        		joueur.setPiece(joueur.getPiece() + monstersList.get(indexMonstre).getPiece());
        		System.out.println("Vous avez recupere " + monstersList.get(indexMonstre).getPiece() + " pieces d'or vous avez maintenant " + joueur.getPiece() + " pieces d'or");
        		monstersList.remove(indexMonstre);
        	}
        	else {
        		monstersList.get(indexMonstre).attaquer(joueur);
        		if(joueur.getVie() <= 0) {
        			System.out.println("Vous etes mort. GAME OVER.");
        			System.exit(0);
        		}
        		else {
        			System.out.println("Il vous reste " + joueur.getVie() + " point de vie.");
        		}
        	}
        	if(monstersList.isEmpty()) {
        		System.out.println("Vous avez tuez tous les monstres ! Vous pouvez desormais acceder a la salle suivante !");
        	}
    	}
    	menuJoueur();
    }

    public void monstersAndObjectsGeneration(String contenuSalle) {

    	if(contenuSalle.equals("O")) {
        	for (int i = 0; i < randInt(1, 3); i++) { // genere un nombre d'item de 1 a 3.
            	objetsList.add(randomisedObjects()); // genere le type d'item
    		}
    	}
    	
    	if(contenuSalle.equals("F")) {
        	for (int i = 0; i < randInt(1, 3); i++) { // genere un nombre de Frankenstein de 1 a 3.
        		monstersList.add(new Frankenstein(randInt(3, 10), randInt(3, 5), randInt(20, 150), 0, 0));
    		}
    	}
    	if(contenuSalle.equals("~")) {
        	for (int i = 0; i < randInt(1, 3); i++) { // genere un nombre de BlopInfernal de 1 a 3.
        		monstersList.add(new BlopInfernal(randInt(3, 10), randInt(3, 5), randInt(20, 150), 0, 0));
    		}
    	}
    	
    }

	public String randomisedObjects() {
		String result = "0";
		int random = randInt(0, 60);
		if(random < 20) {
			result = "Bourse d'or";
		} else if ((random > 20) && (random < 40)) {
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