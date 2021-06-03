package fr.eni.papeterie.dal.jdbc;

import fr.eni.papeterie.bo.Article;

import java.util.List;

/**
 * INTERFACE ARTICLEDAO DONT DEPEND ARTICLEDAOIMPL
 */

public interface ArticleDAO {
    List<Article> selectAll() throws DALException;
    void update(Article article) throws DALException;
    void insert(Article article) throws DALException;
    Article selectById(int id) throws DALException;
    void delete(int id)throws DALException;
}
