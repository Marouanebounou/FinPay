package org.example.controller;

import org.example.services.StatistiqueService;

import java.util.Scanner;

public class StatistiqueController {
    private final StatistiqueService statistiqueService = new StatistiqueService();
    private final Scanner scanner = new Scanner(System.in);
    public void afficherStatistiquesGlobales() throws Exception {
        System.out.println("=== Statistiques Globales ===");

        System.out.println("Total paiements : "
                + statistiqueService.totalPaiements());

        System.out.println("Total commissions : "
                + statistiqueService.totalCommissions());
    }
}
