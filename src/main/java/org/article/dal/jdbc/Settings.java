package org.article.dal.jdbc;

import org.article.dal.DALException;

import java.io.IOException;
import java.util.Properties;

public class Settings {
    private static Properties properties;
    private static void chargement() throws DALException {
        if (properties == null) {
            properties = new Properties();
            try {
                properties.load(Settings.class.getClassLoader().getResourceAsStream("settings.properties"));
            } catch (IOException e) {
                throw new DALException("Erreur lors du chargement du fichiers contenant les " +
                        "informations de connection à la base de données", e);
            }
        }
    }
    public static String getProperty(String key) throws DALException {
        chargement();
        return properties.getProperty(key);
    }
}
