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

}
