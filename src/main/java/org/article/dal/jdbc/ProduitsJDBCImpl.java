package org.article.dal.jdbc;

import eu.unareil.bo.*;
import org.article.bo.Produit;
import org.article.dal.DALException;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProduitsJDBCImpl implements DAO<Produit>{

    private static final String SQL_INSERT = "insert into produit (marque, libelle, qteStock, " +
            "prixUnitaire, type, dateLimiteConso, poids, parfum, temperatureConservation, couleur, " +
            "typeMine, typeCartePostale)" + "values(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
    private static final String SQL_DELETE = "delete from produit where refProd=?";
    private static final String SQL_SELECT_BY_ID = "select * from produit where refProd=?";
    private static final String SQL_SELECT_ALL = "select * from produit";
    private static final String SQL_INSERT_AUTEUR_CartePostale = "insert into auteur_cartepostale" +
            " (refAuteur, refCartePostale )" + "values(?, ?)";

    private static final String SQL_SELECT_ALL_AUTEUR = "select * from auteur_cartepostale where " +
            "refCartePostale=?";
    @Override
    public void insert(Produit data) throws DALException {
        PreparedStatement pstmt = null;
        Connection cnx = JDBCTools.getConnection();
        try {
            pstmt = cnx.prepareStatement(SQL_INSERT, Statement.RETURN_GENERATED_KEYS);

            pstmt.setString(1, data.getMarque());
            pstmt.setString(2, data.getLibelle());
            pstmt.setLong(3, data.getQteStock());
            pstmt.setFloat(4, data.getPrixUnitaire());

            if (data instanceof Pain) {
                pstmt.setString(5, "pain");
                pstmt.setDate(6, Date.valueOf(((Pain) data).getDateLimiteDeConso()));
                pstmt.setFloat(7, ((Pain) data).getPoids());
                pstmt.setNull(8, Types.NULL);
                pstmt.setNull(9, Types.NULL);
                pstmt.setNull(10, Types.NULL);
                pstmt.setNull(11, Types.NULL);
                pstmt.setNull(12, Types.NULL);
            } else if (data instanceof Glace) {
                pstmt.setString(5, "glace");
                pstmt.setDate(6, Date.valueOf(((Glace) data).getDateLimiteDeConso()));
                pstmt.setNull(7, Types.NULL);
                pstmt.setString(8, ((Glace) data).getParfum());
                pstmt.setInt(9, ((Glace) data).getTemperatureConservation());
                pstmt.setNull(10, Types.NULL);
                pstmt.setNull(11, Types.NULL);
                pstmt.setNull(12, Types.NULL);
            } else if (data instanceof Stylo) {
                pstmt.setString(5, "stylo");
                pstmt.setNull(6, Types.NULL);
                pstmt.setNull(7, Types.NULL);
                pstmt.setNull(8, Types.NULL);
                pstmt.setNull(9, Types.NULL);
                pstmt.setString(10, ((Stylo) data).getCouleur());
                pstmt.setString(11, ((Stylo) data).getTypeMine());
                pstmt.setNull(12, Types.NULL);
            } else if (data instanceof CartePostale) {
                pstmt.setString(5, "cartepostale");
                pstmt.setNull(6, Types.NULL);
                pstmt.setNull(7, Types.NULL);
                pstmt.setNull(8, Types.NULL);
                pstmt.setNull(9, Types.NULL);
                pstmt.setNull(10, Types.NULL);
                pstmt.setNull(11, Types.NULL);
                pstmt.setString(12, ((CartePostale) data).getType());
            }
            int nbRow = pstmt.executeUpdate();
            if (nbRow == 1) {
                ResultSet rs = pstmt.getGeneratedKeys();
                if (rs.next()) {
                    data.setRefProd(rs.getLong(1));
                    PreparedStatement cartePostalPstmt = null;
                    for (Auteur auteur : ((CartePostale) data).getLesAuteursDeLaCarte()) {
                        cartePostalPstmt  = cnx.prepareStatement(SQL_INSERT_AUTEUR_CartePostale);
                        cartePostalPstmt.setInt(1, auteur.getId());
                        cartePostalPstmt.setLong(2, data.getRefProd());
                        cartePostalPstmt.executeUpdate();
                    }
                }
            }

        } catch (SQLException e) {
            throw new DALException("Erreur du insert - data=" + data, e);
        }
        finally
        {
            try {
                if(pstmt != null)
                {
                    pstmt.close();
                }
            } catch (SQLException e) {
                // TODO Auto-generated catch block
                throw new DALException("erreur du insert au niveau du close- data=" + data, e);
            }
        }
    }
    @Override
    public void delete(Produit data) throws DALException {
        PreparedStatement pstmt = null;
        long id  = data.getRefProd();
        Connection cnx = JDBCTools.getConnection();
        try {
            pstmt = cnx.prepareStatement(SQL_DELETE);
            pstmt.setLong(1, id);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            throw new DALException("Erreur du delete - id=" + id, e);
        }
        finally {
            try {
                if (pstmt != null) {
                    pstmt.close();
                }
                if (cnx != null) {
                    cnx.close();
                }
            } catch (SQLException e) {
                throw new DALException("Erreur du delete - id=" + id, e);
            }
        }
    }
    @Override
    public Produit selectById(long id) throws DALException {
        PreparedStatement pstmt = null;
        PreparedStatement cartePostalPstmt = null;
        ResultSet cartePostaleRs = null;
        ResultSet rs = null;
        Produit produit = null;
        Connection cnx = JDBCTools.getConnection();
        try {
            pstmt = cnx.prepareStatement(SQL_SELECT_BY_ID);
            pstmt.setLong(1, id);
            rs = pstmt.executeQuery();
            if (rs.next()) {
                System.out.println("test");
                if (rs.getString(6).equals("stylo")) {
                    produit = new Stylo(
                            rs.getLong(1), rs.getString(2), rs.getString(3),
                            rs.getLong(4), rs.getFloat(5), rs.getString(11), rs.getString(12));

                } else if (rs.getString(6).equals("pain")) {
                    produit = new Pain(rs.getLong(1), rs.getString(2), rs.getString(3),
                            rs.getFloat(5), rs.getLong(4), rs.getFloat(8));

                } else if (rs.getString(6).equals("glace")) {
                    produit = new Glace(rs.getLong(1), rs.getString(2), rs.getString(3),
                            rs.getLong(4), rs.getInt(5), rs.getDate(7).toLocalDate(),
                            rs.getString(9), rs.getInt(10));

                } else if (rs.getString(6).equals("cartepostale")) {

                    cartePostalPstmt  = cnx.prepareStatement(SQL_SELECT_BY_ID);
                    cartePostalPstmt.setLong(1, rs.getLong(1));
                    cartePostaleRs = cartePostalPstmt.executeQuery();
                    AuteursJDBCImpl auteursJDBC = new AuteursJDBCImpl();
                    List<Auteur> auteurs = new ArrayList<>();
                    while(cartePostaleRs.next()) {
                        auteurs.add(auteursJDBC.selectById(cartePostaleRs.getInt(1)));
                    }
                    produit = new CartePostale(rs.getLong(1), rs.getString(2), rs.getString(3),
                            rs.getLong(4), rs.getInt(5), auteurs, TypeCartePostale.valueOf(rs.getString(13)));
                }
            }
        } catch (SQLException e) {
            throw new DALException("Erreur du select by id - id=" + id, e);
        }
        finally {
            try {
                if (pstmt != null) {
                    pstmt.close();
                }
            } catch (SQLException e) {
                throw new DALException("Erreur du select by id - id=" + id, e);
            }
        }
        return produit;
    }

    @Override
    public List<Produit> selectAll() throws DALException {
        Connection cnx = JDBCTools.getConnection();
        // TODO Auto-generated method stub
        Statement stmt=null;
        ResultSet rs=null;
        List<Produit> lesElements= new ArrayList<>();
        Produit produit = null;
        try {
            stmt = cnx.createStatement();
            rs = stmt.executeQuery(SQL_SELECT_ALL);
            while(rs.next())
            {
                if (rs.getString(6).equals("stylo")) {
                   produit = new Stylo(rs.getLong(1), rs.getString(2), rs.getString(3),
                           rs.getLong(4), rs.getFloat(5), rs.getString(11), rs.getString(12));
                    lesElements.add(produit);
                } else if (rs.getString(6).equals("pain")) {
                   produit = new Pain(rs.getLong(1), rs.getString(2), rs.getString(3), rs.getFloat(5), rs.getLong(4), rs.getFloat(8));
                    lesElements.add(produit);
                } else if (rs.getString(6).equals("glace")) {
                    //long refProd, String libelle, String marque, long qteStock, float prixUnitaire, LocalDate dateLimiteDeConso, String parfum, int temperatureConservation
                    produit = new Glace(rs.getLong(1), rs.getString(2), rs.getString(3),
                            rs.getLong(4), rs.getInt(5), rs.getDate(7).toLocalDate(),
                            rs.getString(9), rs.getInt(10));
                    lesElements.add(produit);
                }
            }
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            throw new DALException("erreur du select all", e);
        }
        finally
        {
            try {
                if(stmt!=null)
                {
                    stmt.close();
                }
            } catch (SQLException e) {
                // TODO Auto-generated catch block
                throw new DALException("erreur du select all au niveau du close- ",e);
            }
        }
        return lesElements;
    }
}