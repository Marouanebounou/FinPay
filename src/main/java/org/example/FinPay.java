package org.example;

import org.example.model.Client;
import org.example.model.Facture;
import org.example.model.Prestataire;
import org.example.model.Statistique;

import java.util.ArrayList;
import java.util.List;

public class FinPay {
    private String domaine ;
    private int id;
    private static int idCounter = 1;
    private List<Facture> factures;
    private List<Client> clients;
    private List<Prestataire> prestataires;
    private Statistique statistique;

    public FinPay(String domaine) {
        this.id = idCounter++;
        this.statistique = new Statistique(this);
        this.domaine = domaine;
        this.factures = new ArrayList<>();
        this.clients = new ArrayList<>();
        this.prestataires = new ArrayList<>();
    }

    public Statistique getStatistique() {
        return statistique;
    }

    public void setStatistique(Statistique statistique) {
        this.statistique = statistique;
    }

    public String getDomaine() {
        return domaine;
    }

    public void setDomaine(String domaine) {
        this.domaine = domaine;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public List<Facture> getFactures() {
        return factures;
    }

    public void setFactures(List<Facture> factures) {
        this.factures = factures;
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
