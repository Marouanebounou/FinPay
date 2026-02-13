package org.example.model;

import java.math.BigDecimal;
import java.util.Date;

public class Paiement {
    private int id;
    private Date date;
    private BigDecimal balance;
    private int idFacture;

    public Paiement(BigDecimal balance, int idFacture , Date date) {
        this.date = date;
        this.balance = balance;
        this.idFacture = idFacture;
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

    public int getIdFacture() {
        return idFacture;
    }

    public void setIdFacture(int idFacture) {
        this.idFacture = idFacture;
    }

    @Override
    public String toString() {
        return "Paiement : " +
                "id= " + id +
                ", date= " + date +
                ", balance= " + balance +
                ", idFacture= " + idFacture;
    }
}
