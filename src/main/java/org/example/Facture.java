package org.example;

import java.time.LocalDateTime;

public class Facture {
    private int prestataireId;
    private int factureId;
    private double balence;
    private LocalDateTime date;
    private String status;
    private int counter = 1;
    private int clientId;

    public Facture(int factureId,double balence, LocalDateTime date, String status ,int clientId) {
        this.prestataireId = prestataireId;
        this.factureId = counter++;
        this.balence = balence;
        this.date = date;
        this.status = status;
        this.clientId = clientId;
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

    public int getPrestataireId() {
        return prestataireId;
    }

    public void setPrestataireId(int prestataireId) {
        this.prestataireId = prestataireId;
    }
}
