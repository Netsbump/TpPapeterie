package fr.eni.papeterie.bo;

/**
 * CLASSE RAMETTE
 */

/**************************************ATTRIBUTS-DE-CLASSES-ET-D-INSTANCE**********************************************/
public class Ramette extends Article{

    private int grammage;

/*************************************************CONSTRUCTEURS********************************************************/
    public Ramette (){};

    public Ramette(int idArticle, String reference, String marque, String designation, float prixUnitaire, int qteStock, int grammage) {
        super(idArticle, reference, marque, designation, prixUnitaire, qteStock);
        this.grammage = grammage;
    }

    public Ramette(String reference, String marque, String designation, float prixUnitaire, int qteStock, int grammage) {
        super(reference, marque, designation, prixUnitaire, qteStock);
        this.grammage = grammage;
    }

/***********************************************GETTERS/SETTERS********************************************************/
    public int getGrammage() {
        return grammage;
    }

    public void setGrammage(int grammage) {
        this.grammage = grammage;
    }

/*********************************************SURCHARGE-TO-STRING******************************************************/
    @Override
    public String toString() {
        return "Ramette{" +
                "grammage=" + grammage +
                '}';
    }
}
