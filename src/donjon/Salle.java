package donjon;

import java.util.Scanner;

public class Salle {

	private boolean mur;
	
	public Salle() {
//		mur = GenerationSalle();
	}
	
	public static void GenerationSalle() {
		
	    Scanner in = new Scanner(System.in);

	    System.out.print("Saisir le nombre de lignes du donjon : ");
	    int lignes = in.nextInt();
	    
	    System.out.print("Saisir le nombre de colonnes du donjon: ");
	    int colonnes = in.nextInt();
	    
	    //déclaration de la matrice
	    String[][] donjon = new String[lignes][colonnes];

	    for (int i = 0; i < lignes; i++) {
	      for (int j = 0; j < colonnes; j++) {
	    	  donjon[i][j] = "X";
	      }
	    }

	    
	    int x = 1; // valeur temporaire de j
	    int j = 1;
	    
	    for (int i = 1; i < lignes-1; i++) {
	    	if(j == colonnes-1) {
	    		break;
	    	}
	      for (j = x; j < colonnes-1; j++) {
	    	  
	    	  donjon[1][1] = " "; // entrée
	    	  
	    	  boolean enBas = false;
	    	  boolean salleCree = false;
	  	      int count = 0;
	  	    
	    	  while(!salleCree) {
	    		  if(salleCree) {
	    			  break;
	    		  }

    		    int tempResult = randInt(1,4);
    		    System.out.println(tempResult);
//    		    if(count < 30) {
//        		    count++;
//    		    } else {
//    		    	break;
//    		    }
//    		    System.out.println("count = " + count);
		    	  switch(tempResult){
		    	  	case 1:
		    	  		if((donjon[i-1][j].equals("X")) 
	    	  				&& (i != 1)
	    	  				&& ((donjon[i-1][j-1].equals("X"))
	    	  				|| (donjon[i-1][j+1].equals("X")))
		  				){
		    	  			System.out.println("case 1");
							donjon[i-1][j] = ""+randInt(0,1);
							if(donjon[i-1][j].equals("0")) {
								donjon[i-1][j] = " ";
								enBas = true;
								salleCree = true;
								i = i-2;
								x = j;
							} else {
								donjon[i-1][j] = "X";
								break;
							}
		    	  		}
		    		break;
		    	  	case 2:
		    	  		if((donjon[i][j+1].equals("X")) 
	    	  				&& (j != colonnes-1)
	    	  				&& ((donjon[i+1][j+1].equals("X"))
	    	  				|| (donjon[i-1][j+1].equals("X")))
		  				){
		    	  			System.out.println("case 2");
							donjon[i][j+1] = ""+randInt(0,1);
							if(donjon[i][j+1].equals("0")) {
								salleCree = true;
								donjon[i][j+1] = " ";
								x = j;
							} else {
								donjon[i][j+1] = "X";
								break;
							}
		    	  		}
		    		break;
		    	  	case 3:
		    	  		if((donjon[i+1][j].equals("X")) 
	    	  				&& (i != lignes-1)
	    	  				&& ((donjon[i+1][j-1].equals("X"))
	    	  				|| (donjon[i+1][j+1].equals("X")))
		  				){
		    	  			System.out.println("case 3");
							donjon[i+1][j] = ""+randInt(0,1);
							if(donjon[i+1][j].equals("0")) {
								salleCree = true;
								enBas = true;
								donjon[i+1][j] = " ";
								x = j;
							} else {
								donjon[i+1][j] = "X";
								break;
							}
		    	  		}
		    		break;
		    	  	case 4:
		    	  		if((donjon[i][j-1].equals("X")) 
	    	  				&& (j != 1)
	    	  				&& ((donjon[i+1][j-1].equals("X"))
	    	  				|| (donjon[i-1][j-1].equals("X")))
		  				){
		    	  			System.out.println("case 4");
							donjon[i][j-1] = ""+randInt(0,1);
							if(donjon[i][j-1].equals("0")) {
								salleCree = true;
								donjon[i][j-1] = " ";
								if(j != 1 ) {
									j = j-2;
								}
							} else {
								donjon[i][j-1] = "X";
								break;
							}
		    	  		}
		    		break;
		    	  }
	    	  }
	    	  System.out.println("x = " + x);
	    	  System.out.println("j = " + j);
	    	  System.out.println("i = " + i);
	    	  if(enBas) {
	    		  break;
	    	  }
  	      }
      }
	    
//	    for (int i = 1; i < lignes-1; i++) {
//		      for (int j = x; j < colonnes-1; j++) {
//		    	  
//		    	  donjon[1][1] = " "; // entrée
//		    	  
//		    	  donjon[i][j+1] = ""+randInt(0,1);
//		    	  
//		    	  if(donjon[i][j+1].equals("0")) {
//			    	  donjon[i][j+1] = " "
//		    		  x = j;
//		    	  } else {
//			    	  donjon[i][j+1] = "X";
//			    	  donjon[i+1][j] = " ";
//		    		  x = j;
//			    	  break;    	  
//		    	  }  
//		      }
//		    }
	    
	    for (int i = 0; i < donjon.length; i++) {
	      for (int k = 0; k < donjon[0].length; k++) {
	        System.out.print(donjon[i][k] + " ");
	      }
	      System.out.println();
	    }
	    
	    // fermeture du scanner
	    in.close();
	  }

	public static int randInt(int min, int max) {
		int x = (int) ((Math.random() * ((max - min) + 1)) + min);
		return x;
	}
}
