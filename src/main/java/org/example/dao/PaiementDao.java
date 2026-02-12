package org.example.dao;

import org.example.config.DatabaseConfig;
import org.example.model.Paiement;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PaiementDao {
    private static final String INSERT_SQL = "INSERT INTO paiment (date_pai , balance , id_fact) VALUES (?,?,?)";

    public void insert(Paiement paiement) throws SQLException {
        try (Connection con = DatabaseConfig.getConnection();
             PreparedStatement ps = con.prepareStatement(INSERT_SQL);
        ){
            ps.setDate(1, (Date) paiement.getDate());
            ps.setBigDecimal(2,paiement.getBalance());
            ps.setInt(3,paiement.getIdFacture());

            ps.executeUpdate();
        }
    }

    public Paiement findById(int id) throws SQLException{
        String sql = "Select * from paiment where id_pai = ?";
        Paiement paiement = null;
        try(Connection con = DatabaseConfig.getConnection();
            PreparedStatement ps = con.prepareStatement(sql);
        ) {
            ps.setInt(1 , id);
            ResultSet resultSet = ps.executeQuery();
            if (resultSet.next()){
                paiement = new Paiement(resultSet.getBigDecimal("balance") , resultSet.getInt("id_fact") , resultSet.getDate("date_pai"));
            }
        }
        return paiement;
    }

    public List<Paiement> findAll() throws SQLException{
        List<Paiement> paiements = new ArrayList<>();
        String sql = "Select * from paiment";
        try(Connection con = DatabaseConfig.getConnection();
            PreparedStatement preparedStatement = con.prepareStatement(sql);
        ){
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
                paiements.add( new Paiement(resultSet.getBigDecimal("balance") , resultSet.getInt("id_fact") , resultSet.getDate("date_pai")));
            }
        }
        return paiements;
    }

    public boolean update(Paiement paiement) throws SQLException{
        String sql = "UPDATE paiment SET date_pai = ?, balance = ?, id_fact = ? WHERE id_pai = ?;";

        try (Connection con = DatabaseConfig.getConnection();
            PreparedStatement preparedStatement = con.prepareStatement(sql);
        ) {
            preparedStatement.setDate(1 , (Date) paiement.getDate());
            preparedStatement.setBigDecimal(2, paiement.getBalance());
            preparedStatement.setInt(3 , paiement.getIdFacture());
            preparedStatement.setInt(4 , paiement.getId());
            return preparedStatement.executeUpdate() > 0;
        }
    }

    public boolean deleteById(int id) throws SQLException{
        String sql = "Delete from paiment where id_pai = ?";

        try(Connection con = DatabaseConfig.getConnection();
            PreparedStatement preparedStatement = con.prepareStatement(sql);
        ) {
            preparedStatement.setInt(1 , id);
            return preparedStatement.executeUpdate() > 0;
        }
    }

}
