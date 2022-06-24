package org.article.dal.jdbc;


import org.article.bo.Auteur;
import org.article.bo.CartePostale;
import org.article.dal.DALException;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AuteursJDBCImpl implements DAO<Auteur>{
    private static final String SQL_INSERT = "insert into auteur (nom, prenom)" + "values(?, ?)";
    private static final String SQL_DELETE = "delete from auteur where id=?";
    private static final String SQL_SELECT_BY_ID = "select * from auteur where id=?";
    private static final String SQL_SELECT_ALL = "select * from auteur";
    private static final String SQL_SELECT_ALL_REF_PROD = "select produit.refProd, marque, libelle, prixUnitaire, qteStock, typeCartePostale from produit inner join auteur_cartepostale on produit.refProd= auteur_cartepostale.refProd where refAuteur = ?";
    @Override
    public void insert(Auteur data) throws DALException {
        PreparedStatement pstmt = null;
        Connection cnx = JDBCTools.getConnection();
        try {
            pstmt = cnx.prepareStatement(SQL_INSERT, Statement.RETURN_GENERATED_KEYS);

            pstmt.setString(1, data.getNom());
            pstmt.setString(2, data.getPrenom());

            int nbRow = pstmt.executeUpdate();
            if (nbRow == 1) {
                ResultSet rs = pstmt.getGeneratedKeys();
                if (rs.next()) {
                    data.setId(rs.getInt(1));
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
    public void delete(Auteur data) throws DALException {
        PreparedStatement pstmt = null;
        long id  = data.getId();
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
    public Auteur selectById(long id) throws DALException {
        PreparedStatement pstmt = null;
        PreparedStatement auteurPstmt = null;
        ResultSet auteurRs = null;
        ResultSet rs = null;
        Auteur auteur = null;
        Connection cnx = JDBCTools.getConnection();
        try {
            pstmt = cnx.prepareStatement(SQL_SELECT_BY_ID);
            pstmt.setLong(1, id);
            rs = pstmt.executeQuery();
            if (rs.next()) {
                auteurPstmt = cnx.prepareStatement(SQL_SELECT_ALL_REF_PROD);
                auteurPstmt.setLong(1, id);
                auteurRs = auteurPstmt.executeQuery();
                List<CartePostale> cartePostales = new ArrayList<>();
                    if (auteurRs.next()) {
                        cartePostales.add(new CartePostale(auteurRs.getLong(1),
                                auteurRs.getString(2), auteurRs.getString(3), auteurRs.getLong(4), auteurRs.getFloat(5), auteurRs.getString(6)));
                    }
                auteur = new Auteur(rs.getInt(1), rs.getString(2), rs.getString(3), cartePostales);
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
        return auteur;
    }

    @Override
    public List<Auteur> selectAll() throws DALException {
        Connection cnx = JDBCTools.getConnection();
        // TODO Auto-generated method stub
        Statement stmt=null;

        ResultSet rs=null;
        List<Auteur> lesAuteurs= new ArrayList<>();
        Auteur auteur = null;
        try {
            stmt = cnx.createStatement();
            rs = stmt.executeQuery(SQL_SELECT_ALL);
            while(rs.next())
            {
                auteur = new Auteur(rs.getInt(1), rs.getString(2), rs.getString(3));
                lesAuteurs.add(auteur);
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
        return lesAuteurs;
    }
}



