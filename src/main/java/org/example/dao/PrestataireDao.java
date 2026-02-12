package org.example.dao;

import org.example.config.DatabaseConfig;
import org.example.model.Prestataire;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PrestataireDao {
    private static final String INSERT_SQL = "INSERT INTO prestataire (nom , typePre , created_at) values (?, ?, ?)";
    private static final String SELECT_BY_ID = "SELECT * FROM prestataire WHERE id_pre = ?";

    public void insert (Prestataire prestataire) throws SQLException{
        try(Connection con = DatabaseConfig.getConnection();
            PreparedStatement ps = con.prepareStatement(INSERT_SQL)
        ){
            ps.setString(1, prestataire.getName());
            ps.setString(2 , prestataire.getType());
            ps.setDate(3 ,  (Date) prestataire.getCreatedAt());

            ps.executeUpdate();
        }
    }

    public Prestataire findById(int id) throws SQLException{
        Prestataire prestataire = null;
        try(Connection con = DatabaseConfig.getConnection();
            PreparedStatement ps = con.prepareStatement(SELECT_BY_ID);
        ){
            ps.setInt(1, id);
            ResultSet rs = ps.getResultSet();
            if (rs.next()){
                prestataire = new Prestataire();
                prestataire.setId(rs.getInt("id_per"));
                prestataire.setName(rs.getNString("nom"));
                prestataire.setType(rs.getString("typePre"));
                prestataire.setCreatedAt(rs.getDate("created_at"));
            }
        }
        return prestataire;
    }

    public List<Prestataire> findAll() throws SQLException{
        List<Prestataire> prestataires = new ArrayList<>();
        String sql = "SELECT * FROM prestataire";

        try(Connection con = DatabaseConfig.getConnection();
            PreparedStatement ps = con.prepareStatement(sql);
        ){
            ResultSet rs = ps.executeQuery();
            while (rs.next()){
                Prestataire prestataire = new Prestataire();
                prestataire.setId(rs.getInt("id_per"));
                prestataire.setName(rs.getNString("nom"));
                prestataire.setType(rs.getString("typePre"));
                prestataire.setCreatedAt(rs.getDate("created_at"));

                prestataires.add(prestataire);
            }
        }
        return prestataires;
    }

    public boolean update(Prestataire prestataire) throws SQLException{
        String sql = "Update prestataire set nom = ? ,typePre = ?, created_at = ? where id_pre = ?";
        try(
                Connection con = DatabaseConfig.getConnection();
                PreparedStatement preparedStatement = con.prepareStatement(sql);
            ) {
                preparedStatement.setString(1 ,prestataire.getName());
                preparedStatement.setString(2 , prestataire.getType());
                preparedStatement.setDate(3 , (Date)prestataire.getCreatedAt());
                preparedStatement.setInt(4 , prestataire.getId());
                return preparedStatement.executeUpdate() > 0;
            }
    }

    public boolean deleteById(int id) throws SQLException{
        String sql = "Delete from prestataire where id_pai = ?";

        try(Connection con = DatabaseConfig.getConnection();
            PreparedStatement preparedStatement = con.prepareStatement(sql);
        ) {
            preparedStatement.setInt(1 , id);
            return preparedStatement.executeUpdate() > 0;
        }
    }



}

