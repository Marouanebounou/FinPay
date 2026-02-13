package org.example.services;

import org.example.dao.FactureDao;
import org.example.model.Facture;
import java.sql.SQLException;
import java.util.List;

public class FactureService {
    private final FactureDao factureDao = new FactureDao();

    public void createInvoice(Facture facture) throws SQLException {
        if (facture.getBalance().doubleValue() <= 0) {
            throw new IllegalArgumentException("Le solde ne peut pas être négatif.");
        }
        factureDao.addInvoice(facture);
    }

    public List<Facture> getAllInvoices() throws SQLException {
        return factureDao.showFactures();
    }

    public List<Facture> getInvoicesByStatus(String status) throws SQLException {
        if (status == null || status.isBlank()) {
            return getAllInvoices();
        }
        return factureDao.filterByStatus(status);
    }

    public List<Facture> getInvoicesByProvider(int idPre) throws SQLException {
        if (idPre <= 0) {
            throw new IllegalArgumentException("ID prestataire invalide.");
        }
        return factureDao.filterByPrestataire(idPre);
    }

    public boolean deleteInvoice(int id) throws SQLException {
        return factureDao.removeInvoice(id);
    }
}