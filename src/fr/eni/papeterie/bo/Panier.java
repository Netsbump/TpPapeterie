package fr.eni.papeterie.bo;

import java.util.ArrayList;
import java.util.List;

/**
 * CLASSE PANIER
 */

/**************************************ATTRIBUTS-DE-CLASSES-ET-D-INSTANCE**********************************************/
public class Panier {

    private float montant;
    private List<Ligne> listeDeLignes;

/*************************************************CONSTRUCTEURS********************************************************/
    public Panier() {
        this.listeDeLignes = new ArrayList<Ligne>();
    }

/***********************************************METHODES***************************************************************/
    public float getMontant() {
        return montant;
    }

    public Ligne getLigne(int index){
        return listeDeLignes.get(index);
    }

    public void addLigne(Article article, int qte)   {
        listeDeLignes.add(new Ligne(qte, article));
    }

    public void updateligne(int index, int newQte){
        this.getLigne(index).setQte(newQte);
        // ou idem que
        // this.listeDeLignes.get(index).setQte(newQte);
    }

    public void removeline(int index){
        listeDeLignes.remove(index);
    }

/***********************************************GETTERS/SETTERS********************************************************/
    public List<Ligne> getListeDeLignes() {
        return listeDeLignes;
    }

/*********************************************SURCHARGE-TO-STRING******************************************************/
    @Override
    public String toString() {
        return "Panier{" +
                "montant=" + montant +
                ", lignesPanier=" + listeDeLignes +
                '}';
    }
}
