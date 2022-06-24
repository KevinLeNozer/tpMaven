package org.article.dal.jdbc;

import org.article.dal.DALException;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class JDBCTools {

    public static Connection getConnection() throws DALException {

        String db = String.format("jdbc:mariadb://%s:%s/%s?user=%s&password=%s",
                Settings.getProperty("serveur"), Settings.getProperty("port"),
                Settings.getProperty("db"), Settings.getProperty("user"), Settings.getProperty("password"));
        System.out.println(db);
        Connection cnx = null;
        try {
            cnx = DriverManager.getConnection(db);
        } catch (SQLException e) {
           throw new DALException("Erreur de connexion à la base de données",e);
        }
        return cnx;
    }
}
