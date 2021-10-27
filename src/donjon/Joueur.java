package donjon;

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
	public void attaquer() {
		// TODO Auto-generated method stub
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
