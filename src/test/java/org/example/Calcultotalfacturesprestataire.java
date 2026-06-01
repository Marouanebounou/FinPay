package org.example;

import org.example.model.Facture;
import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static org.example.config.DatabaseConfig.getConnection;

public class Calcultotalfacturesprestataire {

    public static List<Facture> totalFacturesChaquePrestataire() {
        List<Facture> facturesResultat = new ArrayList<>();

        String query = "SELECT f.id_pre, COUNT(f.id) as nombreFacture, " +
                "ROUND(SUM(f.balance),2) as sommeMontant " +
                "FROM facture f " +
                "GROUP BY f.id_pre " +
                "ORDER BY f.id_pre";

        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(query);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                int idPre = rs.getInt("id_pre");
                int nb = rs.getInt("nombreFacture");
                BigDecimal total = rs.getBigDecimal("sommeMontant");
                facturesResultat.add(new Facture(idPre, nb, total));
            }
        } catch (SQLException e) {
            System.err.println("Erreur SQL : " + e.getMessage());
            e.printStackTrace();
        }
        return facturesResultat;
    }
}