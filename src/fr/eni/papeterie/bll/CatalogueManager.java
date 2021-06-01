package fr.eni.papeterie.bll;

import fr.eni.papeterie.bll.BLLException;
import fr.eni.papeterie.bo.Article;

import fr.eni.papeterie.bo.Ramette;
import fr.eni.papeterie.bo.Stylo;
import fr.eni.papeterie.dal.jdbc.ArticleDAO;
import fr.eni.papeterie.dal.DAOFactory;
import fr.eni.papeterie.dal.jdbc.DALException;

import java.util.InputMismatchException;
import java.util.List;


public class CatalogueManager {

    private ArticleDAO articleDAO = DAOFactory.getArticleDAO();
/**********************************************CREATION-D-UN-SINGLETON*************************************************/
    private static CatalogueManager instance; //Pour créer un singleton, c'est un attribut de classe

    private CatalogueManager(){}

    public static CatalogueManager getInstance(){
        if (instance == null) {
            instance = new CatalogueManager();
        }
        return instance;
    }

/****************************************METHODE-POUR-AJOUTER-UN-ARTICLE***********************************************/
    public void addArticle(Article a) throws BLLException {
        validerArticle(a);
        this.articleDAO.insert(a);
    }

/****************************************METHODE-LISTER-LE-CATALOGUE***************************************************/
    public List<Article> getCatalogue() {
        return this.articleDAO.selectAll();
    }

/****************************************METHODE-RECUPERER-ARTICLE*****************************************************/
    public Article getArticle(int index){
        return this.articleDAO.selectById(index);
    }

/****************************************METHODE-MODIFIER-ARTICLE******************************************************/
    public void updateArticle(Article a) throws BLLException {
        validerArticle(a);
        this.articleDAO.update(a);
    }

/****************************************METHODE-SUPPRIMER-ARTICLE*****************************************************/
    public void removeArticle(int index) throws BLLException{
        this.articleDAO.delete(index);
    }

/****************************************METHODE-VALIDER-ARTICLE*******************************************************/
    private void validerArticle(Article a) throws BLLException {
        if (a == null){
            throw new BLLException("L'article est null");
        }
        if (a instanceof Ramette && ((Ramette)a).getGrammage() <= 0){
            throw new BLLException("Son grammage n'est pas valide");
        }
        if (a instanceof Stylo && (((Stylo)a).getCouleur() == null || ((Stylo)a).getCouleur().trim().length()==0)) {
            throw new BLLException("La couleur n'est pas valide");
        }
        if(a.getReference() == null || a.getReference().trim().length() == 0) { //on peut faire pareil pour chaque attributs
            throw new BLLException("La référence est obligatoire");
        }
    }

}

