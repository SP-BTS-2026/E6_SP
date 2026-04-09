<?php
require_once "Modele/modele.php";

class Controleur {
    private $unModele;

    public function __construct() {
        $this->unModele = new Modele();
    }

    public function getAppartementsVedettes() {
        return $this->unModele->getAppartementsVedettes();
    }

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

    public function getHistorique($id_client) {
        return $this->unModele->getHistoriqueComplet($id_client);
    }

    /* ==========================================================
       SECTION : UTILISATEURS / CONNEXION
       ========================================================== */

    public function login($email, $mdp) {
        return $this->unModele->login($email, $mdp);
    }

    public function getUserDetails($email) {
        return $this->unModele->getUserDetails($email);
    }

    public function getUserById($id) {
        return $this->unModele->getUserById($id);
    }

    public function inscrireUser($nom, $prenom, $email, $tel, $mdp, $role) {
        $this->unModele->inscrireClient($nom, $prenom, $email, $tel, $mdp, $role);
    }

    /* ==========================================================
       SECTION : FACTURATION
       ========================================================== */

    public function validerReservationEtFacturer($lesIds, $montantTotal) {
        foreach ($lesIds as $id_reser) {
            $this->unModele->insererFacture($id_reser, $montantTotal);
            $this->unModele->validerStatutReservation($id_reser);
        }
        unset($_SESSION['panier']);
    }

    public function getFacture($id_reser) {
        return $this->unModele->getFactureByReser($id_reser);
    }
}
?>
