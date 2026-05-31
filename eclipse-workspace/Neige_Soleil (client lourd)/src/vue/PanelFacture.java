package vue;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class PanelFacture extends PanelPrincipal implements ActionListener {

    // Composants du formulaire
    private JLabel lbIdReser = new JLabel("ID Réservation :");
    private JTextField txtIdReser = new JTextField();
    private JButton btGenerer = new JButton("Générer la Facture");
    
    // Zone d'affichage de la facture reçue
    private JTextArea txtAffichageFacture = new JTextArea();

    public PanelFacture(String titre) {
        super(titre);
        this.setBackground(Color.gray);
        
        // Titre du Panel
        JLabel lbTitre = new JLabel("Édition des Factures Clients");
        lbTitre.setBounds(350, 20, 400, 30);
        lbTitre.setFont(new Font("Arial", Font.BOLD, 22));
        lbTitre.setForeground(Color.white);
        this.add(lbTitre);

        // Positionnement du Formulaire (à gauche)
        lbIdReser.setBounds(50, 100, 150, 25);
        lbIdReser.setForeground(Color.white);
        lbIdReser.setFont(new Font("Arial", Font.PLAIN, 14));
        this.add(lbIdReser);

        txtIdReser.setBounds(180, 100, 150, 25);
        this.add(txtIdReser);

        btGenerer.setBounds(90, 160, 180, 30);
        btGenerer.setBackground(Color.white);
        this.add(btGenerer);

        // Positionnement de la zone de texte (à droite pour simuler la facture papier)
        txtAffichageFacture.setBounds(450, 80, 450, 350);
        txtAffichageFacture.setFont(new Font("Monospaced", Font.PLAIN, 13)); // Police fixe pour faire un joli tableau
        txtAffichageFacture.setEditable(false); // On ne peut pas écrire dedans à la main
        txtAffichageFacture.setText("\n   --- En attente de génération ---   ");
        this.add(txtAffichageFacture);

        // Activation du bouton
        btGenerer.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == btGenerer) {
            String idReserStr = txtIdReser.getText();
            
            if (idReserStr.equals("")) {
                javax.swing.JOptionPane.showMessageDialog(this, "Veuillez saisir un numéro de réservation valide.");
                return;
            }

            try {
                int idReser = Integer.parseInt(idReserStr);
                
                // 1. On appelle le contrôleur pour récupérer les données de la BDD
                String[] infos = controleur.Controleur.recupererDonneesFacture(idReser);
                
                // On vérifie si la réservation existe (si on a trouvé un nom de client)
                if (infos[0] == null) {
                    javax.swing.JOptionPane.showMessageDialog(this, "Aucune réservation trouvée pour cet ID en base de données.");
                    return;
                }
                
                // 2. Extraction des données de la BDD
                String nomClient = infos[0];
                String prenomClient = infos[1];
                String nomAppart = infos[2];
                double prixNuit = Double.parseDouble(infos[3]);
                int nbNuits = Integer.parseInt(infos[4]);
                
                // 3. Calculs financiers
                double totalHT = prixNuit * nbNuits;
                double tva = totalHT * 0.20;
                double totalTTC = totalHT + tva;
                
                // 4. Construction de la facture textuelle
                String corpsFacture = 
                      "=========================================\n"
                    + "             NEIGE & SOLEIL              \n"
                    + "=========================================\n"
                    + " Facture de la Réservation N° : " + idReser + "\n"
                    + "-----------------------------------------\n"
                    + " Client : " + prenomClient + " " + nomClient + "\n"
                    + " Hébergement : " + nomAppart + "\n"
                    + " Durée du séjour : " + nbNuits + " nuits (" + prixNuit + "€/nuit)\n"
                    + "-----------------------------------------\n"
                    + " Montant Total HT : " + totalHT + " €\n"
                    + " TVA (20%)        : " + tva + " €\n"
                    + " TOTAL À PAYER TTC : " + totalTTC + " €\n"
                    + "=========================================\n"
                    + " Statut de la facture : PAYÉ\n"
                    + " Merci de votre confiance !";
                
                // 5. Affichage immédiat dans le carré blanc de l'application
                txtAffichageFacture.setText(corpsFacture);
                
                // 6. GÉNÉRATION DU FICHIER REEL SUR L'ORDINATEUR
                String nomFichier = "facture_reservation_" + idReser + ".txt";
                java.io.FileWriter fw = new java.io.FileWriter(nomFichier);
                java.io.PrintWriter pw = new java.io.PrintWriter(fw);
                
                pw.print(corpsFacture); // On écrit le texte dans le fichier
                
                pw.close(); // On ferme et enregistre le fichier
                fw.close();
                
                javax.swing.JOptionPane.showMessageDialog(this, "Facture calculée et fichier '" + nomFichier + "' généré avec succès !");
                
            } catch (NumberFormatException nfe) {
                javax.swing.JOptionPane.showMessageDialog(this, "L'ID de réservation doit être un nombre entier.");
            } catch (java.io.IOException ioe) {
                javax.swing.JOptionPane.showMessageDialog(this, "Erreur lors de la création physique du fichier texte.");
            }
        }
    }
}