package donjon;

import java.util.ArrayList;
import java.util.Scanner;

public class Joueur extends Personnage {
	private int i;
	private int j;

	public Joueur(int vie, int force, int piece) {
		super(vie, force, piece);
		this.i = 1;
		this.j = 1;
	}

	public int getI() {
		return i;
	}
	

	public int getJ() {
		return j;
	}

	@Override
	public void attaquer(Personnage perso) {
		Monstre monstre = null;
		if(perso instanceof Monstre) {
			 monstre = (Monstre)perso;
		}
		System.out.println("Vous attaquez le monstre avec une force de " + force);
		monstre.setVie(monstre.getVie() - force);
		if(monstre.getVie() <= 0) {
			System.out.println("Le monstre est mort");
		} else {
			System.out.println("La vie du monstre est maintenant de " + monstre.getVie());
		}
	}
	
	public String deplacer(String donjon[][]) {
		
		System.out.println("Dans quel direction souhaitez vous aller ?");
		System.out.println("1 = haut");
		System.out.println("2 = droite");
		System.out.println("3 = bas");
		System.out.println("4 = gauche");
		Scanner in = new Scanner(System.in);
		int response = in.nextInt();
		String nextSalle = " ";
		switch(response) {
		case 1:
			if(donjon[i - 1][j].equals("#")) {
				System.out.println("Un mur se presente devant vous, vous ne pouvez pas avancer dans cette direction");
			}
			else {
				if(donjon[i - 1][j].equals("S")) {
					System.out.println("Gagné ! Vous avez terminé le niveau avec " + vie + " points de vie et " + piece + " pièces d'or.");
				}
				nextSalle = donjon[i - 1][j];
				donjon[i][j] = " ";
				donjon[i - 1][j] = "P";
				i = i - 1;
			}
			break;
		case 2:
			if(donjon[i][j + 1].equals("#")) {
				System.out.println("Un mur se presente devant vous, vous ne pouvez pas avancer dans cette direction");
			}
			else {
				if(donjon[i][j + 1].equals("S")) {
					System.out.println("Gagné ! Vous avez terminé le niveau avec " + vie + " points de vie et " + piece + " pièces d'or.");
				}
				nextSalle = donjon[i][j+1];
				donjon[i][j] = " ";
				donjon[i][j+1] = "P";
				j = j + 1;
			}
			break;
		case 3:
			if(donjon[i + 1][j].equals("#")) {
				System.out.println("Un mur se presente devant vous, vous ne pouvez pas avancer dans cette direction");
			}
			else {
				if(donjon[i + 1][j].equals("S")) {
					System.out.println("Gagné ! Vous avez terminé le niveau avec " + vie + " points de vie et " + piece + " pièces d'or.");
				}
				nextSalle = donjon[i + 1][j];
				donjon[i][j] = " ";
				donjon[i + 1][j] = "P";
				i = i + 1;	
			}
			break;
		case 4:
			if(donjon[i][j-1].equals("#")) {
				System.out.println("Un mur se présente devant vous, vous ne pouvez pas avancer dans cette direction");
			}
			else {
				if(donjon[i][j-1].equals("S")) {
					System.out.println("Gagné ! Vous avez terminé le niveau avec " + vie + " points de vie et " + piece + " pièces d'or.");
				}
				nextSalle = donjon[i][j-1];
				donjon[i][j] = " ";
				donjon[i][j-1] = "P";
				j = j - 1;
			}
			break;
		default:
			break;
		}
		return nextSalle;
	}

    public void regarder(String contenuSalle, ArrayList objetsList, ArrayList monstersList) {
    	if(contenuSalle.equals("O")) {
            System.out.println(" ");
    		System.out.println("Vous regardez autour de vous...");
    		System.out.println("A vos pieds se trouve une " + objetsList.get(0) + ".");
            System.out.println(" ");
    		if(objetsList.size() > 0) {
        		System.out.println("Un peu plus loin vous trouvez aussi une " + objetsList.get(1) + ".");
                System.out.println(" ");
    		}
    		if(objetsList.size() > 1) {
        		System.out.println("Decidement la chance vous souris, sur le chemin vous trebuchez sur une " + objetsList.get(2) + ".");
                System.out.println(" ");
    		}
    	} else if(contenuSalle.equals("M")) {
            System.out.println(" ");
    		System.out.println("Devant vous se dresse " + monstersList.size() + " monstres.");
    		System.out.println("Etrangement vous voyez les caracteristiques planer au dessus de leurs tetes.");
            System.out.println(" ");
    		if(monstersList.size() < 1) {
        		System.out.println("Voici le monstre " + monstersList.get(0) + ".");
                System.out.println(" ");
    		} else {
        		System.out.println("Premier monstre " + monstersList.get(0) + ".");
                System.out.println(" ");
    		}
    		if(monstersList.size() > 0) {
        		System.out.println("Deuxieme monstre " + monstersList.get(1) + ".");
                System.out.println(" ");
    		}
    		if(monstersList.size() > 1) {
        		System.out.println("Troisieme monstre " + monstersList.get(2) + ".");
                System.out.println(" ");
    		}
    	} else {
            System.out.println(" ");
    		System.out.println("Cette salle semble vide");
            System.out.println(" ");
    	}
    }
    
}
