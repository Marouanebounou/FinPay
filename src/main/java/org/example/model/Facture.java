package org.example.model;


import java.sql.Date;

public class Facture {
    private int idInvoice;
    private int id_pre;
    private double balence;
    private Date date;
    private String status;
    private int idClient;

    public Facture(int id_pre,double balence, Date date, String status ,int idClient) {
        this.id_pre = id_pre;
        this.balence = balence;
        this.date = date;
        this.status = status;
        this.idClient = idClient;
    }

    public int getIdInvoice() {
        return idInvoice;
    }

    public void setIdInvoice(int idInvoice) {
        this.idInvoice = idInvoice;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
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

    public int getId_pre() {
        return id_pre;
    }

    public void setId_pre(int id_pre) {
        this.id_pre = id_pre;
    }

    public int getIdClient() {
        return idClient;
    }

    public void setIdClient(int idClient) {
        this.idClient = idClient;
    }
}
