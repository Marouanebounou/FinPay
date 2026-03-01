package org.example;

import org.example.model.Facture;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import static org.example.config.DatabaseConfig.getConnection;
import static org.junit.jupiter.api.Assertions.*;

public class CalcultotalfacturesprestataireTest {

    private void executeSQL(String sql) {
        try (Connection conn = getConnection();
             Statement stmt = conn.createStatement()) {
            stmt.executeUpdate(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @BeforeEach
    void setup() {

        try (Connection conn = getConnection();
             Statement stmt = conn.createStatement()) {

            stmt.executeUpdate("SET FOREIGN_KEY_CHECKS = 0");
            stmt.executeUpdate("TRUNCATE TABLE paiment");
            stmt.executeUpdate("TRUNCATE TABLE facture");

            stmt.executeUpdate("INSERT INTO facture (id, balance, date, status, idClient, id_pre) VALUES " +
                    "(1, 1500.00, NOW(), 'Payé', 1, 1), " +
                    "(2, 3200.50, NOW(), 'not payed', 2, 1), " +
                    "(3, 850.00, NOW(), 'En attente', 1, 2), " +
                    "(4, 5000.00, NOW(), 'Payé', 3, 2)");

            stmt.executeUpdate("SET FOREIGN_KEY_CHECKS = 1");

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    @Test
    @DisplayName("Test 3 – Calcul total factures prestataire.")
    void testCalculerTotalFactures() {

        List<Facture> resultats =
                Calcultotalfacturesprestataire.totalFacturesChaquePrestataire();

        assertNotNull(resultats, "La liste ne doit pas être nulle");
        assertFalse(resultats.isEmpty(), "La liste ne doit pas être vide");
        assertEquals(2, resultats.size(),
                "Il devrait y avoir exactement 2 prestataires");

        boolean found1 = false;
        boolean found2 = false;

        for (Facture f : resultats) {

            if (f.getId_pre() == 1) {
                found1 = true;

                assertEquals(2, f.getNombreFactures());
                assertEquals(0,
                        new BigDecimal("4700.50")
                                .compareTo(f.getBalance()));
            }

            else if (f.getId_pre() == 2) {
                found2 = true;

                assertEquals(2, f.getNombreFactures());
                assertEquals(0,
                        new BigDecimal("5850.00")
                                .compareTo(f.getBalance()));
            }
        }

        assertTrue(found1, "Prestataire 1 non trouvé !");
        assertTrue(found2, "Prestataire 2 non trouvé !");
    }

    @Test
    @DisplayName("Cas liste vide (Base de données vide)")
    void testCasListeVide() {

        executeSQL("DELETE FROM facture");

        List<Facture> resultats =
                Calcultotalfacturesprestataire.totalFacturesChaquePrestataire();

        assertNotNull(resultats);
        assertTrue(resultats.isEmpty(),
                "La liste devrait être vide s'il n'y a pas de factures");
    }
}