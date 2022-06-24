package org.article.dal.jdbc;

import org.article.bo.Auteur;
import org.article.bo.Produit;
import org.article.dal.DALException;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class TestProduitJDBCImpl {

    public static void main(String[] args) {
        ProduitsJDBCImpl produitJdbc = new ProduitsJDBCImpl();
        AuteursJDBCImpl auteursJDBC = new AuteursJDBCImpl();
        // TODO Auto-generated method stub
        //Insert du Pain
        try {
            //Insert du pain
            /*Pain pain = new Pain("blé dur2", "Tradition", 125, 500, 3.75f);
            elJdbc.insert(pain);

            //Insert de la glace
            Glace glace = new Glace(LocalDate.of(2020, 2, 12),"Miko", "Cône", -18, "Fraise", 1000, 2.75f);
            elJdbc.insert(glace);

            //Insert du stylo
             Stylo stylo = new Stylo("Stabilo","Point 88 - la Cerisaie", 10000, 2.50f, "bleu", "Stylo à bille");
             elJdbc.insert(stylo);*/

//            Auteur auteurExemple = new Auteur("Kevin", "Le Nozer");
//
//
//                auteursJDBC.insert(auteurExemple);
//
           /*List<Auteur> auteurs = auteursJDBC.selectAll();
            for(Auteur auteur: auteurs)
            {
               System.out.println(auteur);
            }*/

           /* Auteur auteur = auteursJDBC.selectById(4);


            List<Auteur> lesAuteursDeLaCarteDeux = new ArrayList<>();
            lesAuteursDeLaCarteDeux.add(auteur);
            CartePostale uneDeuxiemeCarte = new CartePostale("Carte Sud Bretagne","Penestin", 10000, 0.80f,lesAuteursDeLaCarteDeux,TypeCartePostale.Paysage);
            produitJdbc.insert(uneDeuxiemeCarte);*/


            List<Produit> produit = produitJdbc.selectAll();
            for(Produit produits: produit)
            {
                System.out.println(produits);
            }
//
//
//
//            Produit produitSelect = produitJdbc.selectById();
//            System.out.println("=> " + produitSelect.toString());
//
//            produitJdbc.delete(produitSelect);
//            System.out.println("Produit supprimé => " + produitSelect);

            /*Produit cartePostaleSelect = produitJdbc.selectById(25);
            System.out.println(cartePostaleSelect);
            produitJdbc.delete(cartePostaleSelect);
            System.out.println("Produit supprimé => " + cartePostaleSelect);*/

        }catch (DALException e) {
            e.printStackTrace();
        }

    }
}
