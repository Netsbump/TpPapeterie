package fr.eni.papeterie.dal;

import fr.eni.papeterie.dal.jdbc.ArticleDAO;
import fr.eni.papeterie.dal.jdbc.ArticleDAOJdbcImpl;

/**
 * CLASSE DAOFACTORY POUR APPELER L'IMPLEMENTATION ARTICLEDAO (JDBC)
 */

public class DAOFactory {
    public static ArticleDAO getArticleDAO() {
        ArticleDAO articleDAO = new ArticleDAOJdbcImpl();
        return articleDAO;
    }
}
