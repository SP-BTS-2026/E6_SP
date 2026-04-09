<?php
require_once "Modele/modele.php";
 
class Controleur {
    private $unModele;
 
    public function __construct() {
        $this->unModele = new Modele();
    }
<<<<<<< HEAD:Controleur/controleur.class.php

    public function getAppartementsVedettes() {
        return $this->unModele->getAppartementsVedettes();
    }

=======
 
    public function getAppartementsVedettes() {
        return $this->unModele->getAppartementsVedettes();
    }
 
>>>>>>> c0083c2cf535a24ff4c4bf1eb1fc276f16f990f7:Desktop/E6_SP/client leger/Controleur/controleur.class.php
    /* ==========================================================
       SECTION : GESTION DES APPARTEMENTS
       ========================================================== */
 
    public function getAppartement($id) {
        return $this->unModele->getAppartementById($id);
    }
 
    public function afficherCatalogue() {
        return $this->unModele->getTousLesAppartements();
    }
 
    /* ==========================================================
<<<<<<< HEAD:Controleur/controleur.class.php
       SECTION : CRUD PROPRIETAIRE
       ========================================================== */

    public function getAppartsByProprio($id_proprio) {
        return $this->unModele->getAppartsByProprio($id_proprio);
    }

    public function ajouterAppartement($data) {
        return $this->unModele->ajouterAppartement($data);
    }

    public function modifierAppartement($id_appart, $data) {
        return $this->unModele->modifierAppartement($id_appart, $data);
    }

    public function supprimerAppartement($id_appart, $id_proprio) {
        return $this->unModele->supprimerAppartement($id_appart, $id_proprio);
    }

    /* ==========================================================
=======
>>>>>>> c0083c2cf535a24ff4c4bf1eb1fc276f16f990f7:Desktop/E6_SP/client leger/Controleur/controleur.class.php
       SECTION : GESTION DU PANIER / RESERVATIONS
       ========================================================== */
 
    public function ajouterAuPanier($id_client, $id_appart, $date_debut, $date_fin) {
        return $this->unModele->ajouterAuPanier($id_client, $id_appart, $date_debut, $date_fin);
    }
 
    public function getPanierByClient($id_client) {
        return $this->unModele->getPanierByClient($id_client);
    }
 
    public function supprimerReservation($id_reser) {
        $this->unModele->supprimerReservation($id_reser);
    }
<<<<<<< HEAD:Controleur/controleur.class.php

    public function getHistorique($id_client) {
        return $this->unModele->getHistoriqueComplet($id_client);
    }

=======
 
    public function getHistorique($id_client) {
        return $this->unModele->getHistoriqueComplet($id_client);
    }
 
>>>>>>> c0083c2cf535a24ff4c4bf1eb1fc276f16f990f7:Desktop/E6_SP/client leger/Controleur/controleur.class.php
    /* ==========================================================
       SECTION : UTILISATEURS / CONNEXION
       ========================================================== */
 
    public function login($email, $mdp) {
        return $this->unModele->login($email, $mdp);
    }
 
    public function getUserDetails($email) {
        return $this->unModele->getUserDetails($email);
    }
<<<<<<< HEAD:Controleur/controleur.class.php

    public function getUserById($id) {
        return $this->unModele->getUserById($id);
    }

    public function inscrireUser($nom, $prenom, $email, $tel, $mdp, $role) {
        $this->unModele->inscrireClient($nom, $prenom, $email, $tel, $mdp, $role);
    }

    /* ==========================================================
       SECTION : FACTURATION
       ========================================================== */

=======
 
    public function getUserById($id) {
        return $this->unModele->getUserById($id);
    }
 
    public function inscrireUser($nom, $prenom, $email, $tel, $mdp, $role) {
        $this->unModele->inscrireClient($nom, $prenom, $email, $tel, $mdp, $role);
    }
 
    /* ==========================================================
       SECTION : FACTURATION
       ========================================================== */
 
    // $lesIds = tableau de tous les id_reser du panier
>>>>>>> c0083c2cf535a24ff4c4bf1eb1fc276f16f990f7:Desktop/E6_SP/client leger/Controleur/controleur.class.php
    public function validerReservationEtFacturer($lesIds, $montantTotal) {
        foreach ($lesIds as $id_reser) {
            $this->unModele->insererFacture($id_reser, $montantTotal);
            $this->unModele->validerStatutReservation($id_reser);
        }
        unset($_SESSION['panier']);
    }
<<<<<<< HEAD:Controleur/controleur.class.php

=======
 
    // Recuperer les details d'une facture pour l'affichage
>>>>>>> c0083c2cf535a24ff4c4bf1eb1fc276f16f990f7:Desktop/E6_SP/client leger/Controleur/controleur.class.php
    public function getFacture($id_reser) {
        return $this->unModele->getFactureByReser($id_reser);
    }
}
?>
