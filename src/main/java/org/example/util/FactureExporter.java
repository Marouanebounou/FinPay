package org.example.util;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.example.config.DatabaseConfig;
import org.example.model.Facture;

import java.io.FileOutputStream;
import java.math.BigDecimal;
import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class FactureExporter {

    public static List<Facture> fetchFactures(int prestataireId) {
        List<Facture> factures = new ArrayList<>();
        String query = """
    SELECT f.id,
           f.date,
           f.balance,
           f.status,
           f.idClient,
           c.clientName AS client_name
    FROM facture f
    JOIN client c ON f.idClient = c.idClient
    WHERE f.id_pre = ?
""";



        try (Connection conn = DatabaseConfig.getConnection();
             PreparedStatement ps = conn.prepareStatement(query)) {

            ps.setInt(1, prestataireId);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {

                Facture f = new Facture(
                        prestataireId,
                        rs.getBigDecimal("balance"),
                        rs.getTimestamp("date").toLocalDateTime(),
                        rs.getString("status"),
                        rs.getInt("idClient")
                );

                f.setIdInvoice(rs.getInt("id"));
                f.setClientName(rs.getString("client_name"));

                factures.add(f);
            }




        } catch (SQLException e) {
            e.printStackTrace();
        }

        return factures;
    }

    public static void exportToExcel(int id, String fileName) {
        List<Facture> factures = fetchFactures(id);
        Workbook workbook = new XSSFWorkbook();
        Sheet sheet = workbook.createSheet("Factures");

        CellStyle headerStyle = workbook.createCellStyle();
        Font font = workbook.createFont();
        font.setBold(true);
        headerStyle.setFont(font);

        String[] headers = {"ID", "Date", "Client", "Montant", "Statut"};
        Row headerRow = sheet.createRow(0);
        for (int i = 0; i < headers.length; i++) {
            Cell cell = headerRow.createCell(i);
            cell.setCellValue(headers[i]);
            cell.setCellStyle(headerStyle);
        }

        int rowNum = 1;
        for (Facture f : factures) {
            Row row = sheet.createRow(rowNum++);
            row.createCell(0).setCellValue(f.getIdInvoice());
            Cell dateCell = row.createCell(1);
            dateCell.setCellValue(java.sql.Timestamp.valueOf(f.getDate()));
            row.createCell(2).setCellValue(f.getClientName());
            row.createCell(3).setCellValue(f.getBalance().doubleValue());
            row.createCell(4).setCellValue(f.getStatus());
        }

        int lastDataRow = factures.size();

        Row totalFacture = sheet.createRow(lastDataRow + 2);
        totalFacture.createCell(2).setCellValue("Total facturé :");
        totalFacture.createCell(3).setCellFormula(String.format("SUM(D2:D%d)", lastDataRow + 1));

        Row totalPaye = sheet.createRow(lastDataRow + 3);
        totalPaye.createCell(2).setCellValue("Total payé :");
        totalPaye.createCell(3).setCellFormula(String.format("SUMIF(E2:E%d,\"Payé\",D2:D%d)", lastDataRow + 1, lastDataRow + 1));

        Row totalAttente = sheet.createRow(lastDataRow + 4);
        totalAttente.createCell(2).setCellValue("Total en attente :");
        totalAttente.createCell(3).setCellFormula(String.format("SUMIF(E2:E%d,\"En attente\",D2:D%d)", lastDataRow + 1, lastDataRow + 1));

        for (int i = 0; i < headers.length; i++) sheet.autoSizeColumn(i);

        try (FileOutputStream fileOut = new FileOutputStream(fileName)) {
            workbook.write(fileOut);
            workbook.close();
            System.out.println("Fichier Excel créé : " + fileName);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static String getClientName(int idClient) {
        String name = "";
        String query = "SELECT name FROM client WHERE idClient = ?";
        try (Connection conn = DatabaseConfig.getConnection();
             PreparedStatement ps = conn.prepareStatement(query)) {
            ps.setInt(1, idClient);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) name = rs.getString("name");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return name;
    }

}
