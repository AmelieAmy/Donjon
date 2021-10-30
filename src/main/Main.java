package main;

import java.io.IOException;
import java.util.Scanner;

import donjon.GestionDonjon;
import donjon.Score;

public class Main {

	public static void main(String[] args) throws IOException {
		
		Scanner in =new Scanner(System.in);
		String choix = null;
		
		do {
	        System.out.println("");
	        System.out.println("-------------  MENU PRINCIPAL  -------------");
	        System.out.println("A - Afficher les 10 meilleurs scores");
	        System.out.println("B - Nouvelle partie");
	        System.out.println("C - Quitter");
	        System.out.println("-------------  MENU PRINCIPAL  -------------");
	        System.out.println("");
	        System.out.println("Que voulez-vous faire ?");

	        choix = in.next();
	        
	        switch(choix.toUpperCase()) {
		        case "A":
		        	Score score = new Score();
		        	score.hightScore();
		        	break;
		        case "B":
		        	GestionDonjon gestionDonjon = new GestionDonjon();
		        	break;
		        case "C":
		        	System.out.println("Bye bye");
		        	System.exit(0);
		        	break;
	        }
	        
		} while(choix.equalsIgnoreCase("A") || choix.equalsIgnoreCase("B"));
		
	}

}
