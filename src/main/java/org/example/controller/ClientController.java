package org.example.controller;

import org.example.model.Client;
import org.example.services.ClientService;
import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

public class ClientController {

    private final ClientService clientService = new ClientService();
    Scanner scanner = new Scanner(System.in);

    public void addClient() throws SQLException {
        System.out.print("Nom du client: ");
        String name = scanner.nextLine();
        System.out.print("Âge: ");
        int age = scanner.nextInt();
        scanner.nextLine();
        System.out.print("Email: ");
        String email = scanner.nextLine();
        System.out.print("Mot de passe: ");
        String password = scanner.nextLine();

        Client client = new Client(name, age, email, password);
        clientService.registerClient(client);
        System.out.println("Client ajouté avec succès !");
    }

    public void listClients() throws SQLException {
        List<Client> clients = clientService.getAllClients();
        if (clients.isEmpty()) {
            System.out.println("Aucun client trouvé.");
        } else {
            clients.forEach(System.out::println);
        }
    }

    public void deleteClient() throws SQLException {
        System.out.print("ID du client à supprimer: ");
        int id = scanner.nextInt();
        boolean deleted = clientService.deleteClient(id);
        if (deleted) {
            System.out.println("Client supprimé.");
        } else {
            System.out.println("Aucun client trouvé avec cet ID.");
        }
    }

    public void searchClient() throws SQLException {
        System.out.print("Entrez l'ID du client à rechercher: ");
        int id = scanner.nextInt();
        scanner.nextLine();

        Client client = clientService.getClientById(id);

        if (client != null) {
            System.out.println("Client trouvé : " + client);
        } else {
            System.out.println("Aucun client trouvé avec l'ID " + id);
        }
    }

    public void menuGestionClients() {
        int choix = -1;

        do {
            System.out.println("\n===== Gestion des Clients =====");
            System.out.println("1. Ajouter un client");
            System.out.println("2. Lister tous les clients");
            System.out.println("3. Rechercher un client par ID");
            System.out.println("4. Supprimer un client");
            System.out.println("0. Retour");
            System.out.print("Votre choix : ");

            try {
                choix = Integer.parseInt(scanner.nextLine());

                switch (choix) {
                    case 1:
                        addClient();
                        break;
                    case 2:
                        listClients();
                        break;
                    case 3:
                        searchClient();
                        break;
                    case 4:
                        deleteClient();
                        break;
                    case 0:
                        System.out.println("Retour au menu principal...");
                        break;
                    default:
                        System.out.println("Choix invalide.");
                }
            } catch (Exception e) {
                System.out.println("Erreur : " + e.getMessage());
                scanner.nextLine();
            }

        } while (choix != 0);
    }

}

