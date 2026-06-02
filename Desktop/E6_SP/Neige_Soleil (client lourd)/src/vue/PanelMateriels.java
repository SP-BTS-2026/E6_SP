package vue;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

// Importation des classes nécessaires depuis les autres packages
import controleur.Controleur;
import controleur.Materiel;

public class PanelMateriels extends PanelPrincipal implements ActionListener {

    // Éléments du formulaire (Gauche)
    private JLabel lbLibelle = new JLabel("Nom du matériel :");
    private JLabel lbType = new JLabel("Type de matériel :");
    private JLabel lbEtat = new JLabel("État du matériel :");
    private JLabel lbPrix = new JLabel("Prix du jour :");

    private JTextField tfLibelle = new JTextField();
    private JTextField tfPrix = new JTextField();

    private JComboBox<String> cbType = new JComboBox<>(new String[]{"Ski", "Snowboard", "Chaussures", "Casque", "Luge"});
    private JComboBox<String> cbEtat = new JComboBox<>(new String[]{"Neuf", "Excellent", "Bon état", "Usagé"});

    private JButton btAnnuler = new JButton("Annuler");
    private JButton btValider = new JButton("Valider");
    private JButton btSupprimer = new JButton("Supprimer");
    private JButton btModifier = new JButton("Modifier");

    // Éléments du Tableau (Droite)
    private JTable tableMateriels;
    private DefaultTableModel modelTable;
    private JScrollPane scrollTable;

    public PanelMateriels(String titre) {
        super(titre);

        // --- POSITIONNEMENT FORMULAIRE (CÔTÉ GAUCHE) ---
        lbLibelle.setBounds(50, 150, 130, 25);
        tfLibelle.setBounds(180, 150, 150, 25);

        lbType.setBounds(50, 200, 130, 25);
        cbType.setBounds(180, 200, 150, 25);

        lbEtat.setBounds(50, 250, 130, 25);
        cbEtat.setBounds(180, 250, 150, 25);

        lbPrix.setBounds(50, 300, 130, 25);
        tfPrix.setBounds(180, 300, 150, 25);

        btAnnuler.setBounds(50, 360, 120, 30);
        btValider.setBounds(180, 360, 120, 30);
        btSupprimer.setBounds(50, 410, 120, 30);
        btModifier.setBounds(180, 410, 120, 30);

        // --- POSITIONNEMENT TABLEAU (CÔTÉ DROIT) ---
        String[] colonnes = {"ID mat", "Libelle mat", "Type mat", "Etat", "Prix_jour"};
        
        modelTable = new DefaultTableModel(colonnes, 0);
        tableMateriels = new JTable(modelTable);
        
        scrollTable = new JScrollPane(tableMateriels);
        scrollTable.setBounds(400, 150, 500, 300);

        // --- AJOUT DES COMPOSANTS ---
        this.add(lbLibelle);
        this.add(tfLibelle);
        this.add(lbType);
        this.add(cbType);
        this.add(lbEtat);
        this.add(cbEtat);
        this.add(lbPrix);
        this.add(tfPrix);
        
        this.add(btAnnuler);
        this.add(btValider);
        this.add(btSupprimer);
        this.add(btModifier);
        
        this.add(scrollTable);

        // Écouteurs
        btAnnuler.addActionListener(this);
        btValider.addActionListener(this);
        btSupprimer.addActionListener(this);
        btModifier.addActionListener(this);
        
        // Remplir le tableau avec les vraies données au démarrage
        remplirTableau();
    }

    private void remplirTableau() {
        modelTable.setRowCount(0);

        try {
            ArrayList<Materiel> lesMateriels = Controleur.selectAllMateriels("");

            // Sécurité : On vérifie que la liste n'est pas nulle avant de boucler
            if (lesMateriels != null) {
                for (Materiel unMat : lesMateriels) {
                    Object[] ligne = {
                        unMat.getId_mat(),
                        unMat.getLibelle_mat(),
                        unMat.getType_mat(),
                        unMat.getEtat(),
                        unMat.getPrix_jour()
                    };
                    modelTable.addRow(ligne);
                }
            }
        } catch (Exception ex) {
            System.out.println("Erreur lors du chargement du tableau : " + ex.getMessage());
        }
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == btAnnuler) {
            tfLibelle.setText("");
            tfPrix.setText("");
            cbType.setSelectedIndex(0);
            cbEtat.setSelectedIndex(0);
        } 
        else if (e.getSource() == btValider) {
            String libelle = tfLibelle.getText();
            String typeMat = cbType.getSelectedItem().toString();
            String etat = cbEtat.getSelectedItem().toString();
            String prixStr = tfPrix.getText();

            if (libelle.isEmpty() || prixStr.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Veuillez remplir tous les champs !");
            } else {
                try {
                    // CORRECTION 1 : Ton modèle attend un 'float' pour le prix, pas un 'double'
                    float prix = Float.parseFloat(prixStr);
                    
                    // CORRECTION 2 : L'ordre exact de ton constructeur -> (libelle, typeMat, etat, prix, id)
                    Materiel unMateriel = new Materiel(libelle, typeMat, etat, prix, 0);                    
                    
                    // Envoi à la base de données
                    Controleur.insertMateriel(unMateriel);
                    
                    JOptionPane.showMessageDialog(this, "Matériel ajouté avec succès !");
                    
                    // Rafraîchissement automatique du tableau
                    remplirTableau(); 
                    
                    // Nettoyage des champs de saisie
                    tfLibelle.setText("");
                    tfPrix.setText("");
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(this, "Le prix doit être un nombre valide !");
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(this, "Erreur lors de l'insertion : " + ex.getMessage());
                }
            }
        }
    }
}