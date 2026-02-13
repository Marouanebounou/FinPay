package org.example.controller;

import org.example.model.Statistique;
import org.example.sessions.Session;

import java.util.Scanner;

public class MenuController {
    private final Scanner scanner = new Scanner(System.in);

    private final AuthController authController = new AuthController();
    private final PaiementController paiementController = new PaiementController();
    private final StatistiqueController statistiqueController = new StatistiqueController();
    private final ClientController clientController = new ClientController();
    private final PrestataireController prestataireController = new PrestataireController();
    private final FactureController factureController = new FactureController();

    public void start() throws Exception {
        while (Session.isAuth()){
            switch (Session.getRole()){
                case ADMIN -> adminMenu();
                case CLIENT -> clientMenu();
                case PRESTATAIRE -> prestataireMenu();
            }
        }
    }

    private void prestataireMenu() throws Exception {
        System.out.println("\n=== MENU PRESTATAIRE ===");
        System.out.println("1. Voir paiements reçus");
        System.out.println("2. Voir factures clients");
        System.out.println("0. Logout");

        int choice = readChoice();

        switch (choice) {
            case 1 -> paiementController.getAllPaiments();
            case 2 -> factureController.getCurrentProviderFactures();
            case 0 -> authController.logout();
            default -> System.out.println("Choix invalide");
        }
    }

    private void clientMenu() throws Exception {
        System.out.println("\n=== MENU CLIENT ===");
        System.out.println("1. Payer une facture");
        System.out.println("2. Voir mon historique");
        System.out.println("0. Logout");

        int choice = readChoice();

        switch (choice) {
            case 1 -> paiementController.createPaiment();
            case 2 -> paiementController.getCurrentClientPaiments();
            case 0 -> authController.logout();
            default -> System.out.println("Choix invalide");
        }
    }

    private void adminMenu() throws Exception {
        System.out.println("\n=== MENU ADMIN ===");
        System.out.println("1. Voir statistiques");
        System.out.println("2. Voir paiements");
        System.out.println("3. Gérer clients");
        System.out.println("4. Gérer prestataires");
        System.out.println("5. Gérer factures");
        System.out.println("0. Logout");

        int choice = readChoice();

        switch (choice){
            case 1 -> statistiqueController.afficherStatistiquesGlobales();
            case 2 -> paiementController.getAllPaiments();
            case 3 -> clientController.menuGestionClients();
            case 4 -> prestataireController.menuGestionPrestataires();
            case 5 -> factureController.menuGestionFactures();
            case 0 -> authController.logout();
            default -> System.out.println("Choix invalide");
        }

    }

    private int readChoice(){
        System.out.print("Votre choix: ");
        while (!scanner.hasNext()){
            scanner.next();
            System.out.print("Entrez un nombre: ");
        }
        return scanner.nextInt();
    }
}
