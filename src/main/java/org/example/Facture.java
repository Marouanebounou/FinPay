package org.example;

import java.time.LocalDateTime;

public class Facture {
    private int factureId;
    private double balance;
    private LocalDateTime date;
    private String status;
    private int counter = 1;

    public Facture(int factureId, double balance, LocalDateTime date, String status) {
        this.factureId = counter++;
        this.balance = balance;
        this.date = date;
        this.status = status;
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

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

}
