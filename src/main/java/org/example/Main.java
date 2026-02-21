package org.example;

import org.example.controller.MenuController;

public class Main {

    public static void main(String[] args) {
        try {
            System.out.println("=================================");
            System.out.println("        FINPAY APPLICATION       ");
            System.out.println("=================================\n");

            MenuController menuController = new MenuController();
            menuController.start();

        } catch (Exception e) {
            System.err.println("Une erreur critique est survenue.");
            throw new RuntimeException(e);
        }
    }
}