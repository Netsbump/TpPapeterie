package fr.eni.papeterie.ihm;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;

public class GUI extends JFrame {
    private JPanel panneauPrincipal;
    private JPanel panneauRadio;
    private JPanel panneauCheckBox;
    private JPanel panneauBoutons;

    private JButton boutonPrecedent;
    private JButton boutonSuivant;
    private JButton boutonValider;
    private JButton boutonDelete;
    private JButton boutonSave;


    private JLabel labelReference;
    private JLabel labelDesignation;
    private JLabel labelMarque;
    private JLabel labelStock;
    private JLabel labelPrix;
    private JLabel labelType;
    private JLabel labelGrammage;
    private JLabel labelCouleur;
    private JTextField textReference;
    private JTextField textMarque;
    private JTextField textDesignation;
    private JTextField textStock;
    private JTextField textPrix;
    private JRadioButton radioRamette;
    private JRadioButton radioStylo;
    private JCheckBox checkbox80;
    private JCheckBox checkbox100;
    private JComboBox comboCouleur;

    public GUI(){
        this.setTitle("The Office");
        this.setSize(300,300);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        // Le panneau principal
        this.setContentPane(getPanneauPrincipal()); // Je colle le panneau principal sur le support en bois
        this.pack(); // Permet que la fenetre s'adapte au contenu
        this.setVisible(true);
    }



    public JPanel getPanneauPrincipal() {
        if (panneauPrincipal == null) {
            panneauPrincipal = new JPanel(); // Je crée le panneau principal
            panneauPrincipal.setLayout(new GridBagLayout());
            panneauPrincipal.setBackground(Color.ORANGE);
            GridBagConstraints gbc = new GridBagConstraints();
            //PREMIERE COLONNE DE PANNEAU PRINCIPAL
            gbc.gridx = 0;
            gbc.gridy = 0;
            panneauPrincipal.add(getLabelReference(), gbc); // J'ajoute le bouton au panneau principal

            gbc.gridx = 0;
            gbc.gridy = 1;
            panneauPrincipal.add(getLabelDesignation(), gbc); //J'aoujoute le bouton2 au panneau principal

            gbc.gridx = 0;
            gbc.gridy = 2;
            panneauPrincipal.add(getLabelMarque(), gbc);

            gbc.gridx = 0;
            gbc.gridy = 3;
            panneauPrincipal.add(getLabelStock(), gbc);

            gbc.gridx = 0;
            gbc.gridy = 4;
            panneauPrincipal.add(getLabelPrix(), gbc);

            gbc.gridx = 0;
            gbc.gridy = 5;
            panneauPrincipal.add(getLabelType(), gbc);

            gbc.gridheight = 2;
            gbc.gridx = 0;
            gbc.gridy = 6;
            panneauPrincipal.add(getLabelGrammage(), gbc);

            gbc.gridheight = 2;
            gbc.gridx = 0;
            gbc.gridy = 7;
            panneauPrincipal.add(getLabelCouleur(), gbc);

            gbc.gridheight = 3;
            gbc.gridx = 0;
            gbc.gridy = 9;
            gbc.gridwidth = 3;
            panneauPrincipal.add(getPanneauBoutons(), gbc);

            gbc.gridheight = 1;
            gbc.gridwidth = 1;

            //DEUXIEME COLONNE DE PANNEAU PRINCIPAL
            gbc.gridx = 1;
            gbc.gridy = 0;
            panneauPrincipal.add(getTextReference(), gbc);
            gbc.gridx = 1;
            gbc.gridy = 1;
            panneauPrincipal.add(getTextDesignation(), gbc);
            gbc.gridx = 1;
            gbc.gridy = 2;
            panneauPrincipal.add(getTextMarque(), gbc);
            gbc.gridx = 1;
            gbc.gridy = 3;
            panneauPrincipal.add(getTextStock(), gbc);
            gbc.gridx = 1;
            gbc.gridy = 4;
            panneauPrincipal.add(getTextPrix(), gbc);
            gbc.gridx = 1;
            gbc.gridy = 5;
            panneauPrincipal.add(getPanneauRadio(), gbc);
            gbc.gridx = 1;
            gbc.gridy = 6;
            panneauPrincipal.add(getPanneauCheckBox(), gbc);
            gbc.gridx = 1;
            gbc.gridy = 7;
            panneauPrincipal.add(getcomboCouleur(), gbc);

        }
        return panneauPrincipal;
    }

    public JPanel getPanneauRadio() {
        if (panneauRadio == null) {
            panneauRadio = new JPanel();
            panneauRadio.setLayout(new GridLayout(2,1));
            ButtonGroup bg = new ButtonGroup();
            bg.add(getRadioRamette());
            bg.add(getRadioStylo());
            panneauRadio.add(getRadioRamette());
            panneauRadio.add(getRadioStylo());
        }
        return panneauRadio;
    }

    public JPanel getPanneauCheckBox(){
        if(panneauCheckBox == null){
            panneauCheckBox = new JPanel();
            panneauCheckBox.setLayout(new GridLayout(2,1));
            ButtonGroup bg = new ButtonGroup();
            bg.add(getCheckbox80());
            bg.add(getCheckbox100());
            panneauCheckBox.add(getCheckbox80());
            panneauCheckBox.add(getCheckbox100());
        }
        return panneauCheckBox;
    }

    public JPanel getPanneauBoutons(){
        if(panneauBoutons == null){
            panneauBoutons = new JPanel();
            panneauBoutons.setLayout(new GridLayout(1,5));
            panneauBoutons.add(getPrecedent());
            panneauBoutons.add(getValider());
            panneauBoutons.add(getSave());
            panneauBoutons.add(getDelete());
            panneauBoutons.add(getSuivant());
        }
        return panneauBoutons;
    }




    /******************************************************SINGLETON*******************************************************/
    public JButton getPrecedent() {
        if (boutonPrecedent==null) {
            Icon icon = new ImageIcon("D:\\Back24.gif");
            boutonPrecedent = new JButton(icon);
        }
        return boutonPrecedent;
    }

    public JButton getSuivant() {
        if (boutonSuivant==null) {
            Icon icon = new ImageIcon("D:\\Forward24.gif");
            boutonSuivant = new JButton(icon);
        }
        return boutonSuivant;
    }

    public JButton getDelete() {
        if (boutonDelete==null) {
            Icon icon = new ImageIcon("D:\\Delete24.gif");
            boutonDelete = new JButton(icon);
        }
        return boutonDelete;
    }

    public JButton getValider() {
        if (boutonValider==null) {
            Icon icon = new ImageIcon("D:\\New24.gif");
            boutonValider = new JButton(icon);
        }
        return boutonValider;
    }

    public JButton getSave() {
        if (boutonSave==null) {
            Icon icon = new ImageIcon("D:\\Save24.gif");
            boutonSave = new JButton(icon);
        }
        return boutonSave;
    }

    public JLabel getLabelReference(){
        if(labelReference == null) {
            labelReference = new JLabel("Référence ");
        }
        return labelReference;
    }

    public JLabel getLabelDesignation(){
        if(labelDesignation == null) {
            labelDesignation = new JLabel("Désignation ");
        }
        return labelDesignation;
    }

    public JLabel getLabelMarque(){
        if(labelMarque == null) {
            labelMarque = new JLabel("Marque ");
        }
        return labelMarque;
    }

    public JLabel getLabelStock(){
        if(labelStock== null) {
            labelStock= new JLabel("Stock");
        }
        return labelStock;
    }

    public JLabel getLabelPrix(){
        if(labelPrix == null) {
            labelPrix = new JLabel("Prix ");
        }
        return labelPrix;
    }

    public JLabel getLabelType(){
        if(labelType == null) {
            labelType = new JLabel("Type ");
            labelType.setSize(10,20);
        }
        return labelType;
    }

    public JLabel getLabelGrammage(){
        if(labelGrammage == null) {
            labelGrammage = new JLabel("Grammage ");
        }
        return labelGrammage;
    }

    public JLabel getLabelCouleur(){
        if(labelCouleur== null) {
            labelCouleur = new JLabel("Couleur ");
        }
        return labelCouleur;
    }

    public JTextField getTextReference(){
        if(textReference == null) {
            textReference = new JTextField(15);
        }
        return textReference;
    }

    public JTextField getTextDesignation(){
        if(textDesignation == null) {
            textDesignation = new JTextField(15);
        }
        return textDesignation;
    }

    public JTextField getTextMarque(){
        if(textMarque == null) {
            textMarque = new JTextField(15);
        }
        return textMarque;
    }

    public JTextField getTextStock(){
        if(textStock == null) {
            textStock = new JTextField(15);
        }
        return textStock;
    }

    public JTextField getTextPrix(){
        if(textPrix == null) {
            textPrix = new JTextField(15);
        }
        return textPrix;
    }

    public JRadioButton getRadioRamette(){
        if(radioRamette == null) {
            radioRamette = new JRadioButton("Ramette");
        }
        return radioRamette;
    }

    public JRadioButton getRadioStylo(){
        if(radioStylo == null) {
            radioStylo = new JRadioButton("Stylo");
        }
        return radioStylo;
    }

    public JCheckBox getCheckbox80(){
        if (checkbox80 == null) {
            checkbox80 = new JCheckBox("80 grammes");

        }
        return checkbox80;
    }

    public JCheckBox getCheckbox100(){
        if (checkbox100 == null) {
            checkbox100 = new JCheckBox("100 grammes");

        }
        return checkbox100;
    }

    public JComboBox getcomboCouleur(){
        if (comboCouleur == null) {
            comboCouleur = new JComboBox();

        }
        return comboCouleur;
    }
}

