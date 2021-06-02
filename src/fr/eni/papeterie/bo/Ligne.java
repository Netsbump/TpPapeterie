package fr.eni.papeterie.bo;

/**
 * CLASSE LIGNE
 */

/**************************************ATTRIBUTS-DE-CLASSES-ET-D-INSTANCE**********************************************/
public class Ligne {
    protected int qte;
    private Article article; //association unidirectionelle

/*************************************************CONSTRUCTEURS********************************************************/
    public Ligne(int qte, Article article) {
        this.qte = qte;
        this.article = article;
    }

/***********************************************GETTERS/SETTERS********************************************************/
    public int getQte() {
        return qte;
    }

    public void setQte(int qte) {
        this.qte = qte;
    }

    //Retourne le prix unitaire d'un article
    public float getPrix(){
        return this.article.getPrixUnitaire();
    }

    public Article getArticle() {
        return article;
    }

    public void setArticle(Article article) {
        this.article = article;
    }

/*********************************************SURCHARGE-TO-STRING******************************************************/
    @Override
    public String toString() {
        return "Ligne{" +
                "qte=" + qte +
                ", article=" + article +
                '}';
    }
}
