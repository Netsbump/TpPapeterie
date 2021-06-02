package fr.eni.papeterie.dal.jdbc;

/**
 * CLASSE DALEXCEPTION QUI HERITE DE EXCEPTION
 */
public class DALException extends Exception{

    public DALException(String message) {
        super(message);
    }
}
