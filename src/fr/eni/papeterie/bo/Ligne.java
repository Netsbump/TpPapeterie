package fr.eni.papeterie.bo;

public class Ligne {
    //Attribut
    protected int qte;
    private Article article; //association unidirectionelle
    //Constructeurs
    public Ligne(int qte, Article article) {
        this.qte = qte;
        this.article = article;
    }

    //Getters et Setters
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

    //Méthodes
    @Override
    public String toString() {
        return "Ligne{" +
                "qte=" + qte +
                ", article=" + article +
                '}';
    }
}
