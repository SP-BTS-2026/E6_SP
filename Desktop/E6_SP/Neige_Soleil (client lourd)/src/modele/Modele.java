package modele;

import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.sql.SQLException;

import controleur.Appartement;
import controleur.Client;
import controleur.Materiel;

import controleur.Personnel;
import controleur.Proprietaire;
import controleur.Reservation;
import controleur.User;

public class Modele 
{
	private static BDD uneBdd = new BDD("root", "", "neige_soleil", "localhost");
	
	public static User selectWhereUser (String email, String mdp) {
		User unUser = null;
		String requete = "select * from user where email='" + email + "' and mdp='" + mdp + "';";
		
		try {
			uneBdd.seConnecter();
			Statement unStat = uneBdd.getMaConnection().createStatement(); 
			ResultSet unResultat = unStat.executeQuery(requete); 
			if(unResultat.next()) {
				unUser = new User (
					unResultat.getInt("id_perso"), 
					unResultat.getString("nom"),
					unResultat.getString("prenom"),
					unResultat.getString("email"),
					unResultat.getString("mdp"),
					unResultat.getString("tel"),
					unResultat.getString("role")
				);
			}
			unStat.close();
			uneBdd.SeDeConnecter();
		}
		catch(SQLException exp) {
			System.out.println("Erreur d'exécution de la requete :" + requete);
		}
		return unUser;
	}

	public static void updateUserProfil(User unUser) {
		String requete = "UPDATE user SET "
				+ "nom = '" + unUser.getNom() + "', "
				+ "prenom = '" + unUser.getPrenom() + "', "
				+ "email = '" + unUser.getEmail() + "', "
				+ "tel = '" + unUser.getTel() + "', "
				+ "mdp = '" + unUser.getMdp() + "' "
				+ "WHERE id_perso = " + unUser.getId_perso() + ";";
		executerRequete1(requete);
	}

	/***************** Requete sur la table Appartement ****************/
	public static void insertAppartement(Appartement unAppartement) {
		String requete = "INSERT INTO appartement (num_appart, type_appart, surface, exposition, distance_pistes, capacite_accueil, prix_hebdo, image, id_proprio) VALUES ("
				+ "'" + unAppartement.getNum_appart() + "', "
				+ "'" + unAppartement.getType_appart() + "', "
				+ unAppartement.getSurface() + ", "
				+ "'" + unAppartement.getExposition() + "', "
				+ unAppartement.getDistance_pistes() + ", "
				+ unAppartement.getCapacite_accueil() + ", "
				+ unAppartement.getPrix_hebdo() + ", "
				+ "'" + unAppartement.getImage() + "', "
				+ unAppartement.getIdproprio() + ");";

		executerRequete1(requete);
	}

	public static void deleteAppartement(int id_appart) {
		String requete = "DELETE FROM appartement WHERE id_appart = " + id_appart + ";";
		executerRequete1(requete);
	}
	
	public static void updateAppartement(Appartement unAppartement) {
		String requete = "UPDATE appartement SET "
				+ "num_appart = '" + unAppartement.getNum_appart() + "', "
				+ "type_appart = '" + unAppartement.getType_appart() + "', "
				+ "surface = " + unAppartement.getSurface() + ", "
				+ "exposition = '" + unAppartement.getExposition() + "', "
				+ "distance_pistes = " + unAppartement.getDistance_pistes() + ", "
				+ "capacite_accueil = " + unAppartement.getCapacite_accueil() + ", "
				+ "prix_hebdo = " + unAppartement.getPrix_hebdo() + ", "
				+ "image = '" + unAppartement.getImage() + "', "
				+ "id_proprio = " + unAppartement.getIdproprio() + " "
				+ "WHERE id_appart = " + unAppartement.getId_appart() + ";";
		executerRequete1(requete);
	}

	public static ArrayList<Appartement> SelectAllAppartements (String filtre){
		ArrayList<Appartement> lesAppartements = new ArrayList<Appartement>();
		String requete;

		if (filtre.equals("")) {
			requete = "select * from Appartement;";
		} else {
			requete = "select * from Appartement where "
					+ "type_appart like '%" + filtre + "%' "
					+ "or exposition like '%" + filtre + "%' "
					+ "or surface like '%" + filtre + "%';";
		}

		try {
			uneBdd.seConnecter();
			Statement unStat = uneBdd.getMaConnection().createStatement();
			ResultSet desResultats = unStat.executeQuery(requete);
			
			while(desResultats.next()) { 
				Appartement unAppartement = new Appartement(
					desResultats.getInt("id_appart"),
					desResultats.getString("num_appart"),
					desResultats.getString("type_appart"),
					desResultats.getFloat("surface"),
					desResultats.getInt("capacite_accueil"),
					desResultats.getString("exposition"),
					desResultats.getInt("distance_pistes"),
					desResultats.getFloat("prix_hebdo"),
					desResultats.getString("image"),
					desResultats.getInt("id_proprio")
				);
				lesAppartements.add(unAppartement);
			}
			unStat.close();
			uneBdd.SeDeConnecter();
		}
		catch(SQLException exp) {
			System.out.println("Erreur d'execution de la requete :" + requete);
		}
		return lesAppartements;
	}

	public static void executerRequete1(String requete) {
		try {
			uneBdd.seConnecter();
			Statement unStat = uneBdd.getMaConnection().createStatement();
			unStat.execute(requete);
			unStat.close();
			uneBdd.SeDeConnecter();
		}
		catch (SQLException exp) {
			System.out.println("Erreur d'exécution de la requete :" + requete);
		}
	}

	/******************** Requete sur la table Propriétaire ****************/
	public static void insertProprietaire(Proprietaire unProprietaire) {
		String requete = "INSERT INTO proprietaire VALUES (NULL,'"
				+ unProprietaire.getNom() + "','"
				+ unProprietaire.getPrenom() + "','"
				+ unProprietaire.getEmail() + "','"
				+ unProprietaire.getAdresse() + "','"
				+ unProprietaire.getTel() + "','"
				+ unProprietaire.getIban() + "');";

		executerRequete1(requete);
	}

	public static ArrayList<Proprietaire> selectAllProprietaires(String filtre){
		ArrayList<Proprietaire> lesProprietaires = new ArrayList<Proprietaire>();
		String requete;
		if (filtre.equals("")) {
			requete = "Select * from proprietaire;";
		} else {
			requete = "Select * from proprietaire where nom like '%" + filtre + "%' or prenom like '%" + filtre + "%';";
		}
		
		try {
			uneBdd.seConnecter();
			Statement unStat = uneBdd.getMaConnection().createStatement(); 
			ResultSet desResultats = unStat.executeQuery(requete);
			while (desResultats.next()) {
				Proprietaire unProprietaire = new Proprietaire(
					desResultats.getInt("id_proprio"),
					desResultats.getString("nom"), 
					desResultats.getString("prenom"),
					desResultats.getString("adresse"), // Correspondance exacte avec l'IHM
					desResultats.getString("email"),
					desResultats.getString("tel"),
					desResultats.getString("iban")
				);
				lesProprietaires.add(unProprietaire);
			}
			unStat.close();
			uneBdd.SeDeConnecter();
		}
		catch (SQLException exp) {
			System.out.println("Erreur d'execution " + requete);
		}   
		return lesProprietaires;
	}

	// AJOUT : La méthode de suppression est maintenant fonctionnelle !
	public static void deleteProprietaire(int id_proprio) {
		String requete = "DELETE FROM proprietaire WHERE id_proprio = " + id_proprio + ";";
		executerRequete1(requete);
	}

	// AJOUT : La méthode de modification est maintenant fonctionnelle !
	public static void updateProprietaire(Proprietaire unProprietaire) {
		String requete = "UPDATE proprietaire SET "
				+ "nom = '" + unProprietaire.getNom() + "', "
				+ "prenom = '" + unProprietaire.getPrenom() + "', "
				+ "adresse = '" + unProprietaire.getAdresse() + "', "
				+ "email = '" + unProprietaire.getEmail() + "', "
				+ "tel = '" + unProprietaire.getTel() + "', "
				+ "iban = '" + unProprietaire.getIban() + "' "
				+ "WHERE id_proprio = " + unProprietaire.getIdProprio() + ";";
		executerRequete1(requete);
	}

	/***************** Requete sur la table Materiel ****************/
	public static void insertMateriel(Materiel unMateriel) {
		String requete = "INSERT INTO materiel VALUES (NULL, '"
				+ unMateriel.getLibelle_mat() + "', '"
				+ unMateriel.getType_mat() + "', '"
				+ unMateriel.getEtat() + "', '"
				+ unMateriel.getPrix_jour() + "');";
		executerRequete1(requete);
	}

	public static void deleteMateriel(int id_mat) {
		String requete = "DELETE FROM materiel WHERE id_mat = " + id_mat + ";";
		executerRequete1(requete);
	}

	public static void updateMateriel(Materiel unMateriel) {
		String requete = "UPDATE materiel SET "
				+ "libelle_mat = '" + unMateriel.getLibelle_mat() + "', "
				+ "type_mat = '" + unMateriel.getType_mat() + "', "
				+ "etat = '" + unMateriel.getEtat() + "', "
				+ "prix_jour = '" + unMateriel.getPrix_jour() + "' "
				+ "WHERE id_mat = " + unMateriel.getId_mat() + ";";
		executerRequete1(requete);
	}

	public static ArrayList<Materiel> SelectAllMateriels(String filtre) {
		ArrayList<Materiel> lesMateriels = new ArrayList<Materiel>();
		String requete;

		if (filtre.equals("")) {
			requete = "select * from materiel;";
		} else {
			requete = "select * from materiel where "
					+ "libelle_mat like '%" + filtre + "%' "
					+ "or type_mat like '%" + filtre + "%' "
					+ "or etat like '%" + filtre + "%' "
					+ "or prix_jour like '%" + filtre + "%';";
		}

		try {
			uneBdd.seConnecter();
			Statement unStat = uneBdd.getMaConnection().createStatement();
			ResultSet desResultats = unStat.executeQuery(requete);

			while (desResultats.next()) {
				Materiel unMateriel = new Materiel(
						desResultats.getString("libelle_mat"),
						desResultats.getString("type_mat"),
						desResultats.getString("etat"),
						desResultats.getFloat("prix_jour"),
						desResultats.getInt("id_mat")
				);
				lesMateriels.add(unMateriel);
			}
			unStat.close();
			uneBdd.SeDeConnecter();
		} catch (SQLException exp) {
			System.out.println("Erreur d'execution de la requete :" + requete);
		}
		return lesMateriels;
	}

	/***************** Requete sur la table Client ****************/	
	public static void insertClient(Client unClient) {
		String requete = "INSERT INTO CLIENT VALUES (NULL, '"
				+ unClient.getNom() + "', '"
				+ unClient.getPrenom() + "', '"
				+ unClient.getEmail() + "', '"
				+ unClient.getTel() + "');";

		executerRequete1(requete);
	}

	public static ArrayList<Client> selectAllClients(String filtre) {
		ArrayList<Client> lesClients = new ArrayList<Client>();
		String requete = "SELECT * FROM CLIENT WHERE nom LIKE '%" + filtre + "%' OR prenom LIKE '%" + filtre + "%';";

		try {
			uneBdd.seConnecter();
			Statement unStat = uneBdd.getMaConnection().createStatement();
			ResultSet rs = unStat.executeQuery(requete);

			while (rs.next()) {
				Client unClient = new Client(
					rs.getInt("id_client"),
					rs.getString("nom"),
					rs.getString("prenom"),
					rs.getString("email"),
					rs.getString("tel")
				);
				lesClients.add(unClient);
			}
			unStat.close();
			uneBdd.SeDeConnecter();
		} catch (SQLException exp) {
			System.out.println("Erreur selectAllClients : " + exp);
		}
		return lesClients;
	}

	public static void deleteClient(int id_client) {
		String requete = "DELETE FROM CLIENT WHERE id_client=" + id_client + ";";
		executerRequete1(requete);
	}

	public static void updateClient(Client unClient) {
		String requete = "UPDATE CLIENT SET nom='"
				+ unClient.getNom() + "', prenom='"
				+ unClient.getPrenom() + "', email='"
				+ unClient.getEmail() + "', tel='"
				+ unClient.getTel() + "' WHERE id_client="
				+ unClient.getIdclient() + ";";
		executerRequete1(requete);
	}

	
	/*************** RESERVATION *****************/
	public static void insertReservation(Reservation r) {
		String requete = "INSERT INTO RESERVATION (date_debut_loc, date_fin_loc, nb_personnes, id_client, id_appart, id_employe) VALUES ("
				+ "'" + r.getDate_debut_loc() + "', "
				+ "'" + r.getDate_fin_loc() + "', "
				+ r.getNb_personnes() + ", "
				+ r.getId_client() + ", "
				+ r.getId_appart() + ", "
				+ r.getId_employe() + ");";
		executerRequete1(requete);
	}

	public static ArrayList<Reservation> selectAllReservations(String filtre) {
		ArrayList<Reservation> lesReservations = new ArrayList<>();
		String requete = "SELECT * FROM RESERVATION;";

		try {
			uneBdd.seConnecter();
			Statement unStat = uneBdd.getMaConnection().createStatement();
			ResultSet rs = unStat.executeQuery(requete);

			while (rs.next()) {
				Reservation r = new Reservation(
					rs.getInt("id_reser"),
					rs.getString("date_debut_loc"),
					rs.getString("date_fin_loc"),
					rs.getInt("nb_personnes"),
					rs.getInt("id_client"),
					rs.getInt("id_appart"),
					rs.getInt("id_employe")
				);
				lesReservations.add(r);
			}
			unStat.close();
			uneBdd.SeDeConnecter();
		} catch (SQLException exp) {
			System.out.println("Erreur selectAllReservations : " + exp);
		}
		return lesReservations;
	}

	public static void deleteReservation(int id_reser) {
		String requete = "DELETE FROM RESERVATION WHERE id_reser=" + id_reser + ";";
		executerRequete1(requete);
	}

	public static void updateReservation(Reservation r) {
		String requete = "UPDATE RESERVATION SET date_debut_loc='"
				+ r.getDate_debut_loc() + "', date_fin_loc='"
				+ r.getDate_fin_loc() + "', nb_personnes="
				+ r.getNb_personnes() + ", id_client="
				+ r.getId_client() + ", id_appart="
				+ r.getId_appart() + ", id_employe="
				+ r.getId_employe()
				+ " WHERE id_reser=" + r.getId_reser() + ";";
		executerRequete1(requete);
	}
	
	
	
	/*************** PERSONNEL *****************/
	public static void insertPersonnel(Personnel p) {
		String requete = "INSERT INTO PERSONNEL (nom, prenom, tel, role) VALUES ('"
				+ p.getNom() + "', '"
				+ p.getPrenom() + "', '"
				+ p.getTel() + "', '"
				+ p.getRole() + "');";
		executerRequete1(requete);
	}

	public static ArrayList<Personnel> selectAllPersonnel(String filtre) {
		ArrayList<Personnel> lesEmployes = new ArrayList<>();
		String requete = "SELECT * FROM PERSONNEL WHERE nom LIKE '%" + filtre + "%' OR prenom LIKE '%" + filtre + "%';";

		try {
			uneBdd.seConnecter();
			Statement unStat = uneBdd.getMaConnection().createStatement();
			ResultSet rs = unStat.executeQuery(requete);

			while (rs.next()) {
				Personnel p = new Personnel(
					rs.getInt("id_employe"),
					rs.getString("nom"),
					rs.getString("prenom"),
					"", 
					rs.getString("tel"),
					rs.getString("role")
				);
				lesEmployes.add(p);
			}
			unStat.close();
			uneBdd.SeDeConnecter();
		} catch (SQLException exp) {
			System.out.println("Erreur selectAllPersonnel : " + exp);
		}
		return lesEmployes;
	}

	public static void deletePersonnel(int id_employe) {
		String requete = "DELETE FROM PERSONNEL WHERE id_employe=" + id_employe + ";";
		executerRequete1(requete);
	}

	public static void updatePersonnel(Personnel p) {
		String requete = "UPDATE PERSONNEL SET nom='"
				+ p.getNom() + "', prenom='"
				+ p.getPrenom() + "', tel='"
				+ p.getTel() + "', role='"
				+ p.getRole()
				+ "' WHERE id_employe=" + p.getId_employe() + ";";
		executerRequete1(requete);
	}
	
	/*************** Facture   *****************/
	public static String[] recupererDonneesFacture(int id_reser) {
		String[] donnees = new String[5];
		String requete = "SELECT C.nom, C.prenom, A.type_appart, A.prix_hebdo, DATEDIFF(R.date_fin_loc, R.date_debut_loc) AS duree "
					   + "FROM RESERVATION R "
					   + "JOIN CLIENT C ON R.id_client = C.id_client "
					   + "JOIN APPARTEMENT A ON R.id_appart = A.id_appart "
					   + "WHERE R.id_reser = " + id_reser + ";";
		try {
			uneBdd.seConnecter();
			Statement unStat = uneBdd.getMaConnection().createStatement();
			ResultSet rs = unStat.executeQuery(requete);
			
			if (rs.next()) {
				donnees[0] = rs.getString("nom");
				donnees[1] = rs.getString("prenom");
				donnees[2] = rs.getString("type_appart");
				donnees[3] = rs.getString("prix_hebdo");
				donnees[4] = rs.getString("duree");
			}
			unStat.close();
			uneBdd.SeDeConnecter();
		} catch (SQLException exp) {
			System.out.println("Erreur dans recupererDonneesFacture : " + exp);
		}
		return donnees;
	}
}