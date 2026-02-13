package org.example.controller;

import org.example.model.Paiement;
import org.example.services.PaiementService;

import java.math.BigDecimal;
import java.util.Date;
import java.util.Scanner;

public class PaiementController {
    private final PaiementService paiementService = new PaiementService();
    private final Scanner scanner = new Scanner(System.in);

    public void createPaiment() throws Exception{
        System.out.print("Entrer id de facture: ");
        int idFacture = Integer.parseInt(scanner.nextLine());
        System.out.print("Montant payé: ");
        BigDecimal montant = new BigDecimal(0);
        montant = scanner.nextBigDecimal();
        Paiement paiement = new Paiement(montant , idFacture , new Date());
        paiementService.effectuerPaiment(paiement);
    }

}
