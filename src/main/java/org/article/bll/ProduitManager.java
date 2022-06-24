package org.article.bll;


import org.article.bo.Produit;
import org.article.dal.DALException;
import org.article.dal.jdbc.DAO;
import org.article.dal.jdbc.DAOFactory;

import java.util.List;

public class ProduitManager {
    private static volatile ProduitManager instance = null;

    private DAO<Produit> impl;

    public final static ProduitManager getInstance() {
        if (ProduitManager.instance == null) {
            synchronized (ProduitManager.class) {
                if (ProduitManager.instance == null) {
                    ProduitManager.instance = new ProduitManager();
                }
            }
        }
        return ProduitManager.instance;
    }

    private ProduitManager(){
        impl = DAOFactory.getProduitManagerDAO();
    }
    public List<Produit> getLesElements() throws BLLException {
        List<Produit> lesEle = null;

        try {
            lesEle = impl.selectAll();
        } catch (DALException e) {
            throw new BLLException("Erreur lors de la récupération des produits", e);
        }
        return lesEle;
    }

    public void ajouterElement(Produit element) throws BLLException {
        if (element.getRefProd() != 0) {
            throw new BLLException("Produit déjà existant");
        }
        valider(element);
        try {
            impl.insert(element);
        } catch (DALException e) {
            throw new BLLException("Erreur lors du produit" + element, e);
        }
    }

    public void deleteElement(Produit element) throws BLLException {
        try {
            valider(element);
            impl.delete(element);
        }catch (DALException e) {
            throw new BLLException("Erreur lors de la suppression du produit" + element, e);
        }
    }

    private void valider(Produit el) throws BLLException {
        boolean valide = true;
        StringBuilder sb = new StringBuilder();
        if (el == null) {
            throw new BLLException("Le produit ne peut pas être null");
        }
        if (el.getPrixUnitaire() < 1) {
            sb.append("Le prix unitaire doit être positif !");
            valide = false;
        }
        if (el.getQteStock() < 1) {
            sb.append("Le stock doit être positif !");
            valide = false;
        }
        if (!valide) {
            throw new BLLException(sb.toString());
        }
    }

}
