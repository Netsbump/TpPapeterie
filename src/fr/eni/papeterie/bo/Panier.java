package fr.eni.papeterie.bo;

import java.util.ArrayList;
import java.util.List;

public class Panier {

    private float montant;
    private List<Ligne> listeDeLignes;

    public Panier() {
        this.listeDeLignes = new ArrayList<Ligne>();
    }

    public float getMontant() {
        return montant;
    }

    //Attention ce n'est pas un getter
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

    //Getter
    public List<Ligne> getListeDeLignes() {
        return listeDeLignes;
    }

    @Override
    public String toString() {
        return "Panier{" +
                "montant=" + montant +
                ", lignesPanier=" + listeDeLignes +
                '}';
    }
}
