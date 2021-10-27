package donjon;

public abstract class Personnage {
	protected int vie;
	protected int force;
	protected int piece;
	
	public Personnage(int vie, int force, int piece) {
		this.vie = vie;
		this.force = force;
		this.piece = piece;
	}

	public abstract void attaquer(Personnage perso);


	public int getVie() {
		return vie;
	}

	public void setVie(int vie) {
		this.vie = vie;
	}

	public int getForce() {
		return force;
	}

	public void setForce(int force) {
		this.force = force;
	}

	public int getPiece() {
		return piece;
	}

	public void setPiece(int piece) {
		this.piece = piece;
	}

}
