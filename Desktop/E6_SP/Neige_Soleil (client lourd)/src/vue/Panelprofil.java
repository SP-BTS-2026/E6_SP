package vue;

import java.awt.Color;
import java.awt.Font;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSeparator;
import controleur.User; // Pense à vérifier si User est dans controleur ou modele dans ton projet

public class Panelprofil extends PanelPrincipal {

    // Éléments de l'interface du profil
    private JLabel lbSouhait = new JLabel();
    private JLabel lbNom = new JLabel("Nom : ");
    private JLabel lbPrenom = new JLabel("Prénom : ");
    private JLabel lbEmail = new JLabel("Email : ");
    private JLabel lbRole = new JLabel("Statut / Rôle : ");

    private JLabel valNom = new JLabel();
    private JLabel valPrenom = new JLabel();
    private JLabel valEmail = new JLabel();
    private JLabel valRole = new JLabel();

    private JSeparator separateur = new JSeparator();

    public Panelprofil(String titre, User unUser) {
        super(titre); // Appel du titre standard en haut
     // TRÈS IMPORTANT : Désactive le gestionnaire pour que les setBounds() fonctionnent !
        this.setLayout(null); 

        // --- CONFIGURATION DU MESSAGE DE BIENVENUE ---
        lbSouhait.setText("Bienvenue dans votre espace, " + unUser.getPrenom() + " !");
        // ... (la suite de ton code reste inchangée)

        // --- CONFIGURATION DU MESSAGE DE BIENVENUE ---
        lbSouhait.setText("Bienvenue dans votre espace, " + unUser.getPrenom() + " !");
        lbSouhait.setFont(new Font("Arial", Font.BOLD, 22));
        lbSouhait.setForeground(new Color(41, 128, 185)); // Un joli bleu pro
        lbSouhait.setBounds(50, 120, 500, 30);
        this.add(lbSouhait);

        // Ligne de séparation esthétique
        separateur.setBounds(50, 170, 700, 10);
        this.add(separateur);

        // --- CONFIGURATION DES LABELS (Design Alignement Gauche) ---
        Font policeLabel = new Font("Arial", Font.BOLD, 16);
        Font policeValeur = new Font("Arial", Font.PLAIN, 16);

        lbNom.setFont(policeLabel);
        lbNom.setBounds(100, 220, 100, 25);
        valNom.setFont(policeValeur);
        valNom.setText(unUser.getNom());
        valNom.setBounds(250, 220, 300, 25);

        lbPrenom.setFont(policeLabel);
        lbPrenom.setBounds(100, 270, 100, 25);
        valPrenom.setFont(policeValeur);
        valPrenom.setText(unUser.getPrenom());
        valPrenom.setBounds(250, 270, 300, 25);

        lbEmail.setFont(policeLabel);
        lbEmail.setBounds(100, 320, 100, 25);
        valEmail.setFont(policeValeur);
        valEmail.setText(unUser.getEmail());
        valEmail.setBounds(250, 320, 300, 25);

        lbRole.setFont(policeLabel);
        lbRole.setBounds(100, 370, 130, 25);
        valRole.setFont(new Font("Arial", Font.ITALIC, 16));
        valRole.setText(unUser.getRole()); // Adapte avec ta méthode exacte (ex: getStatut, getRole...)
        valRole.setBounds(250, 370, 300, 25);

        // --- AJOUT DES COMPOSANTS AU PANEL ---
        this.add(lbNom);
        this.add(valNom);
        
        this.add(lbPrenom);
        this.add(valPrenom);
        
        this.add(lbEmail);
        this.add(valEmail);
        
        this.add(lbRole);
        this.add(valRole);
    }
}