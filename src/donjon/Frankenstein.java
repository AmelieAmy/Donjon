package donjon;

public class Frankenstein extends Monstre{

	public Frankenstein(int vie, int force, int piece, int i, int j) {
		super(vie, force, piece, i, j);
	}

//	if(!(donjon[((Joueur)joueur).getI()][((Joueur)joueur).getJ()].equals("P"))) {
	
	public static int randInt(int min, int max) {
		int x = (int) ((Math.random() * ((max - min) + 1)) + min);
		return x;
	}
}
