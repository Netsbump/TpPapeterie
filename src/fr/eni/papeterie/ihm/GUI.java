package fr.eni.papeterie.ihm;

import javax.swing.*;
import java.awt.*;

public class GUI extends JFrame {
    private JPanel panneauPrincipal;
    private JButton bouton;
    private JButton bouton2;

    public GUI(){
        this.setTitle("Mon application");
        this.setSize(300,300);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        // Le panneau principal
        this.setContentPane(getPanneauPrincipal()); // Je colle le panneau principal sur le support en bois
        this.pack(); // Permet que la fenetre s'adapte au contenu
        this.setVisible(true);
    }

    /******************************************************SINGLETON*******************************************************/

    public JPanel getPanneauPrincipal() {
        if (panneauPrincipal == null) {
            panneauPrincipal = new JPanel(); // Je crée le panneau principal
            panneauPrincipal.setLayout(new GridBagLayout());
            GridBagConstraints gbc = new GridBagConstraints();
            gbc.gridx = 0;
            gbc.gridy = 0;
            panneauPrincipal.add(getBouton(), gbc); // J'ajoute le bouton au panneau principal
            gbc.gridx = 0;
            gbc.gridy = 1;
            panneauPrincipal.add(getBouton2(), gbc); //J'aoujoute le bouton2 au panneau principal
        }
        return panneauPrincipal;
    }

    public JButton getBouton() {
        if (bouton==null) {
            bouton = new JButton("HD2WM 140 A");
        }
        return bouton;
    }

    public JButton getBouton2() {
        if (bouton2==null) {
            bouton2 = new JButton("HD2WM 140 B");
            bouton2.setToolTipText("Tooltip");
        }
        return bouton2;
    }

}
