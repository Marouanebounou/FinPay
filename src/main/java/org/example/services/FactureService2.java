package org.example.services;

public class FactureService2 {
    private FactureRepository repository;

    public FactureService2(FactureRepository repository) {
        this.repository = repository;
    }

    public double calculateCommission() {
        double total = repository.getTotalAmount();
        return total * 0.02;
    }
}
