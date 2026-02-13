package org.example.controller;

import org.example.model.Facture;
import org.example.services.FactureService;
import org.example.sessions.Session;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.Scanner;

public class FactureController {
    private final FactureService factureService = new FactureService();
    private final Scanner scanner = new Scanner(System.in);

    public void createFacture() throws SQLException {
        System.out.print("Montant (Balance): ");
        BigDecimal balance = scanner.nextBigDecimal();
        scanner.nextLine();
        System.out.print("ID du Client: ");
        int clientId = scanner.nextInt();
        scanner.nextLine();
        System.out.print("ID du Prestataire: ");
        int preId = scanner.nextInt();
        scanner.nextLine(); // clear buffer

        Facture f = new Facture(preId, balance, LocalDateTime.now(), "not payed", clientId);
        factureService.createInvoice(f);
        System.out.println("Facture créée avec succès !");
    }

    public void listAll() throws SQLException {
        factureService.getAllInvoices().forEach(System.out::println);
    }

    public void filterByStatus() throws SQLException {
        System.out.print("Entrez le statut (payed/not payed): ");
        String status = scanner.nextLine();
        factureService.getInvoicesByStatus(status).forEach(System.out::println);
    }

    public void filterByProvider() throws SQLException {
        System.out.print("ID du Prestataire: ");
        int id = scanner.nextInt();
        scanner.nextLine();
        factureService.getInvoicesByProvider(id).forEach(System.out::println);
    }

    public void getCurrentProviderFactures() throws Exception{
        factureService.getInvoicesByProvider(Session.getCurrentUser().getId()).forEach(System.out::println);
    }

    public void deleteFacture() throws SQLException {
        System.out.print("ID de la facture à supprimer: ");
        int id = scanner.nextInt();
        scanner.nextLine();
        if (factureService.deleteInvoice(id)) {
            System.out.println("Facture supprimée.");
        } else {
            System.out.println("ID introuvable.");
        }
    }

    public void menuGestionFactures() {
    }
}
