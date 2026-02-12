package org.example.dao;

import org.example.config.DatabaseConfig;
import org.example.model.Statistique;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class StatistiqueDAO  {
    private static final String INSERT_SQL = "INSERT INTO statistiaue (id_paiment , totalAmount , totalComission, date_op) values (? , ? , ? , ?)";
    public void insert(Statistique statistique) throws SQLException {
        try(Connection con = DatabaseConfig.getConnection(); PreparedStatement ps = con.prepareStatement(INSERT_SQL)){
            ps.setInt(1,statistique.getId_pai());
            ps.setBigDecimal(2,statistique.getTotalAmount());
            ps.setBigDecimal(3,statistique.getTotalComission());
            ps.setDate(4,java.sql.Date.valueOf(statistique.getDateOp().toString()));

            ps.executeUpdate();
        }
    }

    public Statistique findById(int id) throws SQLException{
        Statistique statistique = null;
        String sql = "SELECT * FROM statistiaue where id_stat = ? ";
        try(Connection con = DatabaseConfig.getConnection();
            PreparedStatement ps = con.prepareStatement(sql);
        ){
            ps.setInt(1 , id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()){
                statistique = new Statistique(rs.getBigDecimal("totalAmount") , rs.getBigDecimal("totalComission")  , rs.getInt("id_paiment") , rs.getDate("date_op"));
            }
        }
        return statistique;
    }

    public List<Statistique> findAll()throws SQLException{
        String sql = "Select * from statistiaue";
        List<Statistique> statistiques = new ArrayList<>();
        try(Connection con = DatabaseConfig.getConnection();
            PreparedStatement pr = con.prepareStatement(sql);
        ){
            ResultSet rs = pr.executeQuery();
            if (rs.next()){
                while (rs.next()){
                    statistiques.add(new Statistique(rs.getBigDecimal("totalAmount") , rs.getBigDecimal("totalComission")  , rs.getInt("id_paiment") , rs.getDate("date_op")));
                }
            }
        }
        return statistiques;
    }

    public boolean update(Statistique statistique) throws SQLException{
        String sql = "UPDATE statistiaue SET totalAmount = ?, totalComission = ?, date_op = ? WHERE id_paiement = ?";
        try (Connection conn = DatabaseConfig.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setBigDecimal(1, statistique.getTotalAmount());
            ps.setBigDecimal(2, statistique.getTotalComission());
            ps.setDate(3, (Date) statistique.getDateOp());
            ps.setInt(4, statistique.getId_pai());

            return ps.executeUpdate() > 0;
        }
    }

    public boolean deleteById(int id) throws SQLException{
        String sql = "Delete from statistiaue where id_pai = ?";

        try(Connection con = DatabaseConfig.getConnection();
            PreparedStatement preparedStatement = con.prepareStatement(sql);
        ) {
            preparedStatement.setInt(1 , id);
            return preparedStatement.executeUpdate() > 0;
        }
    }



}
