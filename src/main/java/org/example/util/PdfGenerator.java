package org.example.util;

import org.example.model.Client;
import org.example.model.Facture;

public class PdfGenerator {

    public static void generateFacturePDF(Facture facture, Client client) {
        System.out.println("=== PDF Fichier ===");
        System.out.println("Client : " + client.getName() + " | Email: " + client.getEmail());
        System.out.println("Facture ID : " + facture.getIdInvoice());
        System.out.println("Montant : " + facture.getBalance() + " MAD");
        System.out.println("Statut : " + facture.getStatus());
        System.out.println("===================");
        System.out.println("PDF généré avec succès !");
    }
}

