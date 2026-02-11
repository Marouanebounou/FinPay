package org.example.model;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class Statistique {
    private int id;
    private static int idCounter = 1;
    private List<Facture> factureList;
    private BigDecimal totalAmount;
    private BigDecimal totalComission;
    private List<Client> clients;
    private List<Prestataire> prestataires;

    public Statistique() {
        this.id = idCounter++;
        this.factureList = new ArrayList<>();
        this.totalAmount = BigDecimal.valueOf(0);
        this.totalComission = BigDecimal.valueOf(0);
        this.clients = new ArrayList<>();
        this.prestataires = new ArrayList<>();

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public List<Facture> getFactureList() {
        return factureList;
    }

    public void setFactureList(List<Facture> factureList) {
        this.factureList = factureList;
    }

    public BigDecimal getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(BigDecimal totalAmount) {
        this.totalAmount = totalAmount;
    }

    public BigDecimal getTotalComission() {
        return totalComission;
    }

    public void setTotalComission(BigDecimal totalComission) {
        this.totalComission = totalComission;
    }

    public List<Client> getClients() {
        return clients;
    }

    public void setClients(List<Client> clients) {
        this.clients = clients;
    }

    public List<Prestataire> getPrestataires() {
        return prestataires;
    }

    public void setPrestataires(List<Prestataire> prestataires) {
        this.prestataires = prestataires;
    }
}
