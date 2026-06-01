package org.example.util;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.example.config.DatabaseConfig;
import org.example.model.Facture;

import java.io.FileOutputStream;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class FactureExporter {

    public static int getPrestataireIdFromUser(int userId) {
        String sql = "SELECT id_prestataire FROM utilisateur WHERE id_user = ?";
        try (Connection con = DatabaseConfig.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, userId);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) return rs.getInt("id_prestataire");
        } catch (Exception e) {
            System.out.println("Erreur ID : " + e.getMessage());
        }
        return -1;
    }

    public static void exportToExcel(int prestataireId, String fileName) {
        String sql = "SELECT f.id, f.date, f.balance, f.status, c.clientName " +
                "FROM facture f " +
                "JOIN client c ON f.idClient = c.idClient " +
                "WHERE f.id_pre = ?";

        try (Connection con = DatabaseConfig.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, prestataireId);
            ResultSet rs = ps.executeQuery();
            Workbook workbook = new XSSFWorkbook();
            Sheet sheet = workbook.createSheet("Factures");

            Row header = sheet.createRow(0);
            header.createCell(0).setCellValue("ID");
            header.createCell(1).setCellValue("Date");
            header.createCell(2).setCellValue("Client");
            header.createCell(3).setCellValue("Montant");
            header.createCell(4).setCellValue("Statut");

            int rowNum = 1;
            while (rs.next()) {
                Row row = sheet.createRow(rowNum++);
                row.createCell(0).setCellValue(rs.getInt("id"));
                row.createCell(1).setCellValue(rs.getTimestamp("date").toLocalDateTime().toLocalDate().toString());
                row.createCell(2).setCellValue(rs.getString("clientName"));
                row.createCell(3).setCellValue(rs.getDouble("balance"));
                row.createCell(4).setCellValue(rs.getString("status"));
            }

            int lastData = rowNum;
            Row rTotal = sheet.createRow(lastData + 1);
            rTotal.createCell(2).setCellValue("TOTAL :");
            rTotal.createCell(3).setCellFormula("SUM(D2:D" + lastData + ")");

            Row rPaye = sheet.createRow(lastData + 2);
            rPaye.createCell(2).setCellValue("PAYÉ :");
            rPaye.createCell(3).setCellFormula("SUMIF(E2:E" + lastData + ",\"Paye\",D2:D" + lastData + ")");

            FileOutputStream fileOut = new FileOutputStream(fileName);
            workbook.write(fileOut);
            fileOut.close();
            workbook.close();

            System.out.println("Export Excel réussi : " + fileName);

        } catch (Exception e) {
            System.out.println("Erreur export : " + e.getMessage());
        }
    }
}