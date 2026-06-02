package controleur;

import java.util.ArrayList;
import modele.Modele;

public class Controleur 
{
	public static User selectWhereUser(String email, String mdp) {
		return Modele.selectWhereUser(email, mdp);
	}
	
	// AJOUT : Méthode de liaison pour mettre à jour le profil de l'utilisateur
	public static void updateUserProfil(User unUser) {
		Modele.updateUserProfil(unUser);
	}
	
	//------------------ Proprietaire ------------------//
	public static void insertProprietaire(Proprietaire unProprietaire) {
		Modele.insertProprietaire(unProprietaire);
	}
	
	public static ArrayList<Proprietaire> selectAllProprietaires(String filtre) {
		return Modele.selectAllProprietaires(filtre);
	}
	
	public static void deleteProprietaire(int id_proprio) {
		Modele.deleteProprietaire(id_proprio);
	}
	
	public static void updateProprietaire(Proprietaire unProprietaire) {
		Modele.updateProprietaire(unProprietaire);
	}
		
	//-------------------- Appartement ---------------//
	public static void insertAppartement(Appartement unAppartement) {
		Modele.insertAppartement(unAppartement);
	}
	
	public static ArrayList<Appartement> selectAllAppartements(String filtre) {
		return Modele.SelectAllAppartements(filtre);
	}
	
	public static void deleteAppartement(int id_appart) {
		Modele.deleteAppartement(id_appart);
	}
	
	public static void updateAppartement(Appartement unAppartement) {
		Modele.updateAppartement(unAppartement);
	}
	
	//-------------------- Materiel ---------------//
	public static void insertMateriel(Materiel unMateriel) {
		Modele.insertMateriel(unMateriel);
	}
	
	public static ArrayList<Materiel> selectAllMateriels(String filtre) {
		return Modele.SelectAllMateriels(filtre);
	}
	
	public static void deleteMateriel(int id_mat) {
		Modele.deleteMateriel(id_mat);
	}
	
	public static void updateMateriel(Materiel unMateriel) {
		Modele.updateMateriel(unMateriel);
	}
	
	//-------------------- Client ---------------//
	public static void insertClient(Client unClient) {
		Modele.insertClient(unClient);
	}

	public static ArrayList<Client> selectAllClients(String filtre) {
		return Modele.selectAllClients(filtre);
	}

	public static void deleteClient(int id_client) {
		Modele.deleteClient(id_client);
	}

	public static void updateClient(Client unClient) {
		Modele.updateClient(unClient);
	}
	
	

	//-------------------- Reservation ---------------//
	public static void insertReservation(Reservation r) {
		Modele.insertReservation(r);
	}

	public static ArrayList<Reservation> selectAllReservations(String filtre) {
		return Modele.selectAllReservations(filtre);
	}

	public static void deleteReservation(int id_reser) {
		Modele.deleteReservation(id_reser);
	}

	public static void updateReservation(Reservation r) {
		Modele.updateReservation(r);
	}

	

	

	//-------------------- Facture ---------------//
	public static String[] recupererDonneesFacture(int id_reser) {
		return Modele.recupererDonneesFacture(id_reser);
	}
	
	//-------------------- Personnel ---------------//
	public static void insertPersonnel(Personnel p) {
		Modele.insertPersonnel(p);
	}

	public static ArrayList<Personnel> selectAllPersonnel(String filtre) {
		return Modele.selectAllPersonnel(filtre);
	}

	public static void deletePersonnel(int id_employe) {
		Modele.deletePersonnel(id_employe);
	}

	public static void updatePersonnel(Personnel p) {
		Modele.updatePersonnel(p);
	}
}