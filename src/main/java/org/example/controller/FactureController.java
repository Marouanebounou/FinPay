package org.example.controller;

import org.example.model.Facture;
import org.example.services.FactureService;
import org.example.sessions.Session;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.List;
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
        int preId = Session.getCurrentUser().getIdpre();
        Facture f = new Facture(preId, balance, LocalDateTime.now(), "not payed", clientId);
        factureService.createInvoice(f);
        System.out.println("Facture créée avec succès !");
    }

    public void listAll() throws SQLException {
        factureService.getAllInvoices().forEach(System.out::println);
    }

    public List<Facture> getAllFactures() throws Exception{
        return factureService.getAllInvoices();
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

    public void updateFacture(Facture facture) throws Exception{
        facture.setStatus("payed");
        factureService.updateInvoice(facture);
    }

    public Facture getFactureById(int id)throws Exception{
        return factureService.getFactureById(id);
    }

    public void getCurrentProviderFactures() throws Exception{
        factureService.getInvoicesByProvider(Session.getCurrentUser().getIdpre()).forEach(System.out::println);
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

        int choice = -1;

        while (choice != 0) {
            try {
                System.out.println("\n=== GESTION DES FACTURES ===");
                System.out.println("1. Lister toutes les factures");
                System.out.println("2. Filtrer par statut");
                System.out.println("3. Filtrer par prestataire");
                System.out.println("4. Supprimer une facture");
                System.out.println("0. Retour");

                System.out.print("Votre choix : ");
                choice = Integer.parseInt(scanner.nextLine());

                switch (choice) {
                    case 1 -> listAll();
                    case 2 -> filterByStatus();
                    case 3 -> filterByProvider();
                    case 4 -> deleteFacture();
                    case 0 -> System.out.println("Retour au menu précédent...");
                    default -> System.out.println("Choix invalide");
                }

            } catch (Exception e) {
                System.out.println("Erreur : " + e.getMessage());
            }
        }
    }

}
