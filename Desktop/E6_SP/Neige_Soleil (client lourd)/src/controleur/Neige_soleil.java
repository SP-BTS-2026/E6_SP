package controleur;

import vue.VueConnexion;
import vue.VueGenerale;

public class Neige_soleil {
	private static VueConnexion uneVueConnexion;
	private static VueGenerale uneVueGenerale;

	public static void main(String[] args) {
		uneVueConnexion = new VueConnexion();
	

	}
	public static void rendreVisibleVueConnexion(boolean action) {
		uneVueConnexion.setVisible(action);
	}
	
	// Dans Neige_soleil.java
	public static void creerDetruireVueGenerale(boolean action, User unUser) {
	    if (action == true) {
	        // On passe l'utilisateur au constructeur de VueGenerale
	        uneVueGenerale = new VueGenerale(unUser); 
	        uneVueGenerale.setVisible(true);
	    } else {
	        if (uneVueGenerale != null) {
	            uneVueGenerale.dispose();
	        }
	    }
	}
}
