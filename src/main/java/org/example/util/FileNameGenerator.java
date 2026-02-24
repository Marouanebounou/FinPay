package org.example.util;

public class FileNameGenerator {

    public static String facture(int id) {
        return "facture_" + id + ".pdf";
    }

    public static String receiptName(int id) {
        return "recu_" + id + ".pdf";
    }

    public static String rapport(int month, int year) {
        return String.format("rapport%02d%d.xls", month, year);
    }
}