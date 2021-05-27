package fr.eni.papeterie.dal.jdbc;

import fr.eni.papeterie.bo.Article;
import fr.eni.papeterie.bo.Ramette;
import fr.eni.papeterie.bo.Stylo;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/***********************************************************************************************************************
 ****************************** CLASSE DES METHODES SQL DE LA TABLE ARTICLES *******************************************
 **********************************************************************************************************************/


public class ArticleDAOJdbcImpl {

    private final String URL ="jdbc:sqlite:identifier.sqlite";                                                          //Adresse de la base de donnée

/**********************************************METHODE-SELECT-ALL******************************************************/
    public List<Article> selectAll(){

        final String SELECT_ALL = "SELECT * FROM Articles";
        List<Article> articleList = new ArrayList<>();

        try (Connection connection = DriverManager.getConnection(this.URL);
             PreparedStatement reqPreparee = connection.prepareStatement(SELECT_ALL))
        {
            reqPreparee.executeQuery(SELECT_ALL);                                                        //Resulset sert à récupérer les données dans un select
        }
        catch (SQLException e)   {
            System.out.println(e.getMessage());
        }
        return articleList;
    }

/**********************************************METHODE-SELECT-BY-ID****************************************************/
    public Article selectById(int id){

        final String SELECT_BY_ID = "SELECT * FROM Articles WHERE idArticle=?";
        Article article = null;

        try (Connection connection = DriverManager.getConnection(this.URL);
                PreparedStatement reqPreparee = connection.prepareStatement(SELECT_BY_ID))
        {
            id = article.getIdArticle();
            reqPreparee.setString(1, id););
            reqPreparee.setString(2, article.getMarque());
            reqPreparee.setString(3, article.getReference());
            reqPreparee.setString(4, article.getDesignation());
            reqPreparee.setFloat(5, article.getPrixUnitaire());
            reqPreparee.setInt(6, article.getQteStock());
            if(article instanceof Ramette){
                article = new Ramette(reqPreparee.setInt(7, (((Ramette) article).getGrammage());
            }
            if(article instanceof Stylo){
                article = new Stylo(reqPreparee.setInt(8, (((Stylo) article).getCouleur());
            }

            ResultSet rs = reqPreparee.executeQuery(SELECT_BY_ID); //resultset = jeu de résultat

            if (rs.next()) {
                if (rs.getString("type").trim().equalsIgnoreCase("RAMETTE")) {
                    article = new Ramette(
                            rs.getInt("idArticle"),
                            rs.getString("marque"),
                            rs.getString("reference"),
                            rs.getString("designation"),
                            rs.getFloat("prixUnitaire"),
                            rs.getInt("qteStock"),
                            rs.getInt("grammage")
                    );
                }
                if (rs.getString("type").trim().equalsIgnoreCase("STYLO")) {
                    article = new Stylo(
                            rs.getInt("idArticle"),
                            rs.getString("marque"),
                            rs.getString("reference"),
                            rs.getString("designation"),
                            rs.getFloat("prixUnitaire"),
                            rs.getInt("qteStock"),
                            rs.getString("couleur")
                    );
                }
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return article;
    }

/**********************************************METHODE-UPDATE**********************************************************/
    public void update(Article article){

        final String UPTADE = "UPDATE Articles SET reference=?, marque=?, designation=?, qteStock=?, prixUnitaire=?, grammage=?, couleur =?" +
                "WHERE idArticle=?";

        try (Connection connection = DriverManager.getConnection(this.URL);                                             //ds les parenthèse du try, les paramètres existent seulement à l'intérieur du try et et donc se ferment automatiquement à la fin du try
             PreparedStatement reqPreparee = connection.prepareStatement(UPTADE))
        {
            if (article instanceof Stylo) {
                reqPreparee.setString(6, ((Stylo) article).getCouleur());
                reqPreparee.setInt(8, article.getIdArticle());            //revoir cette ligne
            }
            if (article instanceof Ramette) {
                reqPreparee.setInt(7, ((Ramette) article).getGrammage());
                reqPreparee.setInt(8, article.getIdArticle());            //idem revoir cette ligne
            }
            reqPreparee.setString(1, article.getReference());
            reqPreparee.setString(2, article.getMarque());
            reqPreparee.setString(3, article.getDesignation());
            reqPreparee.setFloat(4, article.getPrixUnitaire());
            reqPreparee.setInt(5, article.getQteStock());

            reqPreparee.executeUpdate(UPTADE);

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

/**********************************************METHODE-INSERT**********************************************************/
    public void insert(Article article){

        final String INSERT = "INSERT INTO Articles VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?);";

        try (Connection connection = DriverManager.getConnection(this.URL);                                             //ds les parenthèse du try, les paramètres existent seulement à l'intérieur du try et et donc se ferment automatiquement à la fin du try
             PreparedStatement reqPreparee = connection.prepareStatement(INSERT))                                       // cela s'appelle un try with ressources, ds ce cas précis cela ferme automatiquement la connection
        {
            if (article instanceof Ramette){
                reqPreparee.setInt(7, ((Ramette)article).getGrammage());
            }
            if (article instanceof Stylo){
                reqPreparee.setString(8, ((Stylo) article).getCouleur());
            }
            reqPreparee.setString(2, article.getReference());
            reqPreparee.setString(3, article.getMarque());
            reqPreparee.setString(4, article.getDesignation());
            reqPreparee.setFloat(5, article.getPrixUnitaire());
            reqPreparee.setInt(6, article.getQteStock());

            reqPreparee.executeUpdate(INSERT);                                                                          // pour recup l'index en sqlServer il faut ajouter (sql,Statement.RETURN_GENERATED_KEYS))

            ResultSet rs = reqPreparee.getGeneratedKeys();
            if(rs.next()) {
                int id = rs.getInt(1);                                                                       //pour recupérer idKey généré par la base
                article.setIdArticle(id);
            }
        }
        catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

/**********************************************METHODE-DELETE**********************************************************/
    public void delete(Article article){

        final String DELETE_FROM = "DELETE FROM Articles WHERE idArticle =?;";

        try(Connection connection = DriverManager.getConnection(this.URL);
            PreparedStatement reqPreparee = connection.prepareStatement(DELETE_FROM))
        {
            reqPreparee.setInt(1, article.getIdArticle());
            reqPreparee.executeQuery(DELETE_FROM);
        }
        catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
}
