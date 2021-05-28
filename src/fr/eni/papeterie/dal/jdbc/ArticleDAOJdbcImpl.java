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

    private final String URL = Settings.getPropriete("url");                                                        //Adresse de la base de donnée

    final String SQL_SELECT_ALL = "SELECT * FROM Articles";
    final String SQL_UPDATE = "UPDATE Articles SET reference=?, marque=?, designation=?, prixUnitaire=?, qteStock=?, grammage=?, couleur =?" +
            "WHERE idArticle=?";
    final String SQL_INSERT = "INSERT INTO Articles " +
            "(reference, marque, designation, prixUnitaire, qteStock, grammage, couleur, type) " +
            " VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
    final String SQL_DELETE = "DELETE FROM Articles WHERE idArticle =?";


/**********************************************METHODE-SELECT-ALL******************************************************/
    public List<Article> selectAll(){

        List<Article> articleList = new ArrayList<>();

        try (Connection connection = DriverManager.getConnection(this.URL))
        {
            PreparedStatement reqPreparee = connection.prepareStatement(this.SQL_SELECT_ALL);

            reqPreparee.executeQuery();
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

        try (Connection connection = DriverManager.getConnection(this.URL))
        {
            PreparedStatement reqPreparee = connection.prepareStatement(this.SQL_UPDATE);

            if (article instanceof Stylo) {
                reqPreparee.setString(7, ((Stylo) article).getCouleur());
            }
            if (article instanceof Ramette) {
                reqPreparee.setInt(6, ((Ramette) article).getGrammage());
            }
            reqPreparee.setString(1, article.getReference());
            reqPreparee.setString(2, article.getMarque());
            reqPreparee.setString(3, article.getDesignation());
            reqPreparee.setFloat(4, article.getPrixUnitaire());
            reqPreparee.setInt(5, article.getQteStock());
            reqPreparee.setInt(8, article.getIdArticle());

            reqPreparee.executeUpdate();

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

/**********************************************METHODE-INSERT**********************************************************/
    public void insert(Article article){

        try (Connection connection = DriverManager.getConnection(this.URL))                                             //ds les parenthèse du try, les paramètres existent seulement à l'intérieur du try et et donc se ferment automatiquement à la fin du try
        {                                                                                                               // cela s'appelle un try with ressources, ds ce cas précis cela ferme automatiquement la connection
            PreparedStatement reqPreparee = connection.prepareStatement(this.SQL_INSERT);                               // pour recup l'index en sqlServer il faut ajouter (this.SQL_INSERT,Statement.RETURN_GENERATED_KEYS))

            if (article instanceof Ramette){
                reqPreparee.setInt(6, ((Ramette)article).getGrammage());
                reqPreparee.setString(8, "RAMETTE");
                // en SQLServer il faut mettre null sur les valeurs non préparée : reqPreparee.setNull (7, Types.NULL);
            }
            if (article instanceof Stylo){
                reqPreparee.setString(7, ((Stylo) article).getCouleur());
                reqPreparee.setString(8, "STYLO");
                // en SQLServer il faut mettre null sur les valeurs non préparée : reqPreparee.setNull (6, Types.NULL)
            }
            reqPreparee.setString(1, article.getReference());
            reqPreparee.setString(2, article.getMarque());
            reqPreparee.setString(3, article.getDesignation());
            reqPreparee.setFloat(4, article.getPrixUnitaire());
            reqPreparee.setInt(5, article.getQteStock());

            reqPreparee.executeUpdate();

            ResultSet rs = reqPreparee.getGeneratedKeys();                                                              //retourne automatiquement un resultset
            if(rs.next()) {
                int id = rs.getInt(1);                                                                       //pour recupérer idKey généré par la base
                article.setIdArticle(id);
                // idem deux autres lignes au dessus : article.setIdArticle(rs.getInt(1));
            }
        }
        catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

/**********************************************METHODE-DELETE**********************************************************/
    public void delete(int id){

        try (Connection connection = DriverManager.getConnection(this.URL))
        {
            PreparedStatement reqPreparee = connection.prepareStatement(this.SQL_DELETE);
            reqPreparee.setInt(1, id);
            reqPreparee.executeUpdate();
        }
        catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
}
