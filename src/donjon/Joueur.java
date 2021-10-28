package donjon;

import java.util.ArrayList;
import java.util.Scanner;

public class Joueur extends Personnage {
	private int i;
	private int j;

	public Joueur(int vie, int force, int piece, int i, int j) {
		super(vie, force, piece);
		this.i = i;
		this.j = j;
	}

	public void setI(int i) {
		this.i = i;
	}

	public void setJ(int j) {
		this.j = j;
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
					System.out.println("Gagne ! Vous avez termine le niveau avec " + vie + " points de vie et " + piece + " pieces d'or.");
					System.exit(0);
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
					System.out.println("Gagne ! Vous avez termine le niveau avec " + vie + " points de vie et " + piece + " pieces d'or.");
					System.exit(0);
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
					System.out.println("Gagne ! Vous avez termine le niveau avec " + vie + " points de vie et " + piece + " pieces d'or.");
					System.exit(0);
				}
				nextSalle = donjon[i + 1][j];
				donjon[i][j] = " ";
				donjon[i + 1][j] = "P";
				i = i + 1;	
			}
			break;
		case 4:
			if(donjon[i][j-1].equals("#")) {
				System.out.println("Un mur se presente devant vous, vous ne pouvez pas avancer dans cette direction");
			}
			else {
				if(donjon[i][j-1].equals("S")) {
					System.out.println("Gagne ! Vous avez termine le niveau avec " + vie + " points de vie et " + piece + " pieces d'or.");
					System.exit(0);
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
	
	public void useObject(String donjon[][],ArrayList<String> objetsList) {
		boolean onGround=true;
		Scanner in = new Scanner(System.in);
		
		
		if (!objetsList.isEmpty()) {
			System.out.println("Il y a " + objetsList +" par terre");
			System.out.println("Voulez-vous ramasser le(s) objet(s) ?(oui ou non)");
			String response=in.next();
			if(response.equals("oui")) {
				do {
					System.out.println("Que voulez-vous ramasser ? (indice de l'objet)");
					System.out.println(objetsList);
					int objet=in.nextInt();
					objetsList.get(objet);
					
					if(objetsList.get(objet).equals("Bourse d'or")) {
						int rand=randInt(10,50);
						setPiece(getPiece() + rand);
						System.out.println("Vous r�cuperez "+rand+ " pieces.\nVous en avez au total "+getPiece());
						objetsList.remove(objet);
						
					}else if(objetsList.get(objet).equals("Potion de vie")) {
						int rand=randInt(2,5);
						setVie(getVie() + rand);
						System.out.println("Vous r�cuperez "+rand+ " de vie.\nVous en avez au total "+getVie());
						objetsList.remove(objet);
						
					}else if(objetsList.get(objet).equals("Potion de force")) {
						int rand=randInt(1,3);
						setForce(getForce() + rand);
						System.out.println("Vous avez booste de "+rand+ " votre force.\nVous en avez au total "+getForce());
						objetsList.remove(objet);
					}else {
						break;
					}
					
					if(objetsList.isEmpty()) {
						onGround=false;
					}
				}while(onGround);
			}
		}else {
			System.out.println("Il n'y a rien par terre");
		}
	
	}
	
	public static int randInt(int min, int max) {
		int x = (int) ((Math.random() * ((max - min) + 1)) + min);
		return x;
	}

    public void regarder(String contenuSalle, ArrayList objetsList, ArrayList monstersList) {
    	if(contenuSalle.equals("O")) {
            System.out.println(" ");
    		System.out.println("Vous regardez autour de vous...");
    		System.out.println("A vos pieds se trouve une " + objetsList.get(0) + ".");
            System.out.println(" ");
    		if(objetsList.size() > 1) {
        		System.out.println("Un peu plus loin vous trouvez aussi une " + objetsList.get(1) + ".");
                System.out.println(" ");
    		}
    		if(objetsList.size() > 2) {
        		System.out.println("Decidement la chance vous souris, sur le chemin vous trebuchez sur une " + objetsList.get(2) + ".");
                System.out.println(" ");
    		}
    	} else if(contenuSalle.equals("M")) {
            System.out.println(" ");
    		System.out.println("Devant vous se dresse " + monstersList.size() + " monstres.");
    		System.out.println("Etrangement vous voyez les caracteristiques planer au dessus de leurs tetes :");
            System.out.println(" ");
        	for(int i = 0; i < monstersList.size(); i++) {
        		System.out.println("Monstre " + i + " " + monstersList.get(i));
                System.out.println(" ");
        	}
    	} else {
            System.out.println(" ");
    		System.out.println("Cette salle semble vide");
            System.out.println(" ");
    	}
    }
    
}
