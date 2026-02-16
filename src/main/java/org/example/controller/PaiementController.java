package org.example.controller;

import org.example.model.Facture;
import org.example.model.Paiement;
import org.example.services.PaiementService;
import org.example.sessions.Session;

import java.math.BigDecimal;
import java.sql.Date;
import java.time.LocalDate;
import java.util.Scanner;

public class PaiementController {
    private final PaiementService paiementService = new PaiementService();
    private final FactureController factureController = new FactureController();
    private final Scanner scanner = new Scanner(System.in);

    public void createPaiment() {

        try {
            factureController.getAllFactures().stream().filter(facture ->
                            facture.getIdClient() == Session.getCurrentUser().getId() && facture.getStatus().equals("not payed")
                            ).forEach(System.out::println);
            System.out.print("Entrer id de facture: ");
            String idInput = scanner.nextLine();
            int idFacture = Integer.parseInt(idInput);

            Facture facture = factureController.getFactureById(idFacture);

            if (facture == null) {
                System.out.println("Facture introuvable.");
                return;
            }

            System.out.print("Montant payé: ");
            String montantInput = scanner.nextLine();
            BigDecimal montant = new BigDecimal(montantInput);

            LocalDate date = LocalDate.now();

            Paiement paiement = new Paiement(
                    montant,
                    idFacture,
                    java.sql.Date.valueOf(date),
                    Session.getCurrentUser().getId()
            );

            factureController.updateFacture(facture);
            paiementService.effectuerPaiment(paiement);

            System.out.println("Paiement effectué avec succès.");

        } catch (NumberFormatException e) {
            System.out.println("Veuillez entrer des valeurs numériques valides.");
        } catch (Exception e) {
            System.out.println("Erreur lors du paiement: " + e.getMessage());
        }
    }


    public void getAllPaiments() throws Exception {
        paiementService.getAllPaiments().forEach(System.out::println);
    }

    public void getPaimentById() throws Exception{
        System.out.print("Paiment ID: ");
        int id = Integer.parseInt(scanner.nextLine());
        paiementService.getPaimentById(id).toString();
    }

    public void getCurrentClientPaiments() throws Exception {

        int currentId = Session.getCurrentUser().getId();

        paiementService.getAllPaiments().stream()
                .filter(p -> p.getClientId() == currentId)
                .forEach(p -> System.out.println("MATCH FOUND: " + p));
    }


}
