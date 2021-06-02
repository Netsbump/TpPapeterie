package fr.eni.papeterie.bo;

/**
 * CLASSE STYLO
 */

/**************************************ATTRIBUTS-DE-CLASSES-ET-D-INSTANCE**********************************************/
public class Stylo extends Article {

    private String couleur;

/*************************************************CONSTRUCTEURS********************************************************/
    public Stylo () {};

    public Stylo(int idArticle, String reference, String marque, String designation, float prixUnitaire, int qteStock, String couleur) {
        super(idArticle, reference, marque, designation, prixUnitaire, qteStock);
        this.couleur = couleur;
    }

    public Stylo(String reference, String marque, String designation, float prixUnitaire, int qteStock, String couleur) {
        super(reference, marque, designation, prixUnitaire, qteStock);
        this.couleur = couleur;
    }

/***********************************************GETTERS/SETTERS********************************************************/
    public String getCouleur() {
        return couleur;
    }

    public void setCouleur(String couleur) {
        this.couleur = couleur;
    }

/*********************************************SURCHARGE-TO-STRING******************************************************/
    @Override
    public String toString() {
        return "Stylo{" +
                "couleur='" + couleur + '\'' +
                '}';
    }
}
