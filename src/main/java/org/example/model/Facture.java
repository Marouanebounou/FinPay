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

    public Facture(int id_pre, BigDecimal balance, LocalDateTime date, String status, int idClient) {
        this.id_pre = id_pre;
        this.balance = balance;
        this.date = date;
        this.status = status;
        this.idClient = idClient;
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
}