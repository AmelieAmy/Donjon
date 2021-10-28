package donjon;

public class Monstre extends Personnage {
	protected int i;
	protected int j;

	public Monstre(int vie, int force, int piece, int i, int j) {
		super(vie, force, piece);
		this.i = i;
		this.j = j;
	}

	public int getI() {
		return i;
	}
	
	public int getJ() {
		return j;
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
