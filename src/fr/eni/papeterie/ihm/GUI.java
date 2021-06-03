package fr.eni.papeterie.ihm;

import fr.eni.papeterie.bll.BLLException;
import fr.eni.papeterie.bll.CatalogueManager;
import fr.eni.papeterie.bo.Article;
import fr.eni.papeterie.bo.Couleur;
import fr.eni.papeterie.bo.Ramette;
import fr.eni.papeterie.bo.Stylo;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


/**
 * CLASSE GUI IHM
 */

/***************************************ATTRIBUTS-DE-CLASSES-ET-D-INSTANCE*********************************************/
public class GUI extends JFrame {

    private JPanel panneauPrincipal, panneauRadio, panneauCheckBox, panneauBoutons;
    private JButton boutonPrecedent, boutonSuivant, boutonValider, boutonDelete, boutonSave;
    private JLabel labelReference, labelDesignation, labelMarque, labelStock, labelPrix, labelType, labelGrammage, labelCouleur;
    private JTextField textReference, textMarque, textDesignation, textStock, textPrix;
    private JRadioButton radioRamette, radioStylo;
    private JCheckBox checkbox80, checkbox100;
    private JComboBox comboCouleur;

    //Liste d'article pour afficher les articles stockés dans la BDD
    List<Article> listeDarticles;
    private int index = 0;

/********************************************CREATION-JFRAME***********************************************************/
    public GUI(){
        this.setTitle("The Office");
        this.setSize(300,300);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        // Le panneau principal
        this.setContentPane(getPanneauPrincipal()); // Je colle le panneau principal sur le support en bois
        this.pack(); // Permet que la fenetre s'adapte au contenu
        this.setVisible(true);
        //Je remplis ma listeDarticles
        listeDarticles = new ArrayList<>();
        CatalogueManager cm = CatalogueManager.getInstance();
        try{
            listeDarticles = cm.getCatalogue();
        } catch (BLLException e) {
            System.out.println(e.getMessage());
        }
        Article articleAaffiche;
        if(!listeDarticles.isEmpty()) {
            articleAaffiche = listeDarticles.get(index); //J'affiche mon premier article
            getTextReference().setText(articleAaffiche.getReference());
            getTextDesignation().setText(articleAaffiche.getDesignation());
            getTextMarque().setText(articleAaffiche.getMarque());
            getTextStock().setText(String.valueOf(articleAaffiche.getQteStock()));
            getTextPrix().setText(String.valueOf(articleAaffiche.getPrixUnitaire()));
            if(articleAaffiche instanceof Ramette){
                getRadioRamette().setSelected(true);
                getcomboCouleur().setEnabled(false);
                getCheckbox80().setEnabled(true);
                getCheckbox100().setEnabled(true);
                getcomboCouleur().setEnabled(false);
            }
            if(articleAaffiche instanceof Stylo){
                getRadioStylo().setSelected(true);
                getcomboCouleur().setSelectedItem(((Stylo) articleAaffiche).getCouleur());
                getCheckbox80().setEnabled(false);
                getCheckbox100().setEnabled(false);
            }
        }
    }

/********************************************CREATION-JPANEL-PRINCIPAL*************************************************/
    public JPanel getPanneauPrincipal() {
        if (panneauPrincipal == null) {
            panneauPrincipal = new JPanel(); // Je crée le panneau principal
            panneauPrincipal.setLayout(new GridBagLayout());
            GridBagConstraints gbc = new GridBagConstraints();
            panneauPrincipal.setBackground(Color.ORANGE);

            //PREMIERE COLONNE DE PANNEAU PRINCIPAL
            gbc.gridx = 0;
            gbc.gridy = 0;
            panneauPrincipal.add(getLabelReference(), gbc); // J'ajoute le bouton au panneau principal

            gbc.gridx = 0;
            gbc.gridy = 1;
            panneauPrincipal.add(getLabelDesignation(), gbc); //J'ajoute le bouton2 au panneau principal

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

            gbc.gridheight = 1;
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

/*****************************************CREATION-JPANEL-SUPPLEMENTAIRES**********************************************/

/************************************************PANEL-PANNEAU-RADIO***************************************************/
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

/************************************************PANEL-PANNEAU-CHECKBOX************************************************/
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

/************************************************PANEL-PANNEAU-BOUTONS*************************************************/
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
            ImageIcon icon = new ImageIcon("Image/Back24.gif");
            boutonPrecedent = new JButton(icon);
            boutonPrecedent.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) { //méthode anonyme
                    if(index == 0) {
                        index = listeDarticles.size() - 1;
                    } else {
                        index--;
                    }
                    Article articleAafficher = listeDarticles.get(index);
                    getTextReference().setText(articleAafficher.getReference());
                    getTextDesignation().setText(articleAafficher.getDesignation());
                    getTextMarque().setText(articleAafficher.getMarque());
                    getTextStock().setText(String.valueOf(articleAafficher.getQteStock()));
                    getTextPrix().setText(String.valueOf(articleAafficher.getPrixUnitaire()));
                    if(articleAafficher instanceof Ramette){
                        getRadioRamette().setSelected(true);
                        getcomboCouleur().setEnabled(false);
                        getCheckbox80().setEnabled(true);
                        getCheckbox100().setEnabled(true);
                        getcomboCouleur().setEnabled(false);
                    }
                    if(articleAafficher instanceof Stylo){
                        getRadioStylo().setSelected(true);
                        getcomboCouleur().setSelectedItem(((Stylo) articleAafficher).getCouleur());
                        getCheckbox80().setEnabled(false);
                        getCheckbox100().setEnabled(false);
                    }
                }
            });
        }
        return boutonPrecedent;
    }

    public JButton getSuivant() {
        if (boutonSuivant==null) {
            Icon icon = new ImageIcon("Image/Forward24.gif");
            boutonSuivant = new JButton(icon);
            boutonSuivant.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    if(index == 0) {
                        index = listeDarticles.size() + 1;
                    } else {
                        index++;
                    }
                    Article articleAafficher = listeDarticles.get(index);
                    getTextReference().setText(articleAafficher.getReference());
                    getTextDesignation().setText(articleAafficher.getDesignation());
                    getTextMarque().setText(articleAafficher.getMarque());
                    getTextStock().setText(String.valueOf(articleAafficher.getQteStock()));
                    getTextPrix().setText(String.valueOf(articleAafficher.getPrixUnitaire()));
                    if(articleAafficher instanceof Ramette){
                        getRadioRamette().setSelected(true);
                        getcomboCouleur().setEnabled(false);
                        getCheckbox80().setEnabled(true);
                        getCheckbox100().setEnabled(true);
                        getcomboCouleur().setEnabled(false);
                    }
                    if(articleAafficher instanceof Stylo){
                        getRadioStylo().setSelected(true);
                        getcomboCouleur().setSelectedItem(((Stylo) articleAafficher).getCouleur());
                        getCheckbox80().setEnabled(false);
                        getCheckbox100().setEnabled(false);
                    }
                }
            });
        }
        return boutonSuivant;
    }

    public JButton getDelete() {
        if (boutonDelete==null) {
            Icon icon = new ImageIcon("Image/Delete24.gif");
            boutonDelete = new JButton(icon);
        }
        return boutonDelete;
    }

    public JButton getValider() {
        if (boutonValider==null) {
            Icon icon = new ImageIcon("Image/New24.gif");
            boutonValider = new JButton(icon);
            boutonValider.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    getTextReference().setText("");
                    getTextDesignation().setText("");
                    getTextMarque().setText("");
                    getTextStock().setText("");
                    getTextPrix().setText("");
                }
            });
        }
        return boutonValider;
    }

    public JButton getSave() {
        if (boutonSave==null) {
            Icon icon = new ImageIcon("Image/Save24.gif");
            boutonSave = new JButton(icon);
            boutonSave.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    try {
                        CatalogueManager cm = CatalogueManager.getInstance();
                        Article article = new Ramette();
                        article.setDesignation(getTextDesignation().getText());
                        article.setReference(getTextReference().getText());
                        article.setMarque(getTextMarque().getText());
                        article.setQteStock(Integer.parseInt(getTextStock().getText()));
                        article.setPrixUnitaire(Float.parseFloat(getTextPrix().getText()));
                        cm.addArticle(article);
                    } catch (BLLException bllException) {
                        bllException.printStackTrace();
                    }

                }
            });
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
            radioRamette.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    getcomboCouleur().setEnabled(false);
                    getCheckbox80().doClick();
                }
            });
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
            comboCouleur = new JComboBox<Couleur>(Couleur.values());
        }
        return comboCouleur;
    }
}

