package donjon;

import java.util.ArrayList;

public class Monstre extends Personnage {

	public Monstre(int vie, int force, int piece) {
		super(vie, force, piece);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void attaquer(Personnage joueur) {
		joueur = (Joueur)joueur;
		System.out.println("Le monstre contre-attaque avec une force de " + getForce() + " !");
		joueur.setVie(joueur.getVie() - getForce());
	}
	public String toString() {
		return "vie = " + vie + " - force = " + force + " - piece = " + piece;
	}


}
