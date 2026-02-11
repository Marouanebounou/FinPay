package org.example.model;

import java.math.BigDecimal;
import java.util.Date;

public class Paiement {
    private int id;
    private static int counter;
    private Date date;
    private BigDecimal balance;
    private Facture facture;

    public Paiement( BigDecimal balance, Facture facture) {
        this.id = counter++;
        this.date = new Date();
        this.balance = balance;
        this.facture = facture;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public BigDecimal getBalance() {
        return balance;
    }

    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }

    public Facture getFacture() {
        return facture;
    }

    public void setFacture(Facture facture) {
        this.facture = facture;
    }
}
