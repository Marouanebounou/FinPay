package org.example.dao;

import org.example.config.DatabaseConfig;
import org.example.model.Facture;

import java.sql.Connection;

import java.sql.PreparedStatement;
import java.sql.SQLException;

public class FactureDao {

    public void addInvoice(Facture facture)throws SQLException{

        String query = "INSERT INTO facture(balance,date,status,idClient,id_pre) VALUES (?,?,?,?,?)";

        try(Connection conn = DatabaseConfig.getConnection();
        PreparedStatement ps = conn.prepareStatement(query)){

        }
    }
}
