package org.example.model;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class Facture {
    private int idInvoice;
    private int id_pre;
    private BigDecimal balance;
    private LocalDateTime date;
    private String status;
    private int idClient;
    private String name;
    private int nombreFactures;

    public String getClientName() {
        return name;
    }

    public void setClientName(String name) {
        this.name = name;
    }

    public Facture(int id_pre, BigDecimal balance, LocalDateTime date, String status, int idClient) {
        this.id_pre = id_pre;
        this.balance = balance;
        this.date = date;
        this.status = status;
        this.idClient = idClient;
    }

    public Facture(int id_pre, int nombreFactures, BigDecimal balance) {
        this.id_pre = id_pre;
        this.nombreFactures = nombreFactures;
        this.balance = balance;
    }

    public int getNombreFactures() {
        return nombreFactures;
    }

    public void setNombreFactures(int nombreFactures) {
        this.nombreFactures = nombreFactures;
    }

    public int getIdInvoice() { return idInvoice; }
    public void setIdInvoice(int idInvoice) { this.idInvoice = idInvoice; }

    public LocalDateTime getDate() { return date; }
    public void setDate(LocalDateTime date) { this.date = date; }

    public BigDecimal getBalance() { return balance; }
    public void setBalance(BigDecimal balance) { this.balance = balance; }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }

    public int getId_pre() { return id_pre; }
    public void setId_pre(int id_pre) { this.id_pre = id_pre; }

    public int getIdClient() { return idClient; }
    public void setIdClient(int idClient) { this.idClient = idClient; }

    @Override
    public String toString() {
        return "Facture{" +
                "idInvoice=" + idInvoice +
                ", id_pre=" + id_pre +
                ", balance=" + balance +
                ", nombreFactures=" + nombreFactures +
                ", date=" + date +
                ", status='" + status + '\'' +
                ", idClient=" + idClient +
                '}';
    }
}