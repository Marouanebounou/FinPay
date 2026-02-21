package org.example.util;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.*;
import org.example.model.Client;
import org.example.model.Facture;
import org.example.services.ClientService;
import org.example.services.FactureService;

import java.io.FileOutputStream;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;

public class FactureUnpayed {

    private final FactureService factureService = new FactureService();
    private final ClientService clientService = new ClientService();

    public void exportFacturesImpayees() {

        String path = "C:\\Users\\appie\\Desktop\\facturat\\facturesimpayeesmois.xls";

        try {

            // get unpaid invoices
            List<Facture> factures = factureService.getInvoicesByStatus("not payed");

            Workbook workbook = new HSSFWorkbook();
            Sheet sheet = workbook.createSheet("Factures Impayees");

            // header row
            Row header = sheet.createRow(0);

            header.createCell(0).setCellValue("ID");
            header.createCell(1).setCellValue("Client");
            header.createCell(2).setCellValue("Date");
            header.createCell(3).setCellValue("Montant");
            header.createCell(4).setCellValue("Jours de Retard");

            int rowIndex = 1;

            for (Facture facture : factures) {

                Client client = clientService.getClientById(facture.getIdClient());

                LocalDate factureDate = facture.getDate().toLocalDate();
                LocalDate today = LocalDate.now();

                long daysLate = ChronoUnit.DAYS.between(factureDate, today);

                Row row = sheet.createRow(rowIndex++);

                row.createCell(0).setCellValue(facture.getIdInvoice());
                row.createCell(1).setCellValue(client.getName());
                row.createCell(2).setCellValue(factureDate.toString());
                row.createCell(3).setCellValue(facture.getBalance().doubleValue());
                row.createCell(4).setCellValue(daysLate);

            }

            // auto size columns
            for (int i = 0; i < 5; i++) {
                sheet.autoSizeColumn(i);
            }

            // write file
            FileOutputStream fileOut = new FileOutputStream(path);
            workbook.write(fileOut);

            fileOut.close();
            workbook.close();

            System.out.println("Excel file generated: " + path);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}