package fr.eni.papeterie.dal.jdbc;

import java.util.Properties;

/**
 * CLASSE SETTINGS POUR RECUPERER LE CONTENU DE SETTINGS.PROPERTIES (URL, LOGIN, MDP, ECT)
 */

public class Settings {
    private static Properties propriete;

    static {
        try{
            propriete = new Properties();
            propriete.load(Settings.class.getResourceAsStream("settings.properties"));
        } catch (Exception e){
            System.out.println(e.getMessage());
        }
    }

    public static String getPropriete(String cle){
        String parametre = propriete.getProperty(cle, null);
        return parametre;
    }
}
