package org.example.util;

import com.itextpdf.kernel.colors.DeviceRgb;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Cell;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.element.Table;
import com.itextpdf.layout.properties.TextAlignment;
import com.itextpdf.layout.properties.UnitValue;
import org.example.model.Client;
import org.example.model.Facture;
import org.example.model.Prestataire;
import org.example.services.ClientService;
import org.example.services.FactureService;
import org.example.services.PrestataireService;

import java.math.BigDecimal;


public class Generatefacture {

    private final FactureService factureService = new FactureService();
    private final ClientService clientService = new ClientService();
    private final PrestataireService prestataireService = new PrestataireService();

    public void generateFacture(int invoiceId) throws Exception {

        Facture facture = factureService.getFactureById(invoiceId);
        Client client = clientService.getClientById(facture.getIdClient());
        Prestataire prestataire = prestataireService.findPrestatairById(facture.getId_pre());


        String path = "C:\\Users\\PC\\Documents\\FinPay\\factures\\facture_" + facture.getIdInvoice() + ".pdf";

        try {
            PdfWriter writer = new PdfWriter(path);
            PdfDocument pdf = new PdfDocument(writer);
            Document document = new Document(pdf);

            // title
            Paragraph title = new Paragraph("FinPay").setFontSize(30).setUnderline()
                    .setTextAlignment(TextAlignment.CENTER)
                    .setFontColor(new DeviceRgb(173, 216, 230));

            document.add(title);

            document.add(new Paragraph("\n"));

            // prestataire content

            Paragraph prestatairename = new Paragraph(prestataire.getName() + "\n").setTextAlignment(TextAlignment.RIGHT)
                    .setFontSize(20);
            Paragraph prestatireType = new Paragraph(prestataire.getType() + "\n").setTextAlignment(TextAlignment.RIGHT)
                    .setFontSize(20);

            document.add(prestatairename);
            document.add(prestatireType);

            // client content
            Table table = new Table(4);
            table.setWidth(UnitValue.createPercentValue(100));

            Cell clientCell = new Cell().add(new Paragraph("Billed to").setFontColor(new DeviceRgb(173, 216, 230)));
            clientCell.add(new Paragraph(client.getName()));
            clientCell.add(new Paragraph(client.getEmail()));

            Cell dateCell = new Cell().add(new Paragraph("Date").setFontColor(new DeviceRgb(173, 216, 230)));

            dateCell.add(new Paragraph(String.valueOf(facture.getDate())));

            Cell invoiceCell = new Cell().add(new Paragraph("nombre de Facture").setFontColor(new DeviceRgb(173, 216, 230)));

            invoiceCell.add(new Paragraph(String.valueOf(facture.getIdInvoice())));

            Cell amountCell = new Cell().add(new Paragraph("balance").setFontColor(new DeviceRgb(173, 216, 230)));

            amountCell.add(new Paragraph(String.valueOf(facture.getBalance())));

            document.add(new Paragraph("\n"));

            table.addCell(clientCell);
            table.addCell(dateCell);
            table.addCell(invoiceCell);
            table.addCell(amountCell);

            document.add(table);

            document.add(new Paragraph("_".repeat(24)).setFontColor(new DeviceRgb(255, 153, 51)));

            Table details = new Table(2);
            details.setWidth(UnitValue.createPercentValue(100));

            Cell description = new Cell().add(new Paragraph("description"));

            description.add(new Paragraph("total paye :"));
            description.add(new Paragraph("commision de FinPay :"));

            Cell balance = new Cell().add(new Paragraph("balance"));

            balance.add(new Paragraph(String.valueOf(facture.getBalance())));

            BigDecimal total = facture.getBalance().multiply(new BigDecimal("0.02"));

            balance.add(new Paragraph(String.valueOf(total)));

            details.addCell(description);
            details.addCell(balance);

            document.add(details);

            document.add(new Paragraph("\n"));

            document.add(new Paragraph("thank you for your bisness"));

            document.close();

            System.out.println("pdf genere par succes");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

