package org.example.util;

import com.itextpdf.kernel.colors.ColorConstants;
import com.itextpdf.kernel.font.PdfFont;
import com.itextpdf.kernel.font.PdfFontFactory;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Cell;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.element.Table;
import com.itextpdf.layout.properties.HorizontalAlignment;
import com.itextpdf.layout.properties.TextAlignment;
import com.itextpdf.layout.properties.UnitValue;
import org.example.config.DatabaseConfig;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class GeneratePaimentPdf {

    public static void genererRecuPaiment(int paiementId) throws Exception {
        String sql = "SELECT p.id_pai, p.date_pai, p.balance AS montant_paye, " +
                "f.id AS facture_id, f.balance AS montant_total " +
                "FROM paiment p " +
                "JOIN facture f ON p.id_fact = f.id " +
                "WHERE p.id_pai = ?";

        try (Connection con = DatabaseConfig.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, paiementId);
            ResultSet rs = ps.executeQuery();

            if (!rs.next()) {
                throw new Exception("Paiement introuvable !");
            }

            int id = rs.getInt("id_pai");
            int factureId = rs.getInt("facture_id");
            double montantPaye = rs.getDouble("montant_paye");
            double montantTotal = rs.getDouble("montant_total");
            double reste = montantTotal - montantPaye;
            String fileName = "recupaiement" + id + ".pdf";

            PdfWriter writer = new PdfWriter(fileName);
            PdfDocument pdf = new PdfDocument(writer);
            Document document = new Document(pdf);

            // Police par défaut
            PdfFont font = PdfFontFactory.createFont(com.itextpdf.io.font.constants.StandardFonts.HELVETICA);

            // Titre avec style
            Paragraph title = new Paragraph("FINPAY")
                    .setFont(font)
                    .setFontSize(22)
                    .setBold()
                    .setFontColor(ColorConstants.BLUE)
                    .setTextAlignment(TextAlignment.CENTER);
            document.add(title);

            document.add(new Paragraph("Reçu de Paiement").setFontSize(16).setBold().setTextAlignment(TextAlignment.CENTER));
            document.add(new Paragraph(" "));

            float[] columnWidths = {150f, 250f};
            Table table = new Table(UnitValue.createPercentArray(columnWidths)).setTextAlignment(TextAlignment.CENTER).setHorizontalAlignment(HorizontalAlignment.CENTER);
            table.setWidth(UnitValue.createPercentValue(80));

            table.addCell(new Cell().add(new Paragraph("Numéro Paiement").setBold()).setBackgroundColor(ColorConstants.LIGHT_GRAY));
            table.addCell(new Cell().add(new Paragraph(String.valueOf(id))).setTextAlignment(TextAlignment.CENTER));

            table.addCell(new Cell().add(new Paragraph("Numéro Facture").setBold()).setBackgroundColor(ColorConstants.LIGHT_GRAY));
            table.addCell(new Cell().add(new Paragraph(String.valueOf(factureId))).setTextAlignment(TextAlignment.CENTER));

            table.addCell(new Cell().add(new Paragraph("Date Paiement").setBold()).setBackgroundColor(ColorConstants.LIGHT_GRAY));
            table.addCell(new Cell().add(new Paragraph(rs.getDate("date_pai").toString())).setTextAlignment(TextAlignment.CENTER));

            table.addCell(new Cell().add(new Paragraph("Montant Payé").setBold()).setBackgroundColor(ColorConstants.LIGHT_GRAY));
            table.addCell(new Cell().add(new Paragraph(String.format("%.2f MAD", montantPaye))).setTextAlignment(TextAlignment.CENTER));

            table.addCell(new Cell().add(new Paragraph("Montant Total").setBold()).setBackgroundColor(ColorConstants.LIGHT_GRAY));
            table.addCell(new Cell().add(new Paragraph(String.format("%.2f MAD", montantTotal))).setTextAlignment(TextAlignment.CENTER));

            table.addCell(new Cell().add(new Paragraph("Reste à Payer").setBold()).setBackgroundColor(ColorConstants.LIGHT_GRAY));
            table.addCell(new Cell().add(new Paragraph(String.format("%.2f MAD", reste))).setTextAlignment(TextAlignment.CENTER));

            document.add(table);

            document.add(new Paragraph(" ").setFontSize(5));
            document.add(new Paragraph("Merci pour votre paiement !").setTextAlignment(TextAlignment.CENTER).setItalic());

            document.close();

            System.out.println("Reçu généré avec succès : " + fileName);
        }
    }
}
