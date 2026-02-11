package org.example.model;

import java.time.LocalDateTime;

public class Facture {
    private Prestataire prestataire;
    private int factureId;
    private double balence;
    private LocalDateTime date;
    private String status;
    private static int counter = 1;
    private Client client;

    public Facture(Prestataire prestataire,double balence, LocalDateTime date, String status ,Client client) {
        this.prestataire = prestataire;
        this.factureId = counter++;
        this.balence = balence;
        this.date = date;
        this.status = status;
        this.client = client;
    }

    public int getFactureId() {
        return factureId;
    }

    public void setFactureId(int factureId) {
        this.factureId = factureId;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    public double getBalence() {
        return balence;
    }

    public void setBalence(double balence) {
        this.balence = balence;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }


}
