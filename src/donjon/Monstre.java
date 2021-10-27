package donjon;

public class Monstre extends Personnage {

	public Monstre(int vie, int force, int piece) {
		super(vie, force, piece);
		// TODO Auto-generated constructor stub
	}

	@Override
	public String toString() {
		return "vie = " + vie + " - force = " + force + " - piece = " + piece;
	}

	@Override
	public void attaquer() {
		// TODO Auto-generated method stub
		
	}

}
