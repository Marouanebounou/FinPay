package org.example.dao;

import org.example.config.DatabaseConfig;
import org.example.model.Facture;

import java.math.BigDecimal;
import java.sql.Connection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class FactureDao {

    public void addInvoice(Facture facture)throws SQLException{

        String query = "INSERT INTO facture(balance,`date`,status,idClient,id_pre) VALUES (?,?,?,?,?)";

        try(Connection conn = DatabaseConfig.getConnection();
        PreparedStatement ps = conn.prepareStatement(query)){
            ps.setBigDecimal(1,facture.getBalance());
            ps.setObject(2,facture.getDate());
            ps.setString(3,facture.getStatus());
            ps.setInt(4,facture.getIdClient());
            ps.setInt(5,facture.getId_pre());

            ps.executeUpdate();
        }
    }

    public boolean removeInvoice(int idInvoice) throws SQLException{
        String query = "DELETE FROM facture WHERE id = ?";
        try(Connection conn = DatabaseConfig.getConnection();
            PreparedStatement ps = conn.prepareStatement(query)){

            ps.setInt(1,idInvoice);
            return ps.executeUpdate() >0 ;
        }
    }

    public Facture updateInvoice(Facture facture)throws SQLException{

        String query = "UPDATE facture SET balance = ? , date = ? , status = ? WHERE id = ?";

        try(Connection conn = DatabaseConfig.getConnection();
        PreparedStatement ps = conn.prepareStatement(query)){

            ps.setBigDecimal(1,facture.getBalance());
            ps.setObject(2,facture.getDate());
            ps.setString(3,facture.getStatus());
            ps.setInt(4,facture.getIdInvoice());

            int rowsUpdated = ps.executeUpdate();
            if (rowsUpdated == 0){
                throw new SQLException("Updating facture failed, no rows affected.");
            }

            return facture;
        }
    }

    public List<Facture> showFactures() throws SQLException{
        List<Facture> factures = new ArrayList<>();
        String query = "SELECT * FROM facture";

        try( Connection conn = DatabaseConfig.getConnection();
        PreparedStatement ps = conn.prepareStatement(query)){

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {

                int id_pre = rs.getInt("id_pre");
                BigDecimal balance = rs.getBigDecimal("balance");
                LocalDateTime date = rs.getObject("date", LocalDateTime.class);
                String status = rs.getString("status");
                int idClient = rs.getInt("idClient");

                Facture f = new Facture(id_pre, balance, date, status, idClient);

                f.setIdInvoice(rs.getInt("id"));

                factures.add(f);
            }
            return factures;
        }
    }

    public List<Facture> filterByStatus(String statusType)throws SQLException{
        List<Facture> factures = new ArrayList<>();
        String query = "SELECT * FROM facture WHERE status = ?";

        try( Connection conn = DatabaseConfig.getConnection();
             PreparedStatement ps = conn.prepareStatement(query)){

            ps.setString(1,statusType);
            try(ResultSet rs = ps.executeQuery()){
                while (rs.next()) {

                    int id_pre = rs.getInt("id_pre");
                    BigDecimal balance = rs.getBigDecimal("balance");
                    LocalDateTime date = rs.getObject("date", LocalDateTime.class);
                    String status = rs.getString("status");
                    int idClient = rs.getInt("idClient");

                    Facture f = new Facture(id_pre, balance, date, status, idClient);

                    f.setIdInvoice(rs.getInt("id"));

                    factures.add(f);
                }
            }
            return factures;
        }
    }

    public List<Facture> filterByPrestataire(int id_prestataire)throws SQLException{
        List<Facture> factures = new ArrayList<>();
        String query = "SELECT * FROM facture WHERE id_pre = ?";

        try( Connection conn = DatabaseConfig.getConnection();
             PreparedStatement ps = conn.prepareStatement(query)){

            ps.setInt(1,id_prestataire);
            try(ResultSet rs = ps.executeQuery()){
                while (rs.next()) {

                    int id_pre = rs.getInt("id_pre");
                    BigDecimal balance = rs.getBigDecimal("balance");
                    LocalDateTime date = rs.getObject("date", LocalDateTime.class);
                    String status = rs.getString("status");
                    int idClient = rs.getInt("idClient");

                    Facture f = new Facture(id_pre, balance, date, status, idClient);

                    f.setIdInvoice(rs.getInt("id"));

                    factures.add(f);
                }
            }
            return factures;
        }
    }
}
