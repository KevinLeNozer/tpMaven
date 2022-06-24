package org.article.dal.jdbc;

import org.article.bo.Produit;

public class DAOFactory {
    public static DAO<Produit> getProduitManagerDAO() {
        DAO<Produit> maDAO = new ProduitsJDBCImpl();
        return maDAO;
    }
}
